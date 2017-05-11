A simple project using Dropwizard for a series of tutorials.  
  
To checkout the project type `git clone https://dnoranovich@bitbucket.org/dnoranovich/dropwizard-getting-started.git`  

Its description can be found here:  
	* [Start building JVM microservices  with DropWizard](http://javaeeeee.blogspot.com/2015/12/start-building-jvm-microservices-with.html)  
	* [First Steps](http://javaeeeee.blogspot.com/2015/01/getting-started-with-dropwizard-first.html) (git checkout first_steps)  
	* [Authentication, Configuration and HTTPS](http://javaeeeee.blogspot.com/2015/02/getting-started-with-dropwizard.html) (git checkout auth_conf)  
	* [Connecting to a Database using Hibernate](http://javaeeeee.blogspot.com/2015/11/getting-started-with-dropwizard.html) (git checkout db_hibernate)  
	* [Connecting to external REST Web-services using Jersey Client](http://javaeeeee.blogspot.com/2015/12/getting-started-with-dropwizard.html) (git checkout client)   
To obtain the source code for each part execute a *git* command in the braces to see the code pertinent to the particular part.  

To work with the database part of tutorial it is necessary to have MySql server running and database *DWGettingStarted* to be created.  
Database settings can be found in the `config.yml` file. Change it to switch to another RDBMS.  

To run the application type the two following commands:  
        * `mvn clean package`  
        * `java -jar target/DWGettingStarted-1.0-SNAPSHOT.jar server config.yml`  
  
Also, here is a link to my [Dropwizard Udemy course](https://www.udemy.com/getting-started-with-dropwizard/?couponCode=bb10).  
  

