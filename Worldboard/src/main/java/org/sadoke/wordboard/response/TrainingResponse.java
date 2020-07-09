package org.sadoke.wordboard.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TrainingResponse {

	private String message;

	public TrainingResponse(String message, Object... args) {
		for (Object arg : args) {
			message = message.replace("{}", arg.toString());
		}
		this.message = message;
	}

}
