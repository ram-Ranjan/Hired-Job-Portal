package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resumeId;
	@NotBlank(message = "filePath shouldn't be blank")
	@NotNull(message = "filePath shouldn't be null")
	private String filePath;
	@DateTimeFormat
	private LocalDateTime uploadDateTime;

	@OneToMany(mappedBy = "resume")
	private JobApplication jobApplicantion;
	
}


