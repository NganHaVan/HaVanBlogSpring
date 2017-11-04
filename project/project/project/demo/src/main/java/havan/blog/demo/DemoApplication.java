package havan.blog.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import havan.blog.demo.models.Post;
import havan.blog.demo.models.PostRepository;
import havan.blog.demo.models.Users;
import havan.blog.demo.models.UsersRepository;

@SpringBootApplication
public class DemoApplication {
	
	private static final Logger log= LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner postDemo(PostRepository postRepo, UsersRepository uRepo){
		return (args)->{
/*			List<Post> posts=new ArrayList<>();
			posts.add(new Post("First Post","<p>Line #1.</p><p>Line #2</p>",new Users("peter10")));
			posts.add(new Post("Second Post","Second post content:<ul><li>line 1</li><li>line 2</li></p>", new Users("maryJane")));
			posts.add(new Post("Post #3", "<p>The post number 3 nice</p>",new Users("tomHank")));
			posts.add(new Post("Forth Post", "<p>Not interesting post</p>" , new Users("pyterJ")));
			posts.add(new Post("Post Number 5", "<p>Just posting</p>", new Users("Valak")));
			posts.add(new Post("Sixth Post", "<p>Another interesting post</p>", new Users("anabelle")));
			postRepo.save(posts);*/
			
			
//			Create havan/havan user/user
			Users user1= new Users("havan","$2a$10$uH3I0o9tXEsU3CrAsGr6vuoMvmJGULXpBSfYb4Qac7x9RmR/Zabbq","havan@email.com","ADMIN");
			Users user2= new Users("user","$2a$10$0b/K7B6qe2vEHDw8NY81ieNRwJnePLARILjnMYbbTFuv5LDjVubdO","user@email.com","USER");
			
			uRepo.save(user1);
			uRepo.save(user2);
			
			log.info("Fetch some posts:");
			for(Post post: postRepo.findAll()){
				log.info(post.toString());
			}
		};
	}
}
