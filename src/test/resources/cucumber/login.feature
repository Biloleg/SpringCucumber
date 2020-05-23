@Login
Feature: I verify login process with valid and not valid account

  Background:
    Given I open browser and navigate by configured URL
    When I navigate to Login page
    Then I check that "Login Page" is invoked

  @Negative
  Scenario Outline: Login with not valid credentials
    Given I type "<account>" to "Username" field on Login page
    And I type "<password>" to "Password" field on Login page
    And I click 'Sign In' button on Login page
    Then I check that Error Login message is "displayed" on Login page
    Then I check that Error login message has text:
    """
    Incorrect username or password.
    """
    Examples:
      | account         | password      |
      | wrongUsername   | wrongPassword |
      | wrong@email.com | wrongPassword |

  @Positive
  Scenario Outline: Login with valid credentials
    Given I type "<account>" to "Username" field on Login page
    And I type "<password>" to "Password" field on Login page
    And I click 'Sign In' button on Login page
    Then I check that Error Login message is "not displayed" on Login page
    Then I check that "Main Page" is invoked
    Examples:
      | account                 | password   |
      | springTest388           | Spr1ngT3st |
      | springTest388@gmail.com | Spr1ngT3st |