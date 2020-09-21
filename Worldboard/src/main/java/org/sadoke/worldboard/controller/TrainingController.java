package org.sadoke.worldboard.controller;

import java.util.Date;
import java.util.List;

import org.sadoke.wordboard.response.TrainingResponse;
import org.sadoke.worldboard.request.TrainingEntity;
import org.sadoke.worldboard.services.KeyService;
import org.sadoke.worldboard.services.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/training")
@Slf4j
public class TrainingController {

	private static final String CSV_SEPERATOR = ",";
	private static final int ACCEL_AMOUNT = 6;
	private final TrainingService trainingService;
	private final KeyService keyService;

	@PostMapping("/learn/{type}/{pw}")
	public ResponseEntity<TrainingResponse> saveLearningData(@PathVariable String type, @PathVariable String pw,
			@RequestBody TrainingEntity body) {
		if (keyService.validateMainKey(pw))
		return new ResponseEntity<>(
				new TrainingResponse("Send the API-KEY",
						body.getAccel().size()),
				HttpStatus.UNAUTHORIZED);
		if (!trainingService.validateType(type))
			return new ResponseEntity<>(new TrainingResponse("Type: {} is invalid. Please choose {} or {} as a type.",
					type, TrainingService.CAR_DATA, TrainingService.NON_CAR_DATA), HttpStatus.BAD_REQUEST);
		if (!trainingService.validateSize(body))
			return new ResponseEntity<>(
					new TrainingResponse("Size of acceleration arrays needs to be exactly {}. Sent body has {}",
							ACCEL_AMOUNT,
							body.getAccel().size()),
					HttpStatus.BAD_REQUEST);
		body.setType(type);
		trainingService.save(body);
		return new ResponseEntity<>(new TrainingResponse("Data Arrived"), HttpStatus.OK);
	}

	@PostMapping("/classify/{pw}")
	public ResponseEntity<TrainingResponse> classifyData(@PathVariable Double pw,
			@RequestBody TrainingEntity body) {

		if (keyService.validateApiKey(pw))
			return new ResponseEntity<>(new TrainingResponse("Send the API-KEY", body.getAccel().size()),
					HttpStatus.UNAUTHORIZED);
		if (!trainingService.validateSize(body))
			return new ResponseEntity<>(
					new TrainingResponse("Size of acceleration arrays needs to be exactly {}. Sent body has {}",
							ACCEL_AMOUNT, body.getAccel().size()),
					HttpStatus.BAD_REQUEST);
		body.setD(new Date());
		log.info("Recieve following body {} by pw {}", body);
		if (trainingService.handleNewData(body, pw))
			return new ResponseEntity<>(new TrainingResponse("s"), HttpStatus.OK);

		return new ResponseEntity<>(new TrainingResponse("Data Arrived"), HttpStatus.OK);
	}

	@GetMapping("/learn/example")
	public ResponseEntity<String> getExample() {
		return new ResponseEntity<String>(
				"{\"id\":null,\"accel\":[{\"id\":null,\"accelX\":42.0,\"accelY\":42.0,\"accelZ\":42.0},{\"id\":null,\"accelX\":123.0,\"accelY\":456.0,\"accelZ\":789.0},{\"id\":null,\"accelX\":666.0,\"accelY\":666.0,\"accelZ\":666.0}],\"movementVector\":20.0}",
				HttpStatus.OK);
	}


	@GetMapping("/learn/full")
	public ResponseEntity<String> getFull() {
		List<TrainingEntity> trainingData = trainingService.getTraining();
		StringBuilder sb = new StringBuilder("");
		sb.append("ID;MOVEMENT_VECTOR;");
		for (int i = 0; i < ACCEL_AMOUNT; i++) {
			sb.append("X_ACCEL_" + i + CSV_SEPERATOR);
			sb.append("Y_ACCEL_" + i + CSV_SEPERATOR);
			sb.append("Z_ACCEL_" + i + CSV_SEPERATOR);
		}
		sb.append("TYPE\n");
		for (TrainingEntity te : trainingData) {
			sb.append(te.getId()).append(CSV_SEPERATOR).append(te.getMovementVector()).append(CSV_SEPERATOR);
			for (int i = 0; i < ACCEL_AMOUNT; i++) {
				sb.append(te.getAccel().get(i).getAccelX()).append(CSV_SEPERATOR)
						.append(te.getAccel().get(i).getAccelY()).append(CSV_SEPERATOR)
						.append(te.getAccel().get(i).getAccelZ()).append(CSV_SEPERATOR);
			}
			sb.append(te.getType()).append("\n");
		}
		
		return new ResponseEntity<String>(
				sb.toString(), HttpStatus.OK);
	}

	@GetMapping("/learn/v2/full")
	public ResponseEntity<String> getFull2() {
		List<TrainingEntity> trainingData = trainingService.getTraining();
		StringBuilder sb = new StringBuilder("");
		sb.append("ID,MOVEMENT_VECTOR,");
			sb.append("X_ACCEL_AVERAGE" + CSV_SEPERATOR);
			sb.append("Y_ACCEL_AVERAGE" + CSV_SEPERATOR);
			sb.append("Z_ACCEL_AVERAGE" + CSV_SEPERATOR);
		sb.append("TYPE\n");
		for (TrainingEntity te : trainingData) {
			sb.append(te.getId()).append(CSV_SEPERATOR).append(te.getMovementVector() * 1000).append(CSV_SEPERATOR);
			double accelx = 0;
			double accely=0;
			double accelz=0;
			for (int i = 0; i < ACCEL_AMOUNT; i++) {
				accelx+=te.getAccel().get(i).getAccelX();
				accely += te.getAccel().get(i).getAccelY();
				accelz += te.getAccel().get(i).getAccelZ();
			}
			sb.append(accelx + CSV_SEPERATOR).append(accely + CSV_SEPERATOR).append(accelz + CSV_SEPERATOR)
					.append(te.getType()).append("\n");
		}

		return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
	}
}