@regression @magentoSearch
  Feature: Magento Search Functionality

    Background: Magento setup
      Given user navigates to magento application

     @magentoSearchValidation
    Scenario: Validating search result contains searches keyword

      When user searches for keyword "jacket"
      Then user validates search result contains
      | jacket | summit kit | sweatshirt | jackshirt |pullover|shell|

    @MagentoPriceRange
  Scenario: Validating price range filter
    And user searches for women's jacket
    When user selects price range from fifty to sixty dollars
    Then user validates items prices are within 50.00 and 59.99 dollars


