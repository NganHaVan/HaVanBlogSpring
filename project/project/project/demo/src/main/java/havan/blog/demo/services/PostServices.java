package havan.blog.demo.services;

import java.util.List;

import havan.blog.demo.models.Post;

public interface PostServices {
	List<Post> findAll();
	List<Post> findDemo();
//	List<Post> findlatest5();
//	Page<Post> findlatest5(Post post);
}
