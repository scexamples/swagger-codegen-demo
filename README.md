This example uses a REST API project developed using Spring Boot, Spring Data JPA, Maven, and an embedded H2 database. The project has one JPA entity 'Student', a 'StudentRepository', and one REST controller 'StudentController'. On application startup, data.sql seeds the Student table with data. For generating Swagger documentation and for Swagger UI, add the dependencies for springfox-boot-starter and springfox-swagger-ui to the pom.xml file.

# Swagger Endpoints

On application startup, the following Swagger endpoints can be accessed:
  
  - http://localhost:8080/v2/api-docs for viewing the Swagger documentation and for generating swagger.json that will be used as the source for Swagger Codegen. 
  
  - http://localhost:8080/swagger-ui.html for viewing the Swagger UI and for testing the application endpoints.


![img1](https://user-images.githubusercontent.com/15854708/210186909-1868ad73-7da9-4dda-b549-46a7efa2fe89.JPG)


Swagger UI displays a list of all available Request methods.

![img2](https://user-images.githubusercontent.com/15854708/210186911-27aa4b33-7f3c-4558-947b-d69b6c1df898.JPG)


Click on a method to see details and test it. Executing the GET request to fetch all students displays the Server response code (200) and the response body displays the two students added to the db on startup.

![img3getAll](https://user-images.githubusercontent.com/15854708/210186915-0df0f90d-cae6-4f6f-a2f1-94e8cb420622.JPG)


Click on the POST method and then the 'Model' tab to see details of the 'Student' model including required attributes (based on annotations).

![img4add](https://user-images.githubusercontent.com/15854708/210186917-175457cb-7361-4df8-ba00-a7d1d76b73a7.JPG)


In the POST method, click on the 'Edit' tab to edit the example values and POST and new Student (id: 3) and click 'Execute'.

![img5](https://user-images.githubusercontent.com/15854708/210186919-d2a00372-2e1a-411a-ac23-08226c24e5b4.JPG)


Server returns a response code of 201 (Created) and a link to the newly created resource in the header (http://localhost:8080/students/3)

![img6afterexecpost](https://user-images.githubusercontent.com/15854708/210186925-98ea6681-20d9-475c-81c5-5c1e5ebc37cf.JPG)

Navigate to the link to verify the new resource can be verified 

![img8newresource](https://user-images.githubusercontent.com/15854708/210186929-3fadf269-5e6a-4ab7-a29b-ecc7c7dfdde5.JPG)

Executing a GET request to /allStudents to verifies that the POST request executed successfully

![img7getafterpost](https://user-images.githubusercontent.com/15854708/210186926-7bf05aa8-ffb3-4a40-98ed-b2b028bbf362.JPG)

-----------

Steps for client code generation: 
(i) save the swagger.json file in src/main/resources (generated from http://localhost:8080/v2/api-docs) 
(ii) add the swagger-codegen-maven-plugin to pom.xml
(iii) run mvn clean generate-sources

Once the code generation is completed, the plugin generates a client 'gradle' project in the new folder a target/generated-sources
The generated client also includes a pom.xml and a README.md

![img9gradle](https://user-images.githubusercontent.com/15854708/210186937-777563e5-4293-4e05-ae37-fbcf47e429bc.JPG)  


## Update the 'basepath' in the generated ApiClient.java
ApiClient.java includes the URL for establishing a HTTP client connection as the 'basePath'. The basepath value in the generated code was 'https://localhost:8080'. This was updated to 'http://localhost:8080'.

Follow instructions in the README.md and add the swagger-java-client dependency to the pom.xml.
Run mvn clean install
Note the messages in the terminal:

[INFO] Installing C:\Users\demos\eclipse-workspace\swagger-codegen-demo\target\generated-sources\target\swagger-java-client-1.0.0.jar to C:\Users\demos\.m2\repository\io\swagger\swagger-java-client\1.0.0\swagger-java-client-1.0.0.jar
[INFO] Installing C:\Users\demos\eclipse-workspace\swagger-codegen-demo\target\generated-sources\pom.xml to C:\Users\demos\.m2\repository\io\swagger\swagger-java-client\1.0.0\swagger-java-client-1.0.0.pom
[INFO] Installing C:\Users\demos\eclipse-workspace\swagger-codegen-demo\target\generated-sources\target\swagger-java-client-1.0.0-tests.jar to C:\Users\demos\.m2\repository\io\swagger\swagger-java-client\1.0.0\swagger-java-client-1.0.0-tests.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.063 s
[INFO] Finished at: 2022-12-23T16:12:22-08:00
[INFO] ------------------------------------------------------------------------

# Using the generated REST Client
To use the generated REST Client in any project, include the generated JAR as a dependency.
     
## Example use of REST Client
For testing the generated REST Client from within the current project, configure the build-path to include the 'generated-sources' folder as a source folder.   
The generated code includes 'StudentControllerApi.java' which includes methods corresponding to all endpoints exposed in the original project (that was the source of swagger.json) 
Create a new class 'GeneratedApi.java' with a 'main' method. 
Create a new Student (example, name: Sam and zip code: 98005).
Create an instance of StudentControllerApi and call its createStudentUsingPOST() method and pass the new student as the argument.

![img10GeneratedApi](https://user-images.githubusercontent.com/15854708/210186941-b54ccfa4-4166-418b-a270-9880a9382b69.JPG)

Message in the terminal indicates that the resource was created successfully (response code 201).
