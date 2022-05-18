package in.ashokit.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="USER_ACCOUNTS")
public class UserAccountEntity {
	
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="FIRST_NAME")
	private String fname;
	
	@Column(name="LAST_NAME")
	private String lname;
	
	@Column(name="USER_EMAIL",unique=true)
	private String email;
	
	@Column(name="USER_PWD")
	private String pazzword;
	
	@Column(name="USER_MOBILE")
	private Long phno;
	
	@Column(name="DOB")
	private LocalDate dob;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="CITY_ID")
	private Integer cityId;

	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="ACC_STATUS")
	private String accStatus;
	
	@Column(name="CREATE_DATE",updatable=false)
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name="UPDATE_DATE",insertable=false)
	@UpdateTimestamp
	private LocalDate updateDate;

}
