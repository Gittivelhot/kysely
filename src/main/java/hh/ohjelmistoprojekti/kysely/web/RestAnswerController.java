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
import org.springframework.web.bind.annotation.RestController;

import hh.ohjelmistoprojekti.kysely.domain.Answer;
import hh.ohjelmistoprojekti.kysely.domain.AnswerRepository;
import hh.ohjelmistoprojekti.kysely.domain.Poll;

@Controller
public class RestAnswerController {
	
	@Autowired
	private AnswerRepository arepository;
	
    // RESTful service to save new answer
    @RequestMapping(value="/json/answers", method = RequestMethod.POST)
    public @ResponseBody Answer saveAnswerRest(@RequestBody Answer answer) {
        return arepository.save(answer);
    }
    
    //restfull service to list all answers
    @RequestMapping (value = "/json/list/answers", method = RequestMethod.GET)
    public @ResponseBody List <Answer> answerlistRest (){
    	return (List <Answer>) arepository.findAll();
    }
    
    
    
}
