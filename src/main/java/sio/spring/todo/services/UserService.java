package sio.spring.todo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import sio.spring.todo.entities.User;
import sio.spring.todo.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<User> findUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}
