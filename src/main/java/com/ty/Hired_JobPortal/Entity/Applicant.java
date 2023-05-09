package com.ty.Hired_JobPortal.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Applicant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicantId;
	@NotBlank(message = "Applicant FirstName shouldn't be blank")
	@NotNull(message = "Applicant FirstName shouldn't be null")
	private String applicantFirstName;
	@NotBlank(message = "Applicant Last Name shouldn't be blank")
	@NotNull(message = "Applicant Last Name shouldn't be null")
	private String applicantLastName;
	@Email(message = "Email doesn't seems to be in correct format")
	private String applicantEmail;
	@NotBlank(message = "Admin Password should not be Empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase, one uppercase letter, one special character and must be at least 8 characters long")
	private String applicantPassword;
	@Min(value = 6000000000l, message = "Customer contact must be ten digits and start with 6,7,8 or  9")

    @Max(value = 9999999999L, message = "Customer contact must be ten digits and start with 6,7,8 or  9")
	private long applicantContactInfo;
	@NotBlank(message = "Applicant Gender shouldn't be blank")
	@NotNull(message = "Applicant Gender shouldn't be null")
	private String applicantGender;

	@ManyToMany(mappedBy = "applicants", cascade = CascadeType.ALL)
	private List<Skill> skill;

	@OneToOne(cascade = CascadeType.ALL) // uni
	private Resume resume;
	
	@OneToMany(cascade = CascadeType.ALL) //uni
	private List<JobApplication> jobApplication;

	@OneToMany(mappedBy = "applicant")
	private List<Notification> notification;
	
	@ManyToMany(mappedBy = "applicant",cascade = CascadeType.ALL)
	private List<Job> job;
}
