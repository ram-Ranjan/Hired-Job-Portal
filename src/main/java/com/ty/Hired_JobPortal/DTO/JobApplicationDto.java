package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class JobApplicationDto {

	private int jobApplicationId;
	private LocalDateTime appliedDate;
	private int noticePeriodInDays;

}
