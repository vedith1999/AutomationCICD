@tag
Feature: Purchase the order from Ecommerce Website
			I want to use this template for my feature file
			
	Background:
	Given I landed on Ecommerce Page
			
@RegressionTest
Scenario Outline:
	Positive Test of submitting the order
	Given  Logged in with username <name> and password <password>
	When I add product <productName> to the Cart
	And checkout <productName> and submit the order
	Then "Thankyou for the order." message is displayed on the confirmation page
	
	Examples:
	| name 					| password 		|	productName |
	| jupaka.40@gmail.com 	| Qwerty@123456 |	ZARA COAT 3 |