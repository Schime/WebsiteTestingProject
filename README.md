# Website Testing with Java and WebDriver

## Overview

This repository contains a Java project for automated testing of the "Links" website using Java. The tests are designed using the Page Object Model (POM) and follow the principles of Object-Oriented Programming (OOP). Cross-browser testing is achieved with WebDriverManager, making it easy to run tests on different browsers. Project is based upon Maven.

## Technologies Used

- **Java**: Programming language used for test automation.
- **IntelliJ IDEA**: Integrated Development Environment (IDE) for Java development.
- **WebDriverManager**: Simplifies the management of WebDriver binaries for different browsers.
- **TestNG**: Testing framework for organizing and running tests.
- **Page Object Model (POM)**: Design pattern for organizing test code into page-specific classes.
- **Cross-Browser Testing**: Tests are designed to run on various browsers for maximum compatibility.
- **Surefire-Plugin**: Allows reports for test results

## Prerequisites

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- WebDriver dependencies managed by [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- Dependencies from [Maven Repository](https://mvnrepository.com/)
     - All dependencies are located in pom.xml file

## Setup and Configuration

1. Clone the repository to your local machine.
   ```bash
   git clone https://github.com/Schime/TestingProject.git

## Running Tests

There are two ways to run the tests in this project:

### 1. Using Maven

#### Option 1: IntelliJ Maven Plugin

1. Navigate to the right side of IntelliJ and click on the 'M' icon (Maven).
2. Find the 'Execute Maven Goal' button.
3. Run the following Maven command:
    ```bash
    mvn test
    ```
   
### 2. Using IntelliJ Run Configurations

#### Option 2: TestNG Configuration

1. Open the project in IntelliJ IDEA.
2. Navigate to **Run -> Edit Configurations**.
3. Click on the Plus icon located far left.
4. Locate **TestNG** and click on it.
5. Apply the configuration settings.
6. Run the test.

## Reporting

Testing reports are automatically generated and saved thanks to the Surefire Plugin used in this project. The Surefire Plugin is a widely-used Maven plugin for running unit tests and generating reports. Here's how reporting is handled:

### Surefire Reports

The Surefire Plugin generates detailed test reports, known as Surefire Reports, which provide insights into the test execution. These reports are automatically created during the Maven build process.

#### Where to Find Reports

After running tests using Maven, you can find the Surefire Reports in the target directory:
    ```
     target/surefire-reports/
    ```
In this directory, you'll find individual XML files for each test class, containing detailed information about test results, including pass/fail status, execution time, and more.

### Viewing Reports

To view the generated reports, you can open the HTML report in your browser. The HTML report provides a comprehensive overview of the test results, making it easier to identify any issues or failures.

#### Locating HTML Report

Navigate to the HTML report in the target directory:
    ```
      target/surefire-reports/index.html
    ```
Open `index.html` in your browser to view the aggregated test results.

These reports are valuable tools for assessing the success and health of your test suite, aiding in the identification and resolution of potential issues.

## Continuous Integration (CI) Integration

This project is configured for seamless integration with GitHub Actions, a powerful CI/CD platform. CI helps ensure the stability and reliability of your codebase by automatically running tests whenever changes are pushed to the repository.

### GitHub Actions Workflow

To enable CI for your project, a workflow configuration file named `.github/workflows/maven.yml` is included in the repository. This file contains the necessary instructions for GitHub Actions to build and test your project.

#### Contents of `maven.yml`

```yaml
name: CI Implementation

on:
  push:
    branches:
      - main

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v3

      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          distribution: 'adopt'
          java-version: '21.0.1'

      - name: Build and Test
        run: mvn test

      - name: Cleaning Up
        run: mvn clean


