package com.example.swaggercodegendemo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class Student implements Serializable {
	
	private static final long serialVersionUID = -8632911029029472859L;

	//SpringFox can generate Swagger documentation based on JSR-303 (Bean Validation) annotations automatically
	@ApiModelProperty(notes = "Unique identifier of the student", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ApiModelProperty(notes = "Name of the student", example = "Bill", required = true, position = 1)
    @NotBlank
    private String name;

	@ApiModelProperty(notes = "ZipCode of the student", example = "60612", required = true, position = 2)
    @Size(min=5, max=5, message = "ZipCode must be 5 digits")
    private String zipCode;

    public Student() {
        super();
    }

    public Student(Long id, String name, String zipCode) {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}