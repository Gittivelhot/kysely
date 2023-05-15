package hh.ohjelmistoprojekti.kysely.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.ohjelmistoprojekti.kysely.domain.Poll;
import hh.ohjelmistoprojekti.kysely.domain.PollRepository;
import hh.ohjelmistoprojekti.kysely.domain.Question;
import hh.ohjelmistoprojekti.kysely.domain.QuestionRepository;
import hh.ohjelmistoprojekti.kysely.domain.User;
import hh.ohjelmistoprojekti.kysely.domain.UserRepository;

@Controller
public class PollController {

	@Autowired
	private QuestionRepository qrepository;
	
	@Autowired
	private PollRepository prepository;
	
	@Autowired
	private UserRepository urepository;

	@RequestMapping(value = "/addpoll", method = RequestMethod.GET)
	public String getNewPoll(Model model) {
		model.addAttribute("poll", new Poll());
		return "addpoll";
	}

	@RequestMapping(value = "/savepoll", method = RequestMethod.POST)
	public String savePoll(Poll poll, Principal principal) {
		String username = principal.getName();
		User user = urepository.findByUsername(username);
		poll.setUser(user);
		poll.setVisible(true);
		prepository.save(poll);
	    for (Question question : poll.getQuestions()) {
	    	question.setPoll(poll);
	        qrepository.save(question);
	    } 
	    return "redirect:/";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPolls(Model model, Principal principal) {
        List<Poll> polls = (List<Poll>) prepository.findAll();
        List<Question> questions = (List<Question>) qrepository.findAll();
        model.addAttribute("polls", polls);
        model.addAttribute("questions", questions);
		model.addAttribute("user", principal.getName());
		Authentication authentication = (Authentication) principal;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		model.addAttribute("isAdmin", isAdmin);
        return "polls";
    }
	
	@RequestMapping(value = "/poll/{id}", method = RequestMethod.GET)
    public String getPollAnswers(@PathVariable("id") Long poll_id, Model model, Principal principal) {
		Authentication authentication = (Authentication) principal;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		Optional<Poll> pollOptional = prepository.findById(poll_id);
		if(pollOptional.isPresent() && (isAdmin || pollOptional.get().getUser().getUsername().equals(principal.getName()))) {
			model.addAttribute("poll", pollOptional.get());
			return "pollanswers";
		}
		return "redirect:../";
	}
	
	@RequestMapping(value = "/hide/{id}", method = RequestMethod.GET)
	public String hidePoll(@PathVariable("id") Long poll_id, Principal principal) {
		Authentication authentication = (Authentication) principal;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		Optional<Poll> pollOptional = prepository.findById(poll_id);
		if(pollOptional.isPresent() && (isAdmin || pollOptional.get().getUser().getUsername().equals(principal.getName()))) {
			Poll poll = pollOptional.get();
			poll.setVisible(false);
			prepository.save(poll);
		}
		return "redirect:../";
	}
	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showPoll(@PathVariable("id") Long poll_id, Principal principal) {
		Authentication authentication = (Authentication) principal;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		Optional<Poll> pollOptional = prepository.findById(poll_id);
		if(pollOptional.isPresent() && (isAdmin || pollOptional.get().getUser().getUsername().equals(principal.getName()))) {
			Poll poll = pollOptional.get();
			poll.setVisible(true);
			prepository.save(poll);
		}
		return "redirect:../";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePoll(@PathVariable("id") Long poll_id, Model model, Principal principal) {
		Authentication authentication = (Authentication) principal;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		Optional<Poll> pollOptional = prepository.findById(poll_id);
		if(pollOptional.isPresent() && (isAdmin || pollOptional.get().getUser().getUsername().equals(principal.getName()))) {
			prepository.deleteById(poll_id);
		}
		return "redirect:../";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPoll(@PathVariable("id") Long pollId, Model model, Principal principal) {
		Authentication authentication = (Authentication) principal;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		Optional<Poll> pollOptional = prepository.findById(pollId);
		if(isAdmin || pollOptional.get().getUser().getUsername().equals(principal.getName())) {
			model.addAttribute("poll", prepository.findById(pollId));
			model.addAttribute("questions", pollOptional.get().getQuestions());
			model.addAttribute("pollId", pollId);
			return "editpoll";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updatePoll(@PathVariable("id") Long pollId, Poll updatedPoll, Principal principal) {
		Authentication authentication = (Authentication) principal;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
		Optional<Poll> pollOptional = prepository.findById(pollId);
		if(pollOptional.isPresent() && (isAdmin || pollOptional.get().getUser().getUsername().equals(principal.getName()))) {
			Poll poll = pollOptional.get();
	        poll.setTitle(updatedPoll.getTitle());
	        
	        // Remove all existing questions
	        List<Question> existingQuestions = poll.getQuestions();
	        for (Question existingQuestion : existingQuestions) {
	            existingQuestion.setPoll(null); // Remove the reference to the poll
	        }
	        existingQuestions.clear();
	        
	        // Add the updated questions
	        List<Question> updatedQuestions = updatedPoll.getQuestions();
	        for (Question updatedQuestion : updatedQuestions) {
	            updatedQuestion.setPoll(poll); // Set the reference to the updated poll
	            existingQuestions.add(updatedQuestion);
	        }
	        
	        poll.setVisible(true);
	        prepository.save(poll);
	    }
	    return "redirect:../";
	}
	
	
}