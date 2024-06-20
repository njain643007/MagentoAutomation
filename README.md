Magento Automation with Selenium WebDriver
This project demonstrates automated testing of the Magento website using Selenium WebDriver and Cucumber in Java. It includes tests for user registration, login, and other functionalities.

Prerequisites
Before running the tests, ensure you have the following installed:

Java Development Kit (JDK) version 8 or higher
Maven
WebDriver compatible with your browser (ChromeDriver)

Setup Instructions
1. Clone the repository:
    git clone https://github.com/yourusername/MagentoAutomation.git
    cd MagentoAutomation
2. Install dependencies:
    mvn clean install
Running Tests
  Execute the following Maven command to run all tests:
    mvn test

Project Structure
  src/main/java: Contains main Java classes (page objects, DriverFactory).
  src/test/java: Contains test automation code (step definitions, hooks).
  src/test/resources: Contains feature files.
  logs: Directory to store logs generated during test execution.
  report/cucumber-reports.html: Directory containing generated Cucumber reports.
Reporting
After running tests, HTML and JSON reports can be found in report/cucumber-reports.json/. These reports include detailed information about test results and scenarios executed.
