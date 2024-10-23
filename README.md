
# Rijksmuseum API Test Suite

This project contains a series of automated tests written to verify the Rijksmuseum public API. The tests are implemented using Java, RestAssured, JUnit 5, and Allure for reporting.

## Prerequisites

To run this project locally, you will need the following software installed:

1. **Java Development Kit (JDK)** - Version 11 or higher.
2. **Maven** - To manage dependencies and build the project.
3. **Allure CLI** - To generate test reports

## Project Setup

Follow the steps below to set up the project:

1. **Clone the Repository**
   ```sh
   git clone <repository-url>
   cd rijksmuseum-api-tests
   ```

2. **Install Dependencies**
   Ensure you have Maven installed, and then run the following command to install dependencies:
   ```sh
   mvn clean install
   ```

3. **Run Tests**
   To execute the test suite, use the following command:
   ```sh
   mvn clean test
   ```

   This command will execute all the test cases included in the suite.

4. **Generate Allure Reports**
   If you want to generate an Allure report to visualize the test results, first ensure Allure is installed, and then run:
   ```sh
   mvn allure:serve
   ```
   This will generate a temporary server with the test report.


## Running Tests in CI/CD

This project is set up to be integrated with GitHub Actions. Upon opening a Pull Request, the tests will be automatically run, and the results will be available in the Actions tab. Successful tests are required before a PR can be merged into the `master` branch.



