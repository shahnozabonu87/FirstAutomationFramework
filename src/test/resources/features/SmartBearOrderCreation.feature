
  @regression @smartbear @orderCreation
  Feature: Validating Order Creation

    Background: @setup
      Given user navigates to smartbear application
      And user logs in with username "Tester" and password "test"
      And user clicks on Order tab

    @discountCalculator
    Scenario Outline: Applying discount to the total amount

      When user selects product "<Product>" and quantity <Quantity>
      Then user validates the price is calculated correctly for quantity <Quantity>
      Examples:
      |Product|Quantity|
      |MyMoney|9       |
      |FamilyAlbum|10  |
      |ScreenSaver|11  |


      @PlaceOrder
      Scenario: Placing an order and validating
        When user places an order with data and validates with success message "New order has been successfully added."
          | PRODUCT     | QUANTITY | CUSTOMER NAME | STREET     | CITY     | STATE | ZIP   | CARD       | CURD NUM  | EXP DATE |
          | MyMoney     | 5        | John Doe      | 123 Lee St | New York | NY    | 12345 | Visa       | 123456789 | 06/36    |
          | FamilyAlbum | 10       | Harsh Patel   | 123 Leo St | Chicago  | IL    | 12335 | Amex       | 123456777 | 05/36    |
          | ScreenSaver | 15       | Umid Ikramov  | 167 Lee St | Boston   | MA    | 12664 | Mastercard | 127456789 | 06/34    |
        Then user validates the created order is in the list of all orders
