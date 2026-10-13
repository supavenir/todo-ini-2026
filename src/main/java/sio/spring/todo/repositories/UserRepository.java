package sio.spring.todo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import sio.spring.todo.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
