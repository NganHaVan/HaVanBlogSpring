package havan.blog.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import havan.blog.demo.models.Post;
import havan.blog.demo.models.PostRepository;
import havan.blog.demo.models.Users;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
	
	@Autowired
	private PostRepository pRepo;
	
	@Test
	public void createNewPost(){
		Post post= new Post("Hello","Hello World", new Users("Valak"));
		pRepo.save(post);
		assertThat(post.getId()).isNotNull();
	}

}
