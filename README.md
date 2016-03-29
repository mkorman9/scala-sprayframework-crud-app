![build status](https://travis-ci.org/mkorman9/scala-sprayframework-crud-app.svg?branch=master)

## Intro
Sample app using Scala and Spray Framework. Slick library is used for database mapping. Dependency injection is done via Guice.
App relies on MySQL database. It all serves as a REST webservice for managing cats and groups of cats (just as an example of typical CRUD app).
It's meant to be deployed on Tomcat web container containing definition of resource named "java:comp/env/jdbc/db".

## Structure
The main concept of architecture is to split "domain models" from "forms". 
Form is a user-friendly representation of object stored in database, ready to convert to JSON and send between server and browser.
Form can be input or output. Input form is used by browser to serialize input data and send it to server, output form is used in opposite case


.   
├── pom.xml - **Standard Maven build descriptor**   
├── src   
│   ├── main   
│   │   ├── resources   
│   │   ├── scala   
│   │   │   └── com   
│   │   │       └── example     
│   │   │           ├── logic - **Transactional and domain logic**  
│   │   │           ├── model - **Domain models**    
│   │   │           ├── modules - **Configuration modules**    
│   │   │           ├── util - **Utility classes**   
│   │   │           └── web   
│   │   │               ├── form - **Forms definitions**   
│   │   │               └── service - **Web service endpoints**    
│   │   └── webapp - **webapp content**   
│   └── test   
.   
