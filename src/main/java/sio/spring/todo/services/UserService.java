package sio.spring.todo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sio.spring.todo.entities.User;
import sio.spring.todo.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository uRepo;
	private final PasswordEncoder pEncoder;

	public UserService(UserRepository uRepo, PasswordEncoder pEncoder) {
		this.uRepo = uRepo;
		this.pEncoder = pEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return uRepo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu"));
	}

	public void encodePassword(User user) {
		String pwd = user.getPassword();
		if (pwd.length() < 20 && !pwd.startsWith("$")) {
			user.setPassword(pEncoder.encode(pwd));
		}
	}

	public User createUser(String login, String password) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		encodePassword(user);
		return uRepo.save(user);
	}
}
