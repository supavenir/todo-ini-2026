package sio.spring.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sio.spring.todo.entities.User;
import sio.spring.todo.repositories.UserRepository;

@Service
public class AppService {

	@Autowired
	private UserRepository userRepository;

	public User getCurrentUser() {
		return userRepository.findAll().get(0);
	}
}
