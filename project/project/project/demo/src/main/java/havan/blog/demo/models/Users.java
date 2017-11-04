package havan.blog.demo.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=30)
	private String username; 
	
	@Column(nullable=false)
	private String hashPass; 
	
	// Unique email	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String role;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL)
	private List<Post> posts;
	
	public Users(){
		
	}
	
	public Users(String username){
		this.username=username;
	}

	public Users(String username, String hashPass, String email, String role) {
		super();
		this.username = username;
		this.hashPass = hashPass;
		this.email = email;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashPass() {
		return hashPass;
	}

	public void setHashPass(String hashPass) {
		this.hashPass = hashPass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username  + "]";
	}

	
	
	
}
