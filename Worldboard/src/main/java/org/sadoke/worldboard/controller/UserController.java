package org.sadoke.worldboard.controller;

import org.sadoke.wordboard.response.UserResponse;
import org.sadoke.worldboard.request.UserEntity;
import org.sadoke.worldboard.services.KeyService;
import org.sadoke.worldboard.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final KeyService keyService;
	@PostMapping("/create/{pw}")
	public ResponseEntity<UserResponse> createUser(@PathVariable String pw) {
		if (keyService.validateMainKey(pw))
			return new ResponseEntity<UserResponse>(new UserResponse(0.0,"Send the API-KEY"),
					HttpStatus.UNAUTHORIZED);
		UserEntity userEntity = userService.createUser();
		return  new ResponseEntity<UserResponse>(new UserResponse(userEntity.getApi(),"Created User"),
				HttpStatus.ACCEPTED);

	}

}
