This example demonstrates the use of:  
(i) Swagger for documentation of REST APIs  
(ii) Swagger UI to visualize and interact with the API's resources  
(iii) Swagger Codegen to generate a REST Client  
(iv) Use of the generated Client code  

It uses a simple REST API project developed using Spring Boot, Maven, Spring Data JPA, and an embedded H2 database.  
The project has one JPA entity 'Student', a 'StudentRepository', and one REST controller 'StudentController'.  
On application startup, data.sql seeds the Student table with data.  
'springfox-boot-starter' and 'springfox-swagger-ui' dependencies for Swagger are declared in the pom.xml file.  

# Swagger Endpoints

On application startup, the following Swagger endpoints can be accessed:
  
  - http://localhost:8080/v2/api-docs for viewing the Swagger documentation and for generating swagger.json that will be used as the source for Swagger Codegen. 
  
  - http://localhost:8080/swagger-ui.html for viewing the Swagger UI and for testing the application endpoints.

### Swagger UI displays a list of all Controllers  

![img1](https://user-images.githubusercontent.com/15854708/210187247-c0f0a3db-0f93-4957-abba-b47d14efb43d.JPG)

### Expanding the 'StudentController' link displays a list of all available Request methods.

![img2](https://user-images.githubusercontent.com/15854708/210187250-6109298f-3b0c-46f0-af7a-50f413770d40.JPG)  

### Click on a button to see details and test the Request method.  
## Execute the 'GET' request to fetch all students. 

![img3getAll]

### The request executes with a Server response code 200 (OK) and the response body displays all students currently in the db.

## Click on the 'POST' button and then the 'Model' tab.  

![img4add](https://user-images.githubusercontent.com/15854708/210187254-71008d66-5dab-455a-be63-0c1174270556.JPG)

### Details of the 'Student' model including required attributes (based on annotations) are displayed.

## Click the 'POST' button and then on the 'Edit' tab.

![img5](https://user-images.githubusercontent.com/15854708/210187255-7fe9e05b-6918-4fdd-a655-0f1904ceec2f.JPG)

## Edit the example values and 'POST' the new Student (id: 3) by clicking 'Execute'

![img6afterexecpost](https://user-images.githubusercontent.com/15854708/210187257-c9cc7ed8-3c78-4386-b79e-dc7da1cf3cc5.JPG)  

### Server returns a response code of 201 (Created) and a link to the newly created resource in the response header (http://localhost:8080/students/3)

## Navigate to the link to verify that the new resource was created  successfully. 

![img8newresource](https://user-images.githubusercontent.com/15854708/210187262-ee26b31b-06fd-4c96-a717-44d86d4277f7.JPG)

## Execute a 'GET' request to '/students' to further verify that the POST request executed successfully.  

![img7getafterpost](https://user-images.githubusercontent.com/15854708/210187258-48a9e714-97e2-485b-9f7f-844f575fcca6.JPG)


## Steps for client code generation:   
(i) Save the swagger.json file in src/main/resources (generated from http://localhost:8080/v2/api-docs)   
(ii) Add the swagger-codegen-maven-plugin to pom.xml  
(iii) Run mvn clean generate-sources  

Once the code generation is complete, the plugin generates a client 'gradle' project in a new folder 'target/generated-sources'.  
The generated client also includes a pom.xml and a README.md  

![img9gradle](https://user-images.githubusercontent.com/15854708/210187265-b243f1b4-cd28-4526-a800-2c2e112afd03.JPG)

## Steps to use the generated client code:  
(i) Update the 'basepath' in the generated ApiClient.java: 
ApiClient.java includes the URL for establishing a HTTP client connection as the 'basePath'. The 'basepath' value in the generated code was 'https://localhost:8080'. This was updated to 'http://localhost:8080'  
(ii) Per instructions in the README.md, add the 'swagger-java-client' dependency to the pom.xml  
(iii) Run mvn clean install  

# Note the messages in the terminal:

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

# Using the generated REST Client  
To use the generated REST Client in any project include the generated JAR as a dependency.
     
### Steps for using the generated REST Client from within the current project
(i) Configure the build-path to include the 'generated-sources' folder as a source folder.  
The generated code includes 'StudentControllerApi.java' which includes methods corresponding to all endpoints exposed in the original project (that was the source of swagger.json).  
(ii) Create a new class 'GeneratedApi.java' with a 'main' method.  
(iii) Create a new Student (example, name: Sam and zip code: 98005).  
(iv) Create an instance of StudentControllerApi and call its createStudentUsingPOST() method and pass the student (created in the previous step) as the argument.

![img10GeneratedApi](https://user-images.githubusercontent.com/15854708/210187267-d5408969-da79-4150-b525-3d610566a635.JPG)

#### Message in the terminal indicates that the resource was created successfully (response code 201).
