package org.sadoke.worldboard.controller;

import org.sadoke.wordboard.response.TrainingResponse;
import org.sadoke.worldboard.request.TrainingEntity;
import org.sadoke.worldboard.services.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/training")
public class TrainingController {

	private final TrainingService trainingService;

	@PostMapping("/learn/{type}")
	public ResponseEntity<TrainingResponse> saveLearningData(@PathVariable String type,
			@RequestBody TrainingEntity body) {
		
		if (!trainingService.validateType(type))
			return new ResponseEntity<>(new TrainingResponse("Type: {} is invalid. Please choose {} or {} as a type.",
					type, TrainingService.CAR_DATA, TrainingService.NON_CAR_DATA), HttpStatus.BAD_REQUEST);
		if (!trainingService.validateSize(body))
			return new ResponseEntity<>(
					new TrainingResponse("Size of acceleration arrays needs to be exactly 3. Sent body has {}",
							body.getAccel().size()),
					HttpStatus.BAD_REQUEST);
		
		
		return new ResponseEntity<>(new TrainingResponse("Data Arrived"), HttpStatus.OK);
	}

}
