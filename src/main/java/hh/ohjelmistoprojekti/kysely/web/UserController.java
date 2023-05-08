package hh.ohjelmistoprojekti.kysely.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hh.ohjelmistoprojekti.kysely.domain.User;
import hh.ohjelmistoprojekti.kysely.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {

		@Autowired
		private UserRepository urepository;
		
		@RequestMapping(value = "/login")
		public String login() {
			return "login";
		}
		
		@RequestMapping (value = "/signup")
		public String addUser (Model model) {
			model.addAttribute("user", new User());
			return "signup";
		}
		
		@RequestMapping(value ="/saveuser", method = RequestMethod.POST)
		public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingresult, Model model) {
			String password = user.getPasswordHash();
			String password2 = user.getpasswordConfirmation();
			boolean passwordsEqual = password.equals(password2);
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String hashedPassword = bcrypt.encode(password);
			User u1 = new User();
			u1.setRole("USER");
			u1.setPasswordHash(hashedPassword);
			u1.setUsername(user.getUsername());
			u1.setEmail(user.getEmail());
			boolean userExists = urepository.findByUsername(u1.getUsername()) != null;
			if (bindingresult.hasErrors() || !passwordsEqual || userExists) {			
				if(!passwordsEqual) {	
					bindingresult.rejectValue("passwordConfirmation", "err.passwordConfirmation", "Your password and confirmation password must match.");
				}
				if(userExists) {
					bindingresult.rejectValue("username", "err.username", "Someone already has that username. Try again?");
				}
				return "signup.html";
			}
			urepository.save(u1);
			model.addAttribute("successMessage", "User created successfully");
			return "redirect:/login";
		}
	    
	    @GetMapping("/users")
	    public @ResponseBody Iterable<User> getUsers() {
	        return urepository.findAll();
	    }

	}