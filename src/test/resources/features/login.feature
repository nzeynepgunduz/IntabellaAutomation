@login
Feature: Login Functionality

  Scenario Outline: Login with valid credentials as a <userType>
    When the "<userType>" is on the login page
    Given the user write "<userName>" and "<password>"
    Then the user should be able to login and the title should be "<pageTitle>"
    Examples:
      | userType     | userName        | password    | pageTitle       |
      | driver       | user10          | UserUser123 | Quick Launchpad |
      | Salesmanager | Salesmanager110 | UserUser123 | Dashboard       |
      | Storemanager | Storemanager85  | UserUser123 | Dashboard       |


  Scenario Outline: Login with invalid credentials
    When the user is on the login page
    Given the user write invalid "<userName>" and "<password>"
    Then the user should not be able to login
    Examples:
      | userName        | password     |
      | user25          | UserUser1234 |
      | Salesmanager130 | Useruser123  |
      | Storemanager85  | UserUser123. |
      | Storemanager555 | UserUser123  |
      | Salesmanager    | UserUser123  |
      |                 | UserUser123  |
      | user20          |              |


  Scenario: Login without providing credentials
    When the user is on the login page
    Given the user logs in with "userName" and "password"
    And the user copies the url and logs out
    Then the user should not be able to login with pasting the url

  @error
  Scenario: Login without providing credentials
    When the user is on the login page
    Given the user logs in with "userName" and "password"
    Then the user should be able to login by copying url without logging out, opening a new tab and pasting the url.

  Scenario: Login with spaces on username
    When the user is on the login page
    Given the user logs in with spaces on "userName"
    Then the user should be able to login


  Scenario Outline: Warning message for invalid credentials
    When the user is on the login page
    Given the user write invalid "<userName>" and "<password>"
    Then the warning message should pop up as "Invalid user name or password."
    Examples:
      | userName | password    |
      | user20   | useruser123 |
      | 123sales | UserUser123 |


  Scenario Outline: Warning message for empty fields
    When the user is on the login page
    Given the user write "<userName>" or "<password>"
    Then the warning message should pop up as "Please fill out this field." for the empty field
    Examples:
      | userName | password    |
      | user20   |             |
      |          | UserUser123 |

    Scenario: Password is toggled to hide the visibility
      When the user is on the login page
      Given the user write any password
      Then the password should not be visible as letters

      Scenario: Password visibility in page source
        When the user is on the login page
        Given the user write any password
        Then the password should not be visible in page source

     Scenario: Password should not be possible to copy
       When the user is on the login page
       Given the user write any password
       Then the password should not be possible to copy

     Scenario: Forgot password page
       When the user is on the login page
       Then the user lands on Forgot Password page after clicking Forgot your password? link


       Scenario: Forgot password page -2
         When the user is on the login page
         Given the user lands on Forgot Password page after clicking Forgot your password? link
         Then the user can change their password by clicking REQUEST button.

        Scenario: Remember me button visibility
          When the user is on the login page
          Then the user should be able to click Remember me on this computer checkbox


        Scenario: Login by using the Keyboard keys
          When the user is on the login page
          Given the user write a valid "username" and click ENTER
          Then the user should be on password input box, write a valid "password"
          Then the login button should be clicked by hitting the ENTER button


        Scenario: Login by using the Keyboard keys
          When the user is on the login page
          Given the user write a valid "username" and click TAB
          Then the user should be on password input box, write a valid "password"
          Then the login button should be clicked by hitting the ENTER button







