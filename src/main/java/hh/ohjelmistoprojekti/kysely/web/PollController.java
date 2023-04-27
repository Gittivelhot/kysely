package hh.ohjelmistoprojekti.kysely.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.ohjelmistoprojekti.kysely.domain.Answer;
import hh.ohjelmistoprojekti.kysely.domain.AnswerRepository;
import hh.ohjelmistoprojekti.kysely.domain.Poll;
import hh.ohjelmistoprojekti.kysely.domain.PollRepository;
import hh.ohjelmistoprojekti.kysely.domain.Question;
import hh.ohjelmistoprojekti.kysely.domain.QuestionRepository;


@Controller
public class PollController {

	
	@Autowired
	private QuestionRepository qrepository;
	
	@Autowired
	private PollRepository prepository;
	
	@Autowired
	private AnswerRepository arepository;

	@RequestMapping(value = "/addpoll", method = RequestMethod.GET)
	public String getNewPoll(Model model) {
		model.addAttribute("poll", new Poll());
		return "addpoll";
	}

	@RequestMapping(value = "/savepoll", method = RequestMethod.POST)
	public String savePoll(@ModelAttribute("poll") Poll poll) {
		System.out.print("Kyselyn kysymykset: " + poll.getQuestions());
		prepository.save(poll);
	    for (Question question : poll.getQuestions()) {
	    	question.setPoll(poll);
	        qrepository.save(question);
	    } 
	    return "redirect:/polls";
	}

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
    public String getPolls(Model model) {
        List<Poll> polls = (List<Poll>) prepository.findAll();
        List<Question> questions = (List<Question>) qrepository.findAll();
        List <Answer> answers = (List<Answer>) arepository.findAll();
        model.addAttribute("polls", polls);
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        return "polls";
    }
	
	
}