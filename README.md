This example demonstrates the use of:  
(1) Swagger for documentation of REST APIs  
(2) Swagger UI to visualize and interact with the API's resources  
(3) Swagger Codegen to generate a REST Client  
(4) Generated REST Client to interact with the APIs  

- It uses a simple REST API project developed using Spring Boot, Maven, Spring Data JPA, and an embedded H2 database.  
- The project has one JPA entity 'Student', a 'StudentRepository', and one REST controller 'StudentController'.  
- On application startup, 'data.sql' is used to seed the 'Student' table.  
- 'springfox-boot-starter' and 'springfox-swagger-ui' dependencies for Swagger are declared in the pom.xml file.  

# 1. Swagger for documentation and 2. Swagger UI for visualizing and testing

On application startup, the following Swagger endpoints can be accessed:
  
  - http://localhost:8080/v2/api-docs for viewing the Swagger documentation and for generating swagger.json that will be used as the source for Swagger Codegen. 
  
  - http://localhost:8080/swagger-ui.html for viewing the Swagger UI and for testing the application endpoints.

#### Swagger UI displays a list of all Controllers  

![img1](https://user-images.githubusercontent.com/15854708/210188228-1186965f-9b5b-480e-9918-fdf8463e6dbe.jpg)

#### Expanding the 'StudentController' link displays a list of all available Request methods.

![img2](https://user-images.githubusercontent.com/15854708/210188232-1a87caf6-89d5-4153-9a89-62a3c75dcbaf.JPG)
  
#### - Execute the 'GET' request to fetch all students. 

![img3getAll](https://user-images.githubusercontent.com/15854708/210188237-c48ee2d2-c4ee-47f7-bc3e-47faf76dc9f9.JPG)

##### The request executes with a Server response code 200 (OK) and the response body displays all students currently in the db.

#### - Click on 'POST' and then the 'Model' tab.  

![img4add](https://user-images.githubusercontent.com/15854708/210188241-31c24cb8-9172-4e65-b811-1ff5d614b60c.JPG)

##### Details of the 'Student' model including required attributes (based on annotations) are displayed.

#### - Click on 'POST' and then on the 'Edit' tab.

![img5](https://user-images.githubusercontent.com/15854708/210188242-fcd5b15e-6c84-49ce-924b-aecb1ed25933.JPG)

#### - Edit the example values and 'POST' the new Student (id: 3) by clicking 'Execute'

![img6afterexecpost](https://user-images.githubusercontent.com/15854708/210188244-3b73f70b-5965-4127-8573-4aabae9e9996.JPG)

##### Server returns a response code of 201 (Created) and a link to the new resource in the response header (http://localhost:8080/students/3)

#### - Navigate to the link to verify that the new resource was created  successfully. 

![img8newresource](https://user-images.githubusercontent.com/15854708/210188248-3a7b3276-43c6-40fc-a879-3ac3581f858c.JPG)

#### - Execute a 'GET' request to '/students' to further verify that the POST request executed successfully.  

![img7getafterpost](https://user-images.githubusercontent.com/15854708/210188245-a1676ce2-01fc-43e1-8893-edca9b46946a.JPG)

# 3. Swagger Codegen for generating the REST Client  

#### Steps for client code generation:   
(i) Save the swagger.json file (generated from http://localhost:8080/v2/api-docs) in src/main/resources    
(ii) Add the 'swagger-codegen-maven-plugin' to pom.xml  
(iii) Run mvn clean generate-sources  

Once the code generation is complete, the plugin generates a client 'gradle' project in a new folder 'target/generated-sources'.  
The generated client also includes a pom.xml and a README.md  

![img9gradle](https://user-images.githubusercontent.com/15854708/210188249-4528c645-5024-47e8-9af0-ab35ae0bd900.JPG)

#### Verifying 'basepath' in the generated code and creating JAR files:  
(i) The generated 'ApiClient.java' includes the URL for establishing a HTTP client connection as the 'basePath'. The 'basepath' value in the generated code was 'https://localhost:8080'. This was updated to 'http://localhost:8080'  
(ii) Run mvn clean install  

##### Note the messages in the terminal:

[INFO] Installing C:\Users\demos\eclipse-workspace\swagger-codegen-demo\target\generated-sources\target\swagger-java-client-1.0.0.jar to C:\Users\demos\.m2\repository\io\swagger\swagger-java-client\1.0.0\swagger-java-client-1.0.0.jar  
[INFO] Installing C:\Users\demos\eclipse-workspace\swagger-codegen-demo\target\generated-sources\pom.xml to C:\Users\demos\.m2\repository\io\swagger\swagger-java-client\1.0.0\swagger-java-client-1.0.0.pom  
[INFO] Installing C:\Users\demos\eclipse-workspace\swagger-codegen-demo\target\generated-sources\target\swagger-java-client-1.0.0-tests.jar to  
C:\Users\demos\.m2\repository\io\swagger\swagger-java-client\1.0.0\swagger-java-client-1.0.0-tests.jar  
[INFO] ------------------------------------------------------------------------  
[INFO] BUILD SUCCESS  
[INFO] ------------------------------------------------------------------------  
[INFO] Total time:  3.063 s  
[INFO] Finished at: 2022-12-23T16:12:22-08:00  
[INFO] ------------------------------------------------------------------------  

# 4. Using the generated REST Client  
To use the generated REST Client in any project, include the generated JAR as a dependency  
Per instructions in the README.md, add the 'swagger-java-client' dependency to the pom.xml  

#### Steps for using the generated REST Client from within the current project:
(i) Configure the build-path to include the 'generated-sources' folder as a source folder.  
The generated code includes 'StudentControllerApi.java' which includes methods corresponding to all endpoints exposed in the original project (that was the source of swagger.json).  
(ii) Create a new class 'GeneratedApi.java' with a 'main' method.  
(iii) Create a new Student (example, name: Sam and zip code: 98005).  
(iv) Create an instance of 'StudentControllerApi' and call its 'createStudentUsingPOST()' method and pass the student (created in the previous step) as the argument.

![img10GeneratedApi](https://user-images.githubusercontent.com/15854708/210188254-c7d10c91-b35a-4dc9-bfaa-e8fcca1de375.JPG)  

##### Message in the terminal indicates that the resource was created successfully (response code 201).
