package com.appdevelopers.app.ws.exception;

import java.time.LocalDateTime;

public class ErrorMessage {

	private LocalDateTime timestamp;
	private String errorMesaage;

	public ErrorMessage() {

	}

	public ErrorMessage(LocalDateTime timestamp, String errorMesaage) {
		super();
		this.timestamp = timestamp;
		this.errorMesaage = errorMesaage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorMesaage() {
		return errorMesaage;
	}

	public void setErrorMesaage(String errorMesaage) {
		this.errorMesaage = errorMesaage;
	}

}
