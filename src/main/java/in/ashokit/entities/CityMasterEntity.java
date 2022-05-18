package in.ashokit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CITY_MASTER")
public class CityMasterEntity {

	@Column(name = "CITY_NAME")
	private String cityName;
    
	@Id
	@Column(name = "CITY_ID")
	private Integer cityId;

	@Column(name = "STATE_ID")
	private Integer stateId;

}
