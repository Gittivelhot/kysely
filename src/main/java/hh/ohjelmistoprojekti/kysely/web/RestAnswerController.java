package hh.ohjelmistoprojekti.kysely.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.ohjelmistoprojekti.kysely.domain.Answer;
import hh.ohjelmistoprojekti.kysely.domain.AnswerRepository;
import hh.ohjelmistoprojekti.kysely.domain.Poll;
import hh.ohjelmistoprojekti.kysely.domain.PollAnswer;
import hh.ohjelmistoprojekti.kysely.domain.PollAnswerRepository;
import hh.ohjelmistoprojekti.kysely.domain.PollRepository;

@Controller
public class RestAnswerController {
	
	@Autowired
	private PollAnswerRepository parepository;
	
	@Autowired
	private PollRepository prepository;
	
	@Autowired
	private AnswerRepository arepository;
	
    // RESTful service to save new answer
    @RequestMapping(value="/json/answers", method = RequestMethod.POST)
    public @ResponseBody String saveAnswerRest(@RequestBody PollAnswer pollanswer) {
		Optional<Poll> pollOptional = prepository.findById(pollanswer.getPoll_id());
		if(pollOptional.isPresent()) {
			pollanswer.setPoll(pollOptional.get());
			parepository.save(pollanswer);
			if(pollanswer.getAnswers() != null) {
				for(Answer answer : pollanswer.getAnswers()) {
					answer.setPollform(pollanswer);
					arepository.save(answer);
				}
			}			
		}
		return "Ok";
    }
    
 // RESTful service to get all answers in JSON format
    @RequestMapping(value="/json/list", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<PollAnswer> getAllAnswersRest() {
        List<PollAnswer> answers = (List<PollAnswer>) parepository.findAll();
        return answers;
    }
    
    // RESTful service to get ONE answer in JSON format
    @RequestMapping(value="/json/answers/{id}", method=RequestMethod.GET)
    public @ResponseBody Poll getOnePollAnswers(@PathVariable("id") Long poll_id) {
        Optional<Poll> poll = prepository.findById(poll_id);
        if(poll.isPresent() && poll.get().getVisible()) {
        	return poll.get();
        }
		return new Poll();
    }

}