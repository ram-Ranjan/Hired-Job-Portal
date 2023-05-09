
package com.ty.Hired_JobPortal.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employerId;
	@NotBlank(message = "Employer Name shouldn't be blank")
	@NotNull(message = "Employer Name shouldn't be null")
	private String employerName;
	@Email(message="Employer Email doesn't seems to be in correct format")
	private String employerEmail;
	@NotBlank(message = "Employer Password should not be Empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase, one uppercase letter, one special character and must be at least 8 characters long")
	private String employerPassword;
	@Min(value = 6000000000l, message = "Customer contact must be ten digits and start with 6,7,8 or  9")
    @Max(value = 9999999999L, message = "Customer contact must be ten digits and start with 6,7,8 or  9")
	private long employerContactInfo;

	@OneToMany(mappedBy = "employer")
	@JsonIgnore
	private List<Job> job;

	@OneToMany(mappedBy = "employer")
	private List<Notification> notification;

}
