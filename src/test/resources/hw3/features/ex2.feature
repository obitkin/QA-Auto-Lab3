Feature: Exercise 2: check page elements and

  Background: Init
    When I open the home page
    Then Home page is opened
    When I log as "Roman" - "Jdi1234"
    Then Username is not null

  Scenario: Assert browser title
    Then Browser title is "Home Page"

  Scenario: Assert username
    Then Username is "ROMAN IOVLEV"

  Scenario: Assert the top "Service" subcategory
    Then Drop down options of top Service have proper values

  Scenario: Assert the left "Service" subcategory
    Then Drop down options of left Service have proper values

  Scenario: Assert elements
    Given Open "Different elements" Page
    Then Check current URL
    And  There are 4 radios
    And  There are 4 checkboxes
    And  There are 1 dropdown
    And  There are 2 buttons

  Scenario: Assert Right and Left Section
    Given Open "Different elements" Page
    Then Right Section displayed
    And Left Section displayed

  Scenario: Select checkboxes
    Given Open "Different elements" Page
    When Select checkboxes "Water" and "Wind"
    Then Checkboxes "Water" and "Wind" are selected
    And Status of checkboxes in Log row are displayed and corresponding
    Then Checkboxes "Water" and "Wind" are unselected
    And Status of checkboxes in Log row are displayed and corresponding

  Scenario: Select radio
    Given Open "Different elements" Page
    When Click "Selen" radio
    Then Radio "Selen" is selected
    And Status of radios in Log row is displayed and corresponding

  Scenario: Select in dropdown
    Given Open "Different elements" Page
    When Select "Yellow" in dropdown
    Then "Yellow" in dropdown is selected
    And Status of dropdown in Log row is displayed and corresponding
