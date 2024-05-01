@regression @smoke @magento
  Feature: Validating Magento Titles


    @magentoTitles
    Scenario Outline: Validating each tab on home page
      Given user navigates to magento application
      When user clicks on "<Tab>" tab
      Then user validates the "<Title>" title
      Examples:
        | Tab        | Title      |
        | What's New | What's New |
        | Women      | Women      |
        | Men        | Men        |
        | Gear       | Gear       |
        | Training   | Training   |
        | Sale       | Sale       |

      #Use backslash to escape pipes in feature files(| -> \ | = regular, non-keyword pipe)


