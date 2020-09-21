package org.sadoke.worldboard.request;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TBL_MOVEMENT")
public class TrainingEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private List<Accelerator> accel;
	@Column
	private double movementVector;
	@Column
	private String type;

	@Column
	private Date d;

	@Data
	@NoArgsConstructor
	@Entity
	@Table(name = "TBL_ACCELERATOR")
	public
	static
	class Accelerator{
		@Id
		@Column
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		@Column
		private double accelX;
		@Column
		private double accelY;
		@Column
		private double accelZ;

	}
	
}
