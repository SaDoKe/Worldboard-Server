package org.sadoke.worldboard.controller;

import java.util.Collections;

import org.sadoke.wordboard.response.FindMessageResponse;
import org.sadoke.wordboard.response.MessageWriteResponse;
import org.sadoke.worldboard.request.MessageWriteEntity;
import org.sadoke.worldboard.request.MessageWriteEntity.GPS;
import org.sadoke.worldboard.services.KeyService;
import org.sadoke.worldboard.services.MessageSerivce;
import org.sadoke.worldboard.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/message")
@Slf4j
public class MessageController {

	private final ObjectMapper mapper;
	private final KeyService keyService;
	private final MessageSerivce messageService;
	private final UserService userService;

	@GetMapping("/next/{pw}")
	public ResponseEntity<FindMessageResponse> getNextMessages(@PathVariable Double pw, @RequestParam double longitude,
			@RequestParam double lattitude) {
		if (keyService.validateApiKey(pw))
			return new ResponseEntity<FindMessageResponse>(
					new FindMessageResponse(Collections.emptyList(), "non-action", "Send the API-KEY"),
					HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<FindMessageResponse>(
				new FindMessageResponse(messageService.getNextMessages(longitude, lattitude), "non-action",
						"Sends message"),
				HttpStatus.OK);

	}

	@PostMapping("/create/{pw}")
	public ResponseEntity<MessageWriteResponse> writeMessage(@PathVariable Double pw,
			@RequestBody MessageWriteEntity entity) {
		if (keyService.validateApiKey(pw))
			return new ResponseEntity<MessageWriteResponse>(new MessageWriteResponse("SEND API-KEY!!!"),
					HttpStatus.UNAUTHORIZED);
		if (entity.getMessage() == null || entity.getMessage().isEmpty()) {
			entity.setMessage("example");
			GPS gps = new GPS();
			gps.setLattitude(2.0);
			gps.setLongitude(2.0);
			entity.setPosition(gps);
			entity.setId(0L);
			return new ResponseEntity<MessageWriteResponse>(
					new MessageWriteResponse(entity ),
					HttpStatus.BAD_REQUEST);
		}
		entity.setAuthor(userService.getUser(pw));
		messageService.writeMessage(entity);
		userService.updateAuthor(pw);
		return new ResponseEntity<MessageWriteResponse>(new MessageWriteResponse(
				"DONE"), HttpStatus.OK);
	}

}
