package org.sadoke.worldboard.request;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TBL_USER")
@Entity
@Data
@NoArgsConstructor
public class UserEntity {

	@Column
	@Id
	private Double api;
	@Column
	private Integer status;
	@Column
	private Date lastUpdate;
	@Column
	private Integer counter;

}
