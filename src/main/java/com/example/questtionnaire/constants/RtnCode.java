package com.example.questtionnaire.constants;

public enum RtnCode  {

	SUCCESSFUL(200, "OK!"),
	QUESTION_PARAM_ERROR(400,"Question_ERROR"),
	QUESTIONNAIRE_PARAM_ERROR(400,"Questionnaire_ERROR"),
	QUESTIONNAIRE_ID_PARAM_ERROR(400,"Questionnaire id param ERROR"),
	QUESTIONNAIRE_ID_NOT_FOUND(404,"Questionnaire id not found"),
	UPDATE_ERROR(400,"update error")
	;

	private int code;
	private String message;
	private RtnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
