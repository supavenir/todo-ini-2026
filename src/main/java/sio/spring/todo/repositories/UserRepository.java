package sio.spring.todo.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import sio.spring.todo.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	@Query("SELECT u FROM User u WHERE u.login = :login")
	public Optional<User> findByLogin(String login);

}
