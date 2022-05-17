**How to run**

<p>The easiest way to start the application is to go to the project directory
and run the command `./mvnw spring-boot:run`</p>

**There is also an alternative way**

<p>Application is an Intellij IDEA project, so to run application you need this IDE.
All you have to do is to:</p>

* go to class named RecruitmentApplication.java
* run main method

![](images/run_main.png)

<p>When you run it for the first time it will take some time. It needs read .csv file with awards data,
fetch proper movies from OMDb and populate database.</p>

<p>When it all happen you will see in application logs an entry:
"Movies are loaded to database"</p>