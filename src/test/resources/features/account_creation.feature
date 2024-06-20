Feature: Account creation and sign-in

  Scenario: Create a new account and sign in
    Given I am on the Magento registration page
    When I fill in the registration form with valid data
    And I submit the registration form
    Then I should see a confirmation message
    When I sign out
    And I sign in with the newly created account
    Then I should be logged in successfully

  Scenario: Attempt to create a new account with invalid email
    Given I am on the Magento registration page
    When I fill in the registration form with an invalid email
    And I submit the registration form
    Then I should see an email error message

  Scenario: Attempt to create a new account with mismatched passwords
    Given I am on the Magento registration page
    When I fill in the registration form with mismatched passwords
    And I submit the registration form
    Then I should see a password mismatch error message

  Scenario: Attempt to sign in with incorrect credentials
    Given I am on the Magento login page
    When I sign in with incorrect credentials
    Then I should see an authentication error message


  Scenario: Attempt to create a new account with a weak password
    Given I am on the Magento registration page
    When I fill in the registration form with a weak password
    And I submit the registration form
    Then I should see a weak password error message

  Scenario: Attempt to create a new account with missing required fields
    Given I am on the Magento registration page
    When I fill in the registration form with missing required fields
    And I submit the registration form
    Then I should see a missing fields error message