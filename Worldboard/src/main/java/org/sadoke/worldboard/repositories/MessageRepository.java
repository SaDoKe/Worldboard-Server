package org.sadoke.worldboard.repositories;

import java.util.List;

import org.sadoke.worldboard.request.MessageWriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageWriteEntity, Long> {
	@Query("SELECT m FROM MessageWriteEntity m where (abs(m.position.longitude-?1)+abs(m.position.lattitude-?2))<2")
	List<MessageWriteEntity> findMessages(double longitude, double lattitude);


}
