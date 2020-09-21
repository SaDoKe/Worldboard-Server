package org.sadoke.wordboard.response;

import org.sadoke.worldboard.request.MessageWriteEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageWriteResponse {
	private String message;
	private MessageWriteEntity example;

	public MessageWriteResponse(String message, Object... args) {
		for (Object arg : args) {
			message.replaceFirst("\\{\\}", arg.toString());
		}
		this.message = message;
	}

	public MessageWriteResponse(MessageWriteEntity example) {
		this.example = example;
		this.message = "Here an example";
	}

}
