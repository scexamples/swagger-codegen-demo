package com.example.swaggercodegendemo;

import com.swagger.client.codegen.rest.api.StudentControllerApi;
import com.swagger.client.codegen.rest.model.Student;


public class GeneratedApi {
	
	public static void main(String[] args) {
        
		Student student = new Student();
		student.setId(4L);
		student.setName("Sam");
		student.setZipCode("98005");
	
		StudentControllerApi apiInstance = new StudentControllerApi();
		Student response = (Student) apiInstance.createStudentUsingPOST(student);
		System.out.println(response);
    }

}
