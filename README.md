# EPAM Mentoring Program (Modul 4)

This is a module 4 task project, which test login and repository search on http://github.com, project based on <img src="https://www.seleniumhq.org/images/big-logo.png" width="17" height="17"/> Selenium WebDriver, <img src="https://spring-petclinic.github.io/images/logo-spring.png" width="20" height="20"/> [Spring-Test](https://spring.io/), <img src="https://getdrip.s3.amazonaws.com/uploads/form_images/54081650/side_fca0130d7aa9ecab00edd7fa1af3e673.png" data-canonical-src="https://getdrip.s3.amazonaws.com/uploads/form_images/54081650/side_fca0130d7aa9ecab00edd7fa1af3e673.png" width="20" height="20"/> [Cucumber](https://cucumber.io/).
and <img src="https://learn-automation.com/wp-content/uploads/2015/03/testng.jpg" data-canonical-src="https://getdrip.s3.amazonaws.com/uploads/form_images/54081650/side_fca0130d7aa9ecab00edd7fa1af3e673.png" width="20" height="20"/> [TestNG](https://testng.org/).

### Prerequisites

For run this project you need download and setup [Java](https://java.com/en/download/) and [Maven](https://maven.apache.org/download.cgi)


### Installing

You don't need any special installing or presetup for run this project (except Java and Maven). Just clone it from github.

## Running the tests

For run project with default configuration, go to project directory and run command:

```
mvn clean test
```
Also, you can configure a browser and resolution from command line.

Setup browser:
```
mvn -Dbrowser=opera clean test
```

Parallel execution of test:

Default number of threads is '1', but you can set any by command line parameter:
```
mvn -Dthreads.count=3 clean test
```

Or any combinations:
```
mvn -Dbrowser=opera -Dthreads.count=3 clean test
```

All tests marked by tags (@Login @Search @Positive and @Negative). If you want to run any group, type or combination, you have to add Cucumber options to command line:
```
mvn -Dcucumber.options="--tags '@Login and @Positive'" clean test
```
##### Supported browsers:
* chrome
* opera
* firefox
* IE

## Reporting

Porject include Allure report. For open report after tests complete execute command:

```
mvn allure:serve
```


## Built With

* [Maven](https://maven.apache.org/) - Build Manager

## Versioning

No further versiones are planned.

## Authors

* **Oleg Bilyk** - *Initial work* - [biloleg](https://github.com/Biloleg)

