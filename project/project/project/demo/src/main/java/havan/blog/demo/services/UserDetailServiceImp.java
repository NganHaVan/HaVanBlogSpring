package havan.blog.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import havan.blog.demo.models.Users;
import havan.blog.demo.models.UsersRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService{
	private final UsersRepository userRepo;
	
	@Autowired
	public UserDetailServiceImp(UsersRepository userRepo) {
		// TODO Auto-generated constructor stub
		super();
		this.userRepo=userRepo;
	}
	
//	public Users currUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub 
		Users currUser=userRepo.findByUsername(username);
		UserDetails user= new org.springframework.security.core.userdetails.User(username, currUser.getHashPass(), AuthorityUtils.createAuthorityList(currUser.getRole()));
		return user;
	}

}
