@Search
Feature: I verify login process with valid and not valid account

  Background:
    Given I open browser and navigate by configured URL
    When I navigate to Login page
    Then I check that "Login Page" is invoked
    When I type "springTest388" to "Username" field on Login page
    And I type "Spr1ngT3st" to "Password" field on Login page
    And I click 'Sign In' button on Login page
    Then I check that "Main Page" is invoked

  @Negative @TestRailsId#115
  Scenario Outline: Search not existed repository
    Given I type "<repoName>" to Search field on Main page and confirm
    Then I check that 'Not Found' message is "displayed" on Main page
    Then I check that 'Not Found' message has text:
    """
    We couldn’t find any repositories matching '<repoName>'
    """
    Examples:
      | repoName                   |
      | sddsgdsdsfgdfdhgfhcdfghchf |

  @Positive @TestRailsId#116
  Scenario Outline: Search existed repository
    Given I type "<repoName>" to Search field on Main page and confirm
    Then I check that 'Not Found' message is "not displayed" on Main page
    Then I check that repo with link "<repoOwner>/<repoName>" is "present" in repo list on Main page
    Then I check that repo with link "<repoOwner>/<repoName>" has "Java" program language in repo list on Main page
    Examples:
      | repoOwner | repoName       |
      | Biloleg   | springCucumber |