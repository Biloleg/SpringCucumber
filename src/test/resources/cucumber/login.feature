Feature: Login

  Scenario Outline: Login with not valid credentials
    Given I open browser and navigate by configured URL
    When I navigate to Login page
    And I type "<account>" to "Username" field on Login page
    And I type "<password>" to "Password" field on Login page
    And I click "Sign In" button on Login page
    Then I check that Error Login message is "displayed" on Login page
    #Then I check that Error login message has text:
   # """
    #Incorrect username or password.
    #"""
    Examples:
      | account         | password      |
      | wrongUsername   | wrongPassword |
      | wrong@email.com | wrongPassword |