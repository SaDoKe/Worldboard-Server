package org.sadoke.worldboard.repositories;

import org.sadoke.worldboard.request.TrainingEntity;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<TrainingEntity, Integer> {
    TrainingEntity findById(Long id);
}