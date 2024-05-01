@regression @smoke @smartbearLogin

@loginPositive
Feature: Login functionality


  Scenario: Validating login functionality with valid credentials
    Given user navigates to smartbear application
    When user logs in with username "Tester" and password "test"
    Then user is successfully logged in and title is "Web Orders"


@v
  Scenario: Validating login functionality with valid credentials and verify logout
    Given user navigates to smartbear application
    When user logs in with username "Tester" and password "test"
    Then user is successfully logged in and title is "Web Orders"
    When User logoutApplication
    Then user should see login page "Web Orders Login"


@loginNegative
  Scenario: Validating login functionality with invalid credentials
  Given user navigates to smartbear application
  When user logs in with username "Test" and password "tester"
  Then user is not logged in and gets error message "Invalid Login or Password."







