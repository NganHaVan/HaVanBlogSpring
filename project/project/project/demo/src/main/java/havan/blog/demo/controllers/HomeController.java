package havan.blog.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import havan.blog.demo.models.Post;
import havan.blog.demo.models.PostRepository;
import havan.blog.demo.models.Users;
import havan.blog.demo.models.UsersRepository;
import havan.blog.demo.services.NotiService;

@Controller
public class HomeController {
	
	@Autowired PostRepository pRepo;
	
	@Autowired
	private NotiService notifyService;
	
	@Autowired
	private UsersRepository uRepo;
	
	@RequestMapping(path="/*")
	public String index(Post post,Model model){
		List<Post> latest5Posts=pRepo.findTop5ByOrderByDateDesc();
		model.addAttribute("latest5posts",latest5Posts);
		
		List<Post> latest3Posts= (List<Post>) pRepo.findAllByOrderByIdDesc();
		model.addAttribute("latest3posts",latest3Posts);
		
		return "index";
	}
	
	//	POST CONTROLLER
		//View posts
	@RequestMapping(value="/posts/view/{id}", method=RequestMethod.GET)
	public String viewPost(@PathVariable("id") Long id, Model model){
		if (pRepo.findOne(id)==null) {
			notifyService.AddErrorMessage("Cannot find post "+id);
			return "redirect:/";
		}
		model.addAttribute("post",pRepo.findOne(id));
		
		return "posts/view";
	}
		//Add Posts
	@RequestMapping(value="/posts/create", method=RequestMethod.GET)
	public String addPost(Model model){
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		
		UserDetails userDetial= (UserDetails) auth.getPrincipal();	
		Users user= uRepo.findByUsername(name);
		Long id= user.getId();	
		
		model.addAttribute("add", new Post());
		model.addAttribute("author", id);
		model.addAttribute("authorName", name);
		return "posts/create";
		
	}
		//Save post
	@RequestMapping(value="/posts/savePost", method=RequestMethod.POST)
	public String savePost(Post post){
		System.out.println(post.getBody() + " user: " + post.getAuthor());

		pRepo.save(post);
		return "redirect:/";
		
	}
		//Delete post
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deletePost(@PathVariable("id") long id, Model model){
		pRepo.delete(id);
		return "redirect:../";		
	}
	
		// Edit post
	@RequestMapping(value="/edit/{id}")
	public String editPost(@PathVariable("id") long id,Post post, Model model){
		model.addAttribute("post",pRepo.findOne(id));
		return "posts/edit";		
	}
	
		// View user's posts
	@RequestMapping(value="/posts/index", method=RequestMethod.GET)
	public String userPost(Users author,Principal principal ,Model model){
		String user=principal.getName();
		author=uRepo.findByUsername(user);
		
		model.addAttribute("posts", pRepo.findByAuthor(author));
		
		return "/posts/index";
	}
	
	// Users View
	@RequestMapping(value="/users/index", method=RequestMethod.GET)
	public String viewUsers(Model model){
		model.addAttribute("users", uRepo.findAll());
		
		return "/users/index";
		
	}
	
	// Post view for admin
	@RequestMapping(value="/posts/postViewForAdmin")
	public String adminViewPost(Model model){
		model.addAttribute("posts",pRepo.findAll());
		
		return "/posts/postViewForAdmin";
		
	}
	
	// Restful Service to get all posts
	@RequestMapping(value="getposts",method=RequestMethod.GET)
	public @ResponseBody List<Post> postRest(){
		return (List<Post>) pRepo.findAll();
	} 
	
	// Restful Service to get posts by Id
	@RequestMapping(value="/getposts/{id}", method=RequestMethod.GET)
	public @ResponseBody Post findPostRest(@PathVariable("id") Long id){
		if (pRepo.findOne(id)==null) {
			return null;
		}
		
		return pRepo.findOne(id);
	}
}
