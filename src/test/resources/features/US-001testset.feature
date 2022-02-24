Feature: Default

	@INTA-921 @INTA-919
	Scenario Outline: US-001_AC1_TC1 Login with valid credentials 
		When the "<userType>" is on the login page
		Given the user write "<userName>" and "<password>"
		Then the user should be able to login and the title should be "<pageTitle>"
		Examples:
		| userType     | userName        | password    | pageTitle       |
		| driver       | user10          | UserUser123 | Quick Launchpad |
		| Salesmanager | Salesmanager110 | UserUser123 | Dashboard       |
		| Storemanager | Storemanager85  | UserUser123 | Dashboard       |	


	@INTA-922 @INTA-919
	Scenario Outline: US-001_AC1_TC2 Login with invalid credentials 
		When the user is on the login page
		Given the user write "<userName>" and "<password>"
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


	@INTA-923 @INTA-919
	Scenario: US-001_AC2_TC3 Login without providing credentials
		When the user is on the login page
		Given the user logs in with "userName" and "password"
		And the user copies the url and logs out
		Then the user should not be able to login with pasting the url	

	@INTA-924 @INTA-919
	Scenario: US-001_AC4_TC4 Login with spaces on username
		When the user is on the login page
		Given the user logs in with spaces on "userName"
		Then the user should be able to login	

	@INTA-925 @INTA-919
	Scenario Outline: US-001_AC6_TC5 Warning message for invalid credentials
		When the user is on the login page
		Given the user write invalid "<userName>" and "<password>"
		Then the warning message should pop up as "Invalid user name or password."
		Examples:
		  | userName | password    |
		  | user20   | useruser123 |
		  | 123sales | UserUser123 |	


	@INTA-1043 @INTA-919
	Scenario Outline: US-001_AC6_TC6 Warning message for empty fields
		When the user is on the login page
		Given the user write "<userName>" or "<password>"
		Then the warning message should pop up as "Please fill out this field." for the empty field
		Examples:
		   | userName | password    |
		   | user20   |             |
		   |          | UserUser123 |	


	@INTA-1044 @INTA-919
	Scenario: US-001_AC7_TC7 Password field is toggled to hide visibility
		When the user is on the login page
		Given the user write any password
		Then the password should not be visible as letters	


	@INTA-1045 @INTA-919
	Scenario: US-001_AC8_TC8 Password visibility in the page source
		When the user is on the login page
		Given the user write any password
		Then the password should not be visible in page source	


	@INTA-1046 @INTA-919
	Scenario: US-001_AC9_TC9 Password is not possible to copy
		When the user is on the login page
		Given the user write any password
		Then the password should not be possible to copy	

	@INTA-1049 @INTA-919
	Scenario: US-001_AC10_TC10 User lands on "Forgot password" page 
		When the user is on the login page
		Then the user lands on Forgot Password page after clicking Forgot your password? link	


	@INTA-1050 @INTA-919
	Scenario: US-001_AC10_TC11 User able to change password with Request button
		When the user is on the login page
		Given the user lands on Forgot Password page after clicking Forgot your password? link
		Then the user can change their password by clicking REQUEST button.	

	@INTA-1051 @INTA-919
	Scenario: US-001_AC11_TC12 Remember me checkbox is clickable
		When the user is on the login page
		Then the user should be able to click Remember me on this computer checkbox	


	@INTA-1052 @INTA-919
	Scenario: US-001_AC12_TC13 Login with Enter button from keyboard 
		When the user is on the login page
		Given the user write a valid "username" and click ENTER
		Then the user should be on password input box, write a valid "password"
		Then the login button should be clicked by hitting the ENTER button	

	@INTA-1053 @INTA-919
	Scenario: US-001_AC12_TC14 Moving from username to password inbox with TAB button
		When the user is on the login page
		Given the user write a valid "username" and click TAB
		Then the user should be on password input box, write a valid "password"
		Then the login button should be clicked by hitting the ENTER button	

	@INTA-1055 @INTA-919
	Scenario: US-001_AC3_TC15 Login without providing credentials on a new TAB
		When the user is on the login page
		Given the user logs in with "userName" and "password"
		Then the user should be able to login by copying url without logging out, opening a new tab and pasting the url.