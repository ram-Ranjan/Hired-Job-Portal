
package com.ty.Hired_JobPortal.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employerId;
	private String employerName;
	private String employerEmail;
	private String employerPassword;
	private long employerContact;

	@OneToMany
	private List<Job> job;

	@OneToMany
	private List<Notification> notification;

}
