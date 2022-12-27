package com.example.swaggercodegendemo;

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 564949015033551591L;

	public StudentNotFoundException(String exception) {
        super(exception);
    }

}
