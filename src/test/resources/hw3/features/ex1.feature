Feature: Exercise 1: check Home page elements

  Background: Init
    When I open the home page
    Then Home page is opened
    When I log as "Roman" - "Jdi1234"
    Then Username is not null

  Scenario: Assert browser title
    Then Browser title is "Home Page"

  Scenario: Assert username
    Then Username is "ROMAN IOVLEV"

  Scenario: Assert header sections
    Then 4 items of header section have proper texts

  Scenario: Assert images
    Then Images are displayed

  Scenario: Assert texts below images
    Then Texts block images are displayed
    And Blocks have proper text

  Scenario: Assert main header
    Then First paragraph of main header has proper text
    Then Second paragraph of main header has proper text

  Scenario: Assert the iframe
    Then The iframe exists
    When Switch to the iframe
    Then Check EPAM logo
    And Switch to home page

  Scenario: Assert sub header
    Then Text has proper value

  Scenario: Assert "JDI GITHUB" block
    Then JDI GITHUB is a Link
    And It has proper value

  Scenario: Assert Left Section
    Then Left Section exists

  Scenario: Assert Footer
    Then Footer exists