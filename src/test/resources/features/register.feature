Feature: Register

  @smokeTest @smoke
  Scenario: Register Happy Path
    # Given User goes to aimped page (Before)
    Given User clicks the Sign Up button
    Then User gets new email address
    Then User enters e-mail address and clicks the register button
    Then The user verifies that the link sent to the email box comes from "@clarusway.com" and clicks it. Creates the username and password
    Then User logs in to Aimped
    Then User verifies registration

  @smoke
  Scenario: Negative email adress_smk
    Given User clicks the Sign Up button
    Then The user enters an e-mail address and verifies error message

      |  | Email is required! |

  @smoke
  Scenario: Negative username in form_smk
    Given User clicks the Sign Up button
    Then User gets new email address
    Then User enters e-mail address and clicks the register button
    Then The user verifies that the link sent to the email box comes from "@clarusway.com" and clicks it.
    Then Creates the username

      |  | Username is required! |

  @smoke
  Scenario: Negative password in form_smk
    Given User clicks the Sign Up button
    Then User gets new email address
    Then User enters e-mail address and clicks the register button
    Then The user verifies that the link sent to the email box comes from "@clarusway.com" and clicks it.
    Then Creates the password

      |  | This field is required. |


#    ------------------------------------


  @regres
  Scenario: Negative email adress
    Given User clicks the Sign Up button
    Then The user enters an e-mail address and verifies error message

      |                           | Email is required!                  |
      | test!@gmail.com           | Email address not valid!            |
      | test@gmail.a              | Email address not valid!            |
      | test@com                  | Email address not valid!            |
      | testgmail.com             | Please enter a valid email address. |
      | ibrahimvesek101@gmail.com | This email already in use!          |
      | çiçek@gmail.com           | Please enter a valid email address. |
      | a balikesir@gmail.com     | Please enter a valid email address. |


  Scenario: Negative username in form
    Given User clicks the Sign Up button
    Then User gets new email address
    Then User enters e-mail address and clicks the register button
    Then The user verifies that the link sent to the email box comes from "@clarusway.com" and clicks it.
    Then Creates the username

      |              | Username is required!                                                                 |
      | إبراهيم      | Username may contain letters (A-Za-z), numbers (0-9), and special characters of @#-._ |
      | Abcd ef.101  | Username may contain letters (A-Za-z), numbers (0-9), and special characters of @#-._ |
      | Abcdef#@101  | Username must not contain adjacent occurrences of @#-._                               |
      | al           | Username should have at least 3 characters.                                           |
      | 101.Abc      | Username must start with letters (A-Za-z)                                             |
      | Çiçek.101    | Username may contain letters (A-Za-z), numbers (0-9), and special characters of @#-._ |
      | Test.101#    | Username must not end with special characters of @#-._                                |
      | Test##101    | Username must not contain adjacent occurrences of @#-._                               |
      | 12345        | Username must start with letters (A-Za-z)                                             |
      | ibrahimvesek | This username already in use!                                                         |

  Scenario: Negative password in form
    Given User clicks the Sign Up button
    Then User gets new email address
    Then User enters e-mail address and clicks the register button
    Then The user verifies that the link sent to the email box comes from "@clarusway.com" and clicks it.
    Then Creates the password

      |                       | This field is required.                                                                                                       |
      | Abc.123               | Password must be between 8 to 20 characters long.                                                                             |
      | إبراهيم               | Password may contain only uppercase (A-Z) letters, lowercase (a-z) letters, numbers (0-9), and special characters of .@#$%_&- |
      | Abc.12345678900000000 | Password must be between 8 to 20 characters long.                                                                             |
      | abc.1234              | Password must contain at least one uppercase.                                                                                 |
      | ABC.1234              | Password must contain at least one lowercase.                                                                                 |
      | Abcdefgh              | Password must contain at least one digit.                                                                                     |
      | Abcdefgh1             | Password must contain at least one special characters from .@#$%_&-                                                           |
      | Abc 1234567890        | Password may contain only uppercase (A-Z) letters, lowercase (a-z) letters, numbers (0-9), and special characters of .@#$%_&- |
      | Abc+1234567890        | Password may contain only uppercase (A-Z) letters, lowercase (a-z) letters, numbers (0-9), and special characters of .@#$%_&- |

  Scenario: Negative confirm password in form
    Given User clicks the Sign Up button
    Then User gets new email address
    Then User enters e-mail address and clicks the register button
    Then The user verifies that the link sent to the email box comes from "@clarusway.com" and clicks it.
    Then Creates the confirm password

      |          | This field is required.                                   |
      | 1        | Passwords do not match, please enter same password again. |
      | test.101 | Passwords do not match, please enter same password again. |
