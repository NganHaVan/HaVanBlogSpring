package havan.blog.demo.models;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>{
	
	List<Post> findAllByOrderByIdDesc();
	List<Post> findById(Long id);
	List<Post> findByAuthor(Users author);
	List<Post> findTop5ByOrderByDateDesc();
//	List<Post> findAll(PageRequest pageRequest);
	
}
