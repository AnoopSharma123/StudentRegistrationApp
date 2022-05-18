package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class UserRegForm {

	private Integer userId;

	private String fname;

	private String lname;

	private String email;

	private String pazzword;

	private Long phno;

	private LocalDate dob;

	private String gender;

	private Integer cityId;

	private Integer stateId;

	private Integer countryId;

	private String accStatus;

}
