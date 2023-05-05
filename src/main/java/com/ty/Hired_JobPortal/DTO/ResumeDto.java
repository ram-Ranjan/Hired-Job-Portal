package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResumeDto {
	private int resumeId;
	private String filePath;
	private LocalDate uploadDate;

}
