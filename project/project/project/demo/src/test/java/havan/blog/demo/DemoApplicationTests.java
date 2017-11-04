package havan.blog.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import havan.blog.demo.controllers.HomeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	@Autowired 
	HomeController homeController;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(homeController).isNotNull();
	}

}
