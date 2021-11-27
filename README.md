# Cucumber Functional Testing

Functional Testing checks an application or system to ensure that it is doing exactly what it is meant to.
It mainly involves black box testing and it is not concerned about the source code of the application.
This project template uses [Cucumber](https://cucumber.io/) and [MockServer](https://www.mock-server.com/) to test a [Spring](https://spring.io/) REST API.

## Overview

[Cucumber](https://cucumber.io/) is a *Behavioral Driven Development* (BDD) testing framework.
Cucumber uses [Gherkin](https://cucumber.io/docs/gherkin/reference/), a set of special keywords to give structure and meaning to executable specifications.
In Cucumber, whatever you write must go into *Given-When-Then* steps. Lets consider the next example:

```gherkin
Feature: Is it Friday yet?

  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it's Friday yet
    Then I should be told "Nope"
```

The first line starts with the keyword `Feature` followed by a description.
The third line, `Scenario: Sunday isn't Friday` is a scenario, which is a concrete example illustrating how the software should behave.
The last three lines starting with `Given`, `When` and `Then` are the steps of our scenario. This is what Cucumber will execute.

## Project structure

This project was generated with [Spring Initializr](https://start.spring.io/).
All of the app's code goes in a folder named `src/main`.
The unit tests and functional test are in the `src/test` and `src/functionalTest` folders.
Static files are placed in `src/main/resources` folder.

## Available gradle tasks

The tasks in [build.gradle](build.gradle) file were built with simplicity in mind to automate as much repetitive tasks as possible and help developers focus on what really matters.

The next tasks should be executed in a console inside the root directory:

- `./gradlew tasks` - Displays the tasks runnable from root project 'app'.
- `./gradlew bootRun` - Runs this project as a Spring Boot application.
- `./gradlew check` - Runs all checks.
- `./gradlew test` - Runs the unit tests.
- `./gradlew functionalTest` - Run the functional tests.
- `./gradlew clean` - Deletes the build directory.
- `./gradlew build` - Assembles and tests this project.
- `./gradlew help` - Displays a help message.

For more details, read the [Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html) documentation in the [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html).

## Running unit tests

Unit tests are responsible for testing of individual methods or classes by supplying input and making sure the output is as expected.

Use `./gradlew test` to execute the unit tests via [JUnit 5](https://junit.org/junit5/), [Mockito](https://site.mockito.org/) and [AssertJ](https://assertj.github.io/doc/).
Use `./gradlew test -t` to keep executing unit tests in real time while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/test/index.html) file in your web browser.

It's a common requirement to run subsets of a test suite, such as when you're fixing a bug or developing a new test case.
Gradle provides different mechanisms.
For example, the following command lines run either all or exactly one of the tests in the `SomeTestClass` test case:

```bash
./gradlew test --tests SomeTestClass
```

For more details, you can see the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation.

This project uses [JaCoCo](https://www.eclemma.org/jacoco/) which provides code coverage metrics for Java.
The minimum code coverage is set to 80%.
You can see the HTML coverage report opening the [index.html](build/reports/jacoco/test/html/index.html) file in your web browser.

## Running functional tests

Functional Testing is not concerned about the source code of the application.
The test files (i.e. `*.feature` files) are stored in the `src/functionalTest/resources` directory.
MockServer is used to return specific responses for different requests.

Use `./gradlew functionalTest` to execute the functional tests via [Cucumber](https://cucumber.io/), [MockServer](https://www.mock-server.com/) and [AssertJ](https://assertj.github.io/doc/).
Use `./gradlew functionalTest -t` to keep executing your tests while watching for file changes in the background.
You can see the HTML report opening the [report.html](build/reports/cucumber/report.html) file in your web browser.

As with unit tests, you can exclude or focus on some tests.
You can exclude features ou scenarios with the `@Ignore` tag or run specific features or scenarios with the `@Focus` tag.
In the case of focused tests, you need to use `./gradlew functionalTest -Dfocus=true` command.

## Debugging

You can debug the source code, add breakpoints, inspect variables and view the application's call stack.
Also, you can use the IDE for debugging the source code, unit and functional tests.
You can customize the [log verbosity](https://docs.gradle.org/current/userguide/logging.html#logging) of gradle tasks using the `-i` or `--info` flag.

## Reference Documentation

For further reference, please consider the following sections:

- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/gradle-plugin/reference/html/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
- [Cucumber 10 Minute Tutorial](https://cucumber.io/docs/guides/10-minute-tutorial/)
- [How to Automate Tests for Spring Boot API using MockServer](https://medium.com/devtechtoday/how-to-automate-tests-for-spring-boot-api-using-mockserver-c6221ea8c549)
