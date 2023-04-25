package hh.ohjelmistoprojekti.kysely.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.ohjelmistoprojekti.kysely.domain.Answer;
import hh.ohjelmistoprojekti.kysely.domain.AnswerRepository;
import hh.ohjelmistoprojekti.kysely.domain.Poll;
import hh.ohjelmistoprojekti.kysely.domain.PollRepository;
import hh.ohjelmistoprojekti.kysely.domain.QuestionRepository;

public class RestAnswerController {
	
	@Autowired
	private AnswerRepository arepository;
	
    // RESTful service to save new student
    @RequestMapping(value="json/answers", method = RequestMethod.POST)
    public @ResponseBody Answer saveAnswerRest(@RequestBody Answer answer) {
        return arepository.save(answer);
    }
    
}
