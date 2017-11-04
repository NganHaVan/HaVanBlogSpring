package havan.blog.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import havan.blog.demo.models.Post;
import havan.blog.demo.models.PostRepository;
import havan.blog.demo.models.Users;

@Service
@Primary
public class PostServicesImp implements PostServices{
	private List<Post> posts= new ArrayList<Post>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add(new Post("First Post","<p>Line #1.</p><p>Line #2</p>",new Users("peter10")));
		add(new Post("Second Post","Second post content:<ul><li>line 1</li><li>line 2</li></p>", new Users("maryJane")));
		add(new Post("Post #3", "<p>The post number 3 nice</p>",new Users("tomHank")));
		add(new Post("Forth Post", "<p>Not interesting post</p>" , new Users("pyterJ")));
		add(new Post("Post Number 5", "<p>Just posting</p>", new Users("Valak")));
		add(new Post("Sixth Post", "<p>Another interesting post</p>", new Users("anabelle")));
	}};
	
	
	@Autowired
	private PostRepository pRepo;

	@Override
	public List<Post> findDemo() {
		// TODO Auto-generated method stub
		return (List<Post>) pRepo.save(posts);
	}

	@Override
	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return posts;
	}

/*	@Override
	public List<Post> findlatest5(Post post) {
		// TODO Auto-generated method stub
		return pRepo.findLatest5ByOrderByDateDesc(post.getDate(), new PageRequest(0, 5));
	}*/	

/*	@Override
	public Page<Post> findlatest5(Post post) {
		// TODO Auto-generated method stub
		return pRepo.findLatest5ByOrderByDateDesc(post.getDate(),new PageRequest(0, 5, new Sort(new Order(Direction.DESC,"date"))));
	}*/
	
	
}

	