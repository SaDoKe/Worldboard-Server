package org.sadoke.wordboard.response;

import java.util.List;

import org.sadoke.worldboard.request.MessageWriteEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * {"message":"hi", "messagePoints":[{"latitude":2222,"longitude":222,
 * id:2},{"latitude":2222,"longitude":222,
 * id:2},{"latitude":2222,"longitude":222, id:2}]}
 * 
 * @author Saied Sadegh
 *
 */
public class FindMessageResponse {
	private String message;

	private List<MessageWriteEntity> messagePoints;
	private String action;

	public FindMessageResponse(List<MessageWriteEntity> messagePoints, String action, String message, Object... args) {
		for (Object arg : args) {
			message.replaceFirst("\\{\\}", arg.toString());
		}
		this.message = message;
		this.messagePoints = messagePoints;
	}

}
