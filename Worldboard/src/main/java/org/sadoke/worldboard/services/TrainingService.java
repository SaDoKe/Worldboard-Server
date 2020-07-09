package org.sadoke.worldboard.services;

import java.util.Arrays;
import java.util.List;

import org.sadoke.worldboard.request.TrainingEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingService {
	public final static String CAR_DATA= "car";
	public static final String NON_CAR_DATA = "non-car";
	private List<String> types = Arrays.asList(CAR_DATA,NON_CAR_DATA);
	public boolean validateType(String type) {
		return types.contains(type);
	}
	public boolean validateSize(TrainingEntity body) {
		return body.getAccel().size()==3;
	}

}
