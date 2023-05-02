package com.ty.HiredApp.Config;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private int status;
	private String message;
	private Object data;

}
