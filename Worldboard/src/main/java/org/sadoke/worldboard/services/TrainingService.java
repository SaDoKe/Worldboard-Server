package org.sadoke.worldboard.services;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.sadoke.worldboard.repositories.TrainingRepository;
import org.sadoke.worldboard.request.TrainingEntity;
import org.sadoke.worldboard.request.UserEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingService {
	public final static String CAR_DATA= "car";
	public static final String NON_CAR_DATA = "non-car";
	private List<String> types = Arrays.asList(CAR_DATA,NON_CAR_DATA);
	private final TrainingRepository trainingRepository;
	private final UserService userService;
	private final WekaWrapper wekaWrapper;
	public boolean validateType(String type) {
		return types.contains(type);
	}

	public boolean validateSize(TrainingEntity body) {
		return body.getAccel().size() == 6;
	}
	public void save(TrainingEntity body) {
		trainingRepository.save(body);
	}

	public void classify(TrainingEntity body, UserEntity user) {

		long diffInMillies = Math.abs(body.getD().getTime() - user.getLastUpdate().getTime());
		long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (body.getMovementVector() == 0) {
			if (diff > 1)
			userService.reset(user);
			return;
		} else
		if (wekaWrapper.classify(body)) {
			if (diff > 1) {
				userService.reset(user);
			}
			userService.countUpUser(user);
			if (user.getCounter() > 5)
				userService.updateMovementToCar(user);
		}

	}

	public List<TrainingEntity> getTraining() {

		return trainingRepository.findAll();
	}

	public boolean handleNewData(TrainingEntity body, Double pw) {
		UserEntity user = userService.getUser(pw);
		int oldStatus = user.getStatus();
		classify(body, user);
		userService.commit(user);
		int newStatus = user.getStatus();
		return newStatus == 2 && newStatus != oldStatus;

	}
}
