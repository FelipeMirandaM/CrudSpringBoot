package cl.tarea.crudspringboot.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

	private String message;
	private int status;
	private List<String> detail = new ArrayList<String>();

	public ErrorResponse(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void addError(String error) {
		detail.add(error);
	}

	public List<String> getDetail() {
		return detail;
	}

	public void setDetail(List<String> detail) {
		this.detail = detail;
	}

}
