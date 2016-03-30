![build status](https://travis-ci.org/mkorman9/scala-sprayframework-crud-app.svg?branch=master)

## Intro
Sample CRUD application for managing cats and groups of cats, written in Scala. It's meant to be an example and the entry point for future projects.


#### Technology stack 
**HTTP toolkit**: Spray Framework   
**Database mapping**: Slick   
**Dependency injection**: Guice   
**RDBMS**: MySQL   
**Servlet container**: Tomcat 7   
**Build tool**: Maven


In order to deploy app, datasource is required to be defined in JNDI tree as "java:comp/env/jdbc/db"


## Structure
The main concept of architecture is to split "domain models" from "forms". 
Form is a user-friendly representation of object stored in database, ready to convert to JSON and send between server and browser.
Form can be input or output. Input form is used by browser to serialize input data and send it to server, output form is used in opposite case


.   
├── .travis - **Resources used during integration tests, database schema is included here**   
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
│       └── integration - **Integration tests written in Python, executed by TravisCI after each push**   
.   
