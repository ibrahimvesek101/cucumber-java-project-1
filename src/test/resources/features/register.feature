Feature: Register


  @smoke
  Scenario: Register Happy Path
    # Given User goes to aimped page (Before)
    Given User clicks the Sign Up button
    Then User gets new email address
    Then User enters e-mail address and clicks the register button
    Then The user verifies that the link sent to the email box comes from "@clarusway.com" and clicks it. Creates the username and password
    Then User logs in to Aimped
    Then User verifies registration
