package org.sadoke.worldboard.request;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "Training")
public class TrainingEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private List<Accelerator> accel;
	private double movementVector;
	@Data
	@NoArgsConstructor
	class Accelerator{
		private double accelX;
		private double accelY;
		private double accelZ;
	}
	
}
