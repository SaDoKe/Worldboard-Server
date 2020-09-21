package org.sadoke.worldboard.repositories;

import org.sadoke.worldboard.request.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Double> {
}
