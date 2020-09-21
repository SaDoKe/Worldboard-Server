package org.sadoke.worldboard.request;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TBL_MESSAGE")
@Entity
@Data
@NoArgsConstructor
public class MessageWriteEntity {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String message;
	@ManyToOne(cascade = CascadeType.ALL)
	private UserEntity author;

	@OneToOne(cascade = CascadeType.ALL)
	private GPS position;

	@Table(name = "TBL_GPS")
	@Entity
	@Data
	@NoArgsConstructor
	// {longitude:20202020,latitude:2209292,id:2222}
	public static class GPS {
		@Column
		private Double longitude;
		@Column
		private Double lattitude;
		@Column
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
	}

}
