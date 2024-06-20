package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.magento.drivers.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.magento.pages.AccountCreationPage;
import org.magento.pages.LoginPage;
import org.magento.pages.MyAccountPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

import java.util.Locale;

public class AccountCreationSteps {

    private static final Logger logger = LoggerFactory.getLogger(AccountCreationSteps.class);
    WebDriver driver;
    AccountCreationPage accountCreationPage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    private String email;
    private String password;
    private Faker faker = new Faker(new Locale("en-IND"));

    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        accountCreationPage = new AccountCreationPage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        logger.info("Opening chrome browser");
    }
    @Given("I am on the Magento registration page")
    public void iAmOnTheMagentoaccountCreationPage() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        logger.info("Navigated to the Magento registration page.");
    }
    @Given("I am on the Magento login page")
    public void i_am_on_the_magento_login_page() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        logger.info("Navigated to the Magento login page.");
    }

    @When("I fill in the registration form with valid data")
    public void iFillInTheRegistrationFormWithValidData() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        password = generateCompliantPassword();
        accountCreationPage.enterFirstName(firstName);
        accountCreationPage.enterLastName(lastName);
        accountCreationPage.enterEmail(email);
        accountCreationPage.enterPassword(password);
        accountCreationPage.enterConfirmPassword(password);

//        accountCreationPage.enterFirstName("Test");
//        accountCreationPage.enterLastName("User");
//        accountCreationPage.enterEmail("testuser@example.com");
//        accountCreationPage.enterPassword("Test@1234");
//        accountCreationPage.enterConfirmPassword("Test@1234");
        logger.info("Filled in the registration form with valid data.");
    }

    @And("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        accountCreationPage.clickOnSubmitButton();
        logger.info("Submitted the registration form.");
    }

    @Then("I should see a confirmation message")
    public void iShouldSeeAConfirmationMessage() {
        Assert.assertTrue("Confirmation message is not visible.", accountCreationPage.isConfirmationMessageVisible());
        logger.info("Confirmation message is visible.");
    }

    @When("I sign out")
    public void iSignOut() {
        Assert.assertTrue("User is not logged out successfully.", myAccountPage.signOutFromApplication());
        logger.info("User is logged out successfully.");

    }

    @And("I sign in with the newly created account")
    public void iSignInWithTheNewlyCreatedAccount() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        loginPage.enterEmail("mangala.namboothiri@yahoo.co.in");
        loginPage.enterPassword("2XDfuR04q:");
        loginPage.clickSignIn();
        logger.info("Signed in with the newly created account.");
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertTrue("User is not logged in successfully.", myAccountPage.isUserOnMyAccountPage());
        logger.info("User is logged in successfully.");
    }

    @When("I fill in the registration form with an invalid email")
    public void i_fill_in_the_registration_form_with_an_invalid_email() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        email = "invalid-email";
        password = generateCompliantPassword();

        accountCreationPage.enterFirstName(firstName);
        accountCreationPage.enterLastName(lastName);
        accountCreationPage.enterEmail(email);
        accountCreationPage.enterPassword(password);
        accountCreationPage.enterConfirmPassword(password);

        logger.info("Filled in the registration form with invalid email. First name: {}, Last name: {}, Email: {}", firstName, lastName, email);
    }

    @When("I fill in the registration form with mismatched passwords")
    public void i_fill_in_the_registration_form_with_mismatched_passwords() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        password = generateCompliantPassword();
        String mismatchedPassword = generateCompliantPassword();

        accountCreationPage.enterFirstName(firstName);
        accountCreationPage.enterLastName(lastName);
        accountCreationPage.enterEmail(email);
        accountCreationPage.enterPassword(password);
        accountCreationPage.enterConfirmPassword(mismatchedPassword);

        logger.info("Filled in the registration form with mismatched passwords. First name: {}, Last name: {}, Email: {}", firstName, lastName, email);
    }

    @When("I sign in with incorrect credentials")
    public void i_sign_in_with_incorrect_credentials() {
        String incorrectEmail = faker.internet().emailAddress();
        String incorrectPassword = generateCompliantPassword();

        loginPage.enterEmail(incorrectEmail);
        loginPage.enterPassword(incorrectPassword);
        loginPage.clickSignIn();

        logger.info("Attempted to sign in with incorrect credentials. Email: {}", incorrectEmail);
    }

    @When("I fill in the registration form with a weak password")
    public void i_fill_in_the_registration_form_with_a_weak_password() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        password = "weakpass";

        accountCreationPage.enterFirstName(firstName);
        accountCreationPage.enterLastName(lastName);
        accountCreationPage.enterEmail(email);
        accountCreationPage.enterPassword(password);
        accountCreationPage.enterConfirmPassword(password);

        logger.info("Filled in the registration form with a weak password. First name: {}, Last name: {}, Email: {}", firstName, lastName, email);
    }
    @When("I fill in the registration form with missing required fields")
    public void i_fill_in_the_registration_form_with_missing_required_fields() {
        String firstName = "";
        String lastName = "";
        email = "";
        password = "";

        accountCreationPage.enterFirstName(firstName);
        accountCreationPage.enterLastName(lastName);
        accountCreationPage.enterEmail(email);
        accountCreationPage.enterPassword(password);
        accountCreationPage.enterConfirmPassword(password);

        logger.info("Filled in the registration form with missing required fields.");
    }
    @Then("I should see an email error message")
    public void i_should_see_an_email_error_message() {
        Assert.assertTrue("Email error message is not visible.", accountCreationPage.isEmailErrorVisible());
        logger.info("Email error message is visible.");
    }

    @Then("I should see a password mismatch error message")
    public void i_should_see_a_password_mismatch_error_message() {
        Assert.assertTrue("Password mismatch error message is not visible.", accountCreationPage.isPasswordErrorVisible());
        logger.info("Password mismatch error message is visible.");
    }
    @Then("I should see an authentication error message")
    public void i_should_see_an_authentication_error_message() {
        Assert.assertTrue("Authentication error message is not visible.", loginPage.isAuthenticationErrorMessageVisible());
        logger.info("Authentication error message is visible.");
    }
    @Then("I should see a weak password error message")
    public void i_should_see_a_weak_password_error_message() {
        Assert.assertTrue("Weak password error message is not visible.", accountCreationPage.isWeakPasswordErrorMessageVisible());
        logger.info("Weak password error message is visible.");
    }

    @Then("I should see a missing fields error message")
    public void i_should_see_a_missing_fields_error_message() {
        Assert.assertTrue("Missing fields error message is not visible.", accountCreationPage.isMissingFieldsErrorMessageVisible());
        logger.info("Missing fields error message is visible.");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        logger.info("Tear down and closed the driver.");
    }

    private String generateCompliantPassword() {
        String upperCaseLetters = faker.regexify("[A-Z]{1}");
        String lowerCaseLetters = faker.regexify("[a-z]{1}");
        String specialCharacters = faker.regexify("[!@#$%^&*()_+{}:<>?]{1}");
        String numbers = faker.regexify("[0-9]{2}");
        String otherChars = faker.regexify("[a-zA-Z0-9]{5}");
        String combinedChars = upperCaseLetters + lowerCaseLetters + specialCharacters + numbers + otherChars;
        char[] passwordChars = combinedChars.toCharArray();
        Random random = new Random();
        for (int i = 0; i < passwordChars.length; i++) {
            int randomIndexToSwap = random.nextInt(passwordChars.length);
            char temp = passwordChars[randomIndexToSwap];
            passwordChars[randomIndexToSwap] = passwordChars[i];
            passwordChars[i] = temp;
        }
        return new String(passwordChars);
    }

}
