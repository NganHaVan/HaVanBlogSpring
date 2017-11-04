package havan.blog.demo.models;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
	Users findByEmail(String email);
	Users findByUsername (String username);
	Users findById(Long id);
}
