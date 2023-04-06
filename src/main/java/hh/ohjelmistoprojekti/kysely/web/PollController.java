package hh.ohjelmistoprojekti.kysely.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hh.ohjelmistoprojekti.kysely.domain.Poll;
import hh.ohjelmistoprojekti.kysely.domain.PollRepository;
import hh.ohjelmistoprojekti.kysely.domain.Question;
import hh.ohjelmistoprojekti.kysely.domain.QuestionRepository;
import jakarta.validation.Valid;

@Controller
public class PollController {

	
	@Autowired
	private QuestionRepository qrepository;
	
	@Autowired
	private PollRepository prepository;

	@RequestMapping(value = "/addpoll", method = RequestMethod.GET)
	public String getNewPoll(Model model) {
		model.addAttribute("poll", new Poll());
		return "addpoll";
	}

	
	@RequestMapping(value = "/savepoll", method = RequestMethod.POST)
	public String savePoll(@ModelAttribute("poll") Poll poll) {
	    // Create three new Question objects and add them to the Poll's list of questions
	    List<Question> questions = new ArrayList<>();
	    questions.add(new Question(poll.getQuestions().get(0).getQuery(), poll));
	    questions.add(new Question(poll.getQuestions().get(1).getQuery(), poll));
	    questions.add(new Question(poll.getQuestions().get(2).getQuery(), poll));
	    poll.setQuestions(questions);

	    // Save the new Poll object to the database using the PollRepository
	    prepository.save(poll);

	    // Redirect to the list of all polls
	    return "redirect:/polls";
	}

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public String getPolls(Model model) {
		List<Poll> polls = (List<Poll>) prepository.findAll();
		List<Question> questions = (List<Question>) qrepository.findAll();
		model.addAttribute("polls", polls);
		model.addAttribute("questions", questions);
		return "polls";
	}
	
	
}