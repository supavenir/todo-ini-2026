package sio.spring.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import sio.spring.todo.entities.User;
import sio.spring.todo.repositories.UserRepository;

@Service
public class AppService {

	private final HttpSession session;

	public AppService(HttpSession session) {
		this.session = session;
	}

	public User getCurrentUser() {
		Object o= session.getAttribute("currentUser");
		if(o!=null) {
			return (User) o;
		}
		return null;
	}
	
	
	public void logout() {
		session.invalidate();
	}
}
