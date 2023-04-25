package hh.ohjelmistoprojekti.kysely.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hh.ohjelmistoprojekti.kysely.domain.AnswerRepository;
import hh.ohjelmistoprojekti.kysely.domain.Poll;
import hh.ohjelmistoprojekti.kysely.domain.PollRepository;
import hh.ohjelmistoprojekti.kysely.domain.QuestionRepository;
import org.springframework.stereotype.Controller;


@Controller
public class RestPollController {

	
	@Autowired
	private QuestionRepository qrepository;
	
	@Autowired
	private PollRepository prepository;
	
	@Autowired
	private AnswerRepository arepository;
	
	
	// REST	 lists all polls
    @RequestMapping(value="/json/polls", method = RequestMethod.GET)
    public @ResponseBody List<Poll> pollListRest() {
        return (List<Poll>) prepository.findAll();
    }

    // REST by id
    @RequestMapping (value ="json/polls/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional <Poll> findPollRest(@PathVariable("id") Long id){
        return prepository.findById(id);
    }
    
    
}
