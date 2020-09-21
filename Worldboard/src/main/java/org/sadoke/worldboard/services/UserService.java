package org.sadoke.worldboard.services;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.sadoke.worldboard.repositories.UserRepository;
import org.sadoke.worldboard.request.UserEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int DRIVING = 2;
	private final UserRepository userRepository;
	private final EntityManager em;

	public UserEntity createUser() {
		UserEntity user = new UserEntity();
		user.setStatus(IDLE);
		user.setLastUpdate(new Date());
		double api = Math.random() * 100000000 + 1;
		while (userRepository.existsById(api))
			api = Math.random() * 100000000 + 1;
		user.setApi(api);
		userRepository.save(user);
		return user;

	}

	public UserEntity getUser(double id) {
		return userRepository.getOne(id);
	}

	@Transactional
	public void updateAuthor(Double pw) {
		UserEntity user = getUser(pw);
		user.setLastUpdate(new Date());
		
	}


	public void countUpUser(UserEntity user) {
		if (user.getCounter() != null) {
			user.setCounter(user.getCounter() + 1);
			log.info("Counting up user {}", user);

		}
		else
			user.setCounter(1);

	}


	public void reset(UserEntity user) {
		user.setStatus(0);
		user.setCounter(0);

	}


	public void updateMovementToCar(UserEntity user) {
		user.setStatus(2);
		user.setCounter(0);

	}

	@Transactional
	public void commit(UserEntity user) {

		log.info("user about to be updated {}", user);
		user.setLastUpdate(new Date());
		log.info("user was updated {}", user);

	}
}
