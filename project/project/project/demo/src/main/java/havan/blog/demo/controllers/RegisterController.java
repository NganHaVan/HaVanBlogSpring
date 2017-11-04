package havan.blog.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import havan.blog.demo.models.RegisterForm;
import havan.blog.demo.models.Users;
import havan.blog.demo.models.UsersRepository;

@Controller
public class RegisterController {
	@Autowired
	private UsersRepository uRepo;
	
	//  LOGIN CONTROLLER
	@RequestMapping(value="/users/login")
	public String login(){
		return "users/login";
	}
	
	@RequestMapping(value="users/register")
	public String addUser(Model model){
		model.addAttribute("signupForm", new RegisterForm());
		return "users/register";
	}
	
	@RequestMapping(value="saveuser", method= RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupForm") RegisterForm signupForm, BindingResult bindingResult){
		if (!bindingResult.hasErrors()) {
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String pass=signupForm.getPassword();
				BCryptPasswordEncoder bc= new BCryptPasswordEncoder();
				String hashPass=bc.encode(pass);
				
				Users newUser=new Users();
				newUser.setHashPass(hashPass);
				newUser.setUsername(signupForm.getUsername());
				newUser.setEmail(signupForm.getEmail());
				newUser.setRole("USER");
				
				if (uRepo.findByEmail(signupForm.getEmail())==null) {	
					uRepo.save(newUser);
				}
				else {
					bindingResult.rejectValue("email", "err.email", "Email already registered");
					return "users/register";
				}
			}
			else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
				return "/users/register";
			}
		}
		else {
			return "/users/register";
		}
		return "redirect:/users/login";
		
	}
}
