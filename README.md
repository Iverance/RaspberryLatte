RaspberryLatte

==============
Coffee with half milk, 2% Raspberry flavor and whip cream

Install maven 3 or above version in your unix based OS, then run following command in terminal to build the project in the based folder(../cab).
```
-mvn clean package
```

If you are using eclipse, using following commands to create .project file for eclipse.
```
-mvn eclipse:clean eclipse:eclipse
```
remember to refresh in eclipse everytime after you build the project.

==============

Now the cab project contain a spring rest server. To start the service we need to do above compiling and build then run following: 

```
mvn spring-boot:run
```

Then it should start running on your local machine and you can test it with accessing to http://localhost:8080/greeting
