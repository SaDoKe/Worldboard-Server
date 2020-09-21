package org.sadoke.wordboard.response;

import org.sadoke.worldboard.request.MessageWriteEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class MessageClickResponse {
	private String message;
	private MessageWriteEntity writeEntity;

	public MessageClickResponse(MessageWriteEntity writeEntity, String message, Object... args) {
		for (Object arg : args) {
			message.replaceFirst("\\{\\}", arg.toString());
		}
		this.message = message;
		this.writeEntity = writeEntity;
	}
}
