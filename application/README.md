# Application
Pre requiste:

JDK: 11
Maven: latest


- Step 1: mvn clean install 
- Step 2: run PollerManagementApplication as Java application
- Step 3: Access http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/service-controller/addService

Services can be:
- Added
- Updated
- Deleted
- Retrived

Poll endpoint can be invoked, if the poll should be triggered manually.

Database:
H2 db is used

- JDBC url: jdbc:h2:file:C:/data/demo
- user: sa
- password: password

H2 console can be seen in below url
The http://localhost:8081/h2/login.do
