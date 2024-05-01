@ElarAppRegression @AddCompany

Feature: Elar App add Company Module  Functionality


  Background: Elarapp login
    Given User navigates to Elar App Application
    When user inputs  username "student1@mindtekbootcamp.com" and password "mindtek109"
    And user clicks on company list button
    And user adds the company

  @InlineErrorMsgNameField
  Scenario: Validating name field is required

    And user inputs  fifty characters "1234567890abcdefghijklmnopqrstuvwxyz|||||--&&&1234"  in the name field
    And user clears the field
    Then user validates the inline error message


  @NameField50Characters
  Scenario: Validating name field with  more then 50 characters

    And user inputs fifty one characters "1234567890abcdefghijklmnopqrstuvwxyz|||||--&&&12341"   in the name field
    Then user validates the name field accepts only "1234567890abcdefghijklmnopqrstuvwxyz|||||--&&&1234" characters

  @NameFieldWithinRange
  Scenario Outline: Validating name field with valid inputs characters

    And user inputs "<value>"  in the name field
    Then user validates the name field accepts provided "<value>"
    @NameFieldWith25  @smoke1
    Examples:
      | value                      |
      | 1234567890abcdefghijklmnop |
    @NameFieldWithOnlyOneCharacter
    Examples:
      | value |
      | a     |
    @NameFieldWithValidCharacters  @smoke1
    Examples:
      | value     |
      | 1k -f.&\| |


  @NameFieldTextSelection
  Scenario: NameFieldHoverOverTextSelection
    And user hover over the cursor on the name field
    Then user validates the cursor changes to text selection


  @ErrorMsj
  Scenario: Validating error message  of name field
    And user inputs invalid text "!@#"
    Then user expecting error message "Invalid input"


  @BrokerCompanyByDefault
  Scenario: Validating Company type field is "Broker company" by default
    Then  user verifies Company type  field is "Broker company" by default


  @BrokerCompanyDropDown
  Scenario: Validating Company type field is drop down
    Then  user verifies Company type  field is "Broker company" by default
    And user clicks on  drop down  in Company type field
    Then user validates after clicking drop down "Broker company" , "Carrier company" and "Broker+Carrier company" status pop up


  @Tag1
  Scenario: Validating  Company type can be updated from default
    Then  user verifies Company type  field is "Broker company" by default
    And user clicks on  drop down  in Company type field
    And user clicks on "Carrier company" status
    And user clicks on  drop down  in Company type field
    And user clicks on "Broker+Carrier company" status
    Then user validates status of field are switching Broker company to Carrier company,Carrier company to Broker+Carrier company



@StatusActive
Scenario: Validating  status field is required and active by default
  Then user verifies status field is required and active by default

@statusDropdownCheck
  Scenario: Validating status field is drop down
    Then user verifies status field is required and active by default
    And user clicks on drop down in status field
    Then user validates after clicking drop down "active" and "Non-active" status pop up


@statusDifferentColorCheck
  Scenario: Validating status of field are different color
    And user clicks on drop down in status field
    Then user validates after clicking drop down "active" and "Non-active" status pop up
    Then user validates green color for active and red color for non-active

@statusSwitching
  Scenario: Validating active and Non-active status switching
    Then user verifies status field is required and active by default
    And user clicks on drop down in status field
    And user clicks on "Non-active" status
    And user clicks on drop down in status field
    And user clicks on "active" status
    Then user validates status field are switching "active" to "Non-active"

@statusCursorHand
  Scenario: Validating status field hover overing Cursor hand
    And user hovers over the cursor on the status field
    Then user validates hover over the cursor on status field is cursor hand

@phoneMaxValue
  Scenario: Validating Phone field wit max value of 12 and  dashes is being added automatically
    And user inputs more than twelve  characters "12345678901" in the phone field
    Then user validates phone field accepts only "123-456-7890" with two dashes


@PhoneFieldTextSelection
  Scenario: Validating Phone  field is text selection
    And user hovers over the cursor on the phone field
    Then user validates cursor icon change to text selection



  @phoneFieldErrorMsg
  Scenario Outline: Validating Phone field error messages
    And user inputs "<inputValue>" in the phone field
    Then user validates error message "<errorMessage>" under phone field

    Examples:
      |inputValue|errorMessage|
      |123456789 |Min length is 12 characters, currently it is 11.|
      |a         |This field is required.                              |

@phoneRequiredField
  Scenario: Validating Phone field is required field
    And user inputs twelve characters "1234567890"
   And user clears the phone field
    Then user validates error message "This field is required."

@extPhoneMaxValue
  Scenario:  Validate Ext(Phone ) field with max value of 10 and only digits are accepted
    And user input ten digits "1234567890" in the Ext(Phone) field
    Then user validates Ext(Phone) field accepting ten digits "123-456-7890"

#@extPhoneEnterDigits
#  Scenario:  Validate Ext(Phone) field error message "Enter only digits"
#    And user inputs any letter "a" in the ext(phone) field
#    Then user validates Ext(Phone) field error message "Enter only digits."

@contactNameMaxValue
  Scenario: Validate Contact name(Phone) field with max of 35 characters
    And user input more than thirty five characters "12345678901234567890dddddddddd -1231" in the Contact name(Phone ) field
    Then user validates Contact name(Phone) field accepts only thirty five "12345678901234567890dddddddddd -123" characters

@contactNameValid
  Scenario: Validate Contact name(Phone) field with valid characters(numbers,letters,spaces,dashes)
    And user input valid characters "123- abc" in the Contact name(Phone) field
    Then user validate Contact name(Phone) field is accepting valid characters "123- abc"

@contactNameInvalidInput
  Scenario: Validate Contact name(Phone) field error message "Invalid input"
    And user input characters  "abc|" in the Contact name(Phone) field
    Then user validates Contact name(Phone) field error message "Invalid input"
  #------------------------------------------------------------------------------
@hoverOverCursorContact
  Scenario: Validate Cursor when hovering over the Contact name(Phone)  field
    And user hovers over the cursor on   Contact name(Phone) field
    Then user validates cursor icon change to text selection


@faxFieldMaxVal
  Scenario: Validate Fax field with max value of 12 and accepts only 10 numbers with 2 dashes
    And user inputs data "1234567890" in the Fax field
    Then user validates Fax field accepting twelve characters "123-456-7890"

@faxFieldMinLength
  Scenario: Validate  Fax field error message "Min length is"
    And user inputs nine digits "123456789" on the Fax field
    Then user validates error message "Min length is 12 characters, currently it is 11" for Fax field

@hoverOverCursorFax
  Scenario: Validate  Cursor when hovering over the Fax field
    And user hovers over the cursor on the Fax field
    Then user validates cursor icon change to text selection

#@streetFieldMaxVal
#  Scenario: Validate Street field with max value of 35 characters
#    And user inputs more than thirty five characters "12345678901234567890dddddddddd -1231" in the street field
#    Then user validates street field accepts only thirty five "12345678901234567890dddddddddd -123" characters

#@StreetValidChars
#  Scenario: Validate Street field with valid characters(Numbers, letters, comma, dots, slash, dash, colon, percentage, space, grid)
#    And user inputs valid characters "123,a.b/c-:z% v" in the street field
#    Then user validates street field accepts all valid "123,a.b/c-:z% v" characters

  @check1
  Scenario Outline: Validate Street field with valid characters
    And user inputs valid characters "<value>" in the street field
    Then user validates street field accepts "<expectedValue>" all valid  characters
   Examples:
    |value                                |expectedValue|
    |12345678901234567890dddddddddd -1231  |12345678901234567890dddddddddd -123|
    |123,a.b/c-:z% v                       |123,a.b/c-:z% v                    |
    |65-20 Rego Park                       |65-20 Rego Park                    |








@streetReqField
Scenario: Validate Street field is required field
  And user inputs data "abc123" in the street field
  And user clears the street field
  Then user validates street field  error message "This field is required."


@streetInvalidIn
Scenario: Validate Street field error message "Invalid input"
  And user inputs valid characters with add sign "123@abc" sign  in the street field
  Then user validates street field  error message "Invalid input"

@streetCursorHover
Scenario: Validate Cursor hover over on the street field is text selection
  And user hovers over the cursor on the street field
  Then user validates cursor changes to text selection on the street field

@streetInvalidChars
Scenario: Validate Street field should not accept invalid characters
  And user inputs data with invalid characters "123#abc$" in the street field
  Then user validates street field should not accept  invalid characters


@aptValidChars
 Scenario Outline: Validate Apt,suite,company,c/o field with valid characters
  And user inputs valid characters "<value>" in the Apt,suite,company,c/o field
  Then user validates Apt,suite,company,c/o field accepts "<expectedValue>" all valid  characters

Examples:

  |value                               |expectedValue                      |
  |1234567890,bbbbbbbbbb.1234567890/-%1|1234567890,bbbbbbbbbb.1234567890/-%|
  |123,a/b.2-d% m                      |123,a/b.2-d% m                     |


@aptInvalidChars
Scenario: Validate Apt,suite,company,c/o field should not accept invalid characters

 And user inputs data with invalid characters "abc$123&" in the  Apt,suite,company,c/o field
  Then user validates Apt,suite,company,c/o field should not accept  invalid characters

@aptErrorMsg
  Scenario: Validate Apt,suite,company,c/o field error message "Invalid input"
    And user inputs data with invalid character "abc 123@" in the  Apt,suite,company,c/o field
    Then user validates  Apt,suite,company,c/o field error message "Invalid input"

@aptCursor
Scenario: Validate Cursor hover over on the  Apt,suite,company,c/o field is text selection
  And user hovers over the cursor on the Apt,suite,company,c/o field
  Then user validates cursor changes to text selection on the Apt,suite,company,c/o field


@cityFieldMaxValue
  Scenario: Validate City field with max value of 28 valid characters (letters,dashes,spaces)
    And user inputs more than twenty eight characters "aaaaaaaaaaaaaaaaaaaa bb-cccccs" in the City field
    Then user validates City field should accept only twenty eight "aaaaaaaaaaaaaaaaaaaa bb-cccc" characters

@cityRequired
  Scenario: Validate City field is required field
    And user inputs twelve characters "abc"
    And user clears the city field
    Then user validates error message "This field is required."

@cityInvalidMsg
  Scenario: Validate City field error message "Invalid input"
    And user inputs data "123" in the City field
    Then user validates error message "Invalid input" for city field

@cityTextSelection
  Scenario: Validate City field is text selection
    And user hovers over the cursor on the City field
    Then user validates cursor changes to text selection on the City field


  Scenario: Validate State field is drop down
    And user clicks on  drop down  in State field
    Then user validates after clicking drop down on Statefield  list of states  should pop up


@zipCodeReq
    Scenario: Validate Zip code is required field
      And user inputs twelve characters "MADOV.4125@gmail.com"
      And user clears the Zip code field
      Then user validates error message "This field is required."