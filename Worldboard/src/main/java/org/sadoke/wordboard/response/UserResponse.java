package org.sadoke.wordboard.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserResponse {

	private String message;
	private double key;

	public UserResponse(Double key, String message, Object... args) {
		this(message, args);
		this.key = key;
	}

	public UserResponse(String message, Object... args) {
		for (Object arg : args) {
			message = message.replaceFirst("\\{\\}", arg.toString());
		}
		this.message = message;
	}

}
