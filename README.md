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
│   │   │   ├── application.conf - **Scala settings**   
│   │   │   └── logback.xml - **Definition of loggers, Tomcat /logs/ directory is used**   
│   │   ├── scala   
│   │   │   └── com   
│   │   │       └── example   
│   │   │           ├── SprayBoot.scala - **Main project class**   
│   │   │           ├── logic   
│   │   │           │   ├── CatFactory.scala - **Factory used to convert between domain model definitions and "forms"**   
│   │   │           │   ├── CatGroupFactory.scala - **Same as above**  
│   │   │           │   └── CatLogic.scala - **Transactional logic used to access data stored in database**   
│   │   │           │   └── CatGroupLogic.scala - **Same as above**  
│   │   │           ├── model   
│   │   │           │   ├── Cat.scala - **Definition of domain model "Cat" and it's database mappings**   
│   │   │           │   └── CatGroup.scala - **Same for "CatGroup"**   
│   │   │           ├── modules   
│   │   │           │   ├── AkkaModule.scala - **Initialization of Akka specific "beans"**   
│   │   │           │   ├── DbModule.scala - **Initialization of database specific "beans"**   
│   │   │           │   └── MainModule.scala - **Aggregator for modules**   
│   │   │           ├── util   
│   │   │           │   └── DateTimeJSONFormat.scala - **Converter between Joda's DateTime and javax.sql.Timestamp (util class)**   
│   │   │           └── web   
│   │   │               ├── form   
│   │   │               │   └── CatForm.scala - **Definitions of "forms"**   
│   │   │               └── service   
│   │   │                   └── WebService.scala - **Web service endpoint**   
│   │   └── webapp   
│   │       └── WEB-INF   
│   │           └── web.xml - **webapp descriptor**   
│   └── test   
│       ├── integration   
│       └── scala   
│           └── com   
│               └── example   
│                   └── logic   
│                       └── CatFactoryTest.scala - **Sample unit test for CatFactory**   
.   

