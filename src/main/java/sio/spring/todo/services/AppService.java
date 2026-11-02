package sio.spring.todo.services;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class AppService {

	private final HttpSession session;

	public AppService(HttpSession session) {

		this.session = session;
	}

	public void logout() {
		session.invalidate();
	}
}
