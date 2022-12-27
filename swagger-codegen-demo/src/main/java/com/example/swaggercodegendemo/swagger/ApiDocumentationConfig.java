package com.example.swaggercodegendemo.swagger;


import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Swagger demo",
                version = "V1",
                title = "Swagger demo API",
                contact = @Contact(
                   name = "scexamples", 
                   email = "test@dummy.com", 
                   url = "http://localhost:8080"
                ),
        		license = @License(
                        name = "Apache 2.0", 
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
				)
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "ReadMe", url = "http://localhost:8080")
)

//Meta info about the API that will be included in the documentation
public interface ApiDocumentationConfig {

}
