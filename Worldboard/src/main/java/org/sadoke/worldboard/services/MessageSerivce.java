package org.sadoke.worldboard.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.sadoke.worldboard.repositories.MessageRepository;
import org.sadoke.worldboard.request.MessageWriteEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSerivce {

	private final MessageRepository messageRepos;
	private final EntityManager entityManager;

	public MessageWriteEntity getMessage(long id) {
		MessageWriteEntity message = messageRepos.getOne(id);
		return message;

	}

	public List<MessageWriteEntity> getNextMessages(double longitude, double lattitude) {
		List<MessageWriteEntity> messages = messageRepos.findMessages(longitude, lattitude);
		log.info("Messages: {}", messages);
		return messages;

	}

	public void writeMessage(MessageWriteEntity entity) {
		log.info("Message to write: {}", entity);
		messageRepos.save(entity);
	}
}
