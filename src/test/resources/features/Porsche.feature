@Porsche

  Feature: Validating price matching

    Background: porsche selecting the model "718 Cayman"
      Given user navigates to porsche application
      When user stores the price and selects the model "718 Cayman"

    Scenario: Validating Porsche Price

      Then user validates Base price is matched with listed price

     @ValidationPriceEquipment
    Scenario: Validating Porsche Price For Equipment

      And user adds Power Sport Seats 14way with Memory Package
      Then user validates that Price For Equipment is added and price matches