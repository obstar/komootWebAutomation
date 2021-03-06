Feature: DiscoverSearch

  Background:
    Given existing user is logged in
    Then user visit Discover page

  Scenario: DiscoverSearch - Search in Porto for activity by easy difficulty
    Given I search for 'Porto' on Discover page
    When I chose activity by 'easy' difficulty
    Then I see tours around 'Porto'
    And first tour has difficulty 'easy'

  Scenario: DiscoverSearch - Search in Innsbruck for tour by duration between 6 and 8 hours
    Given I search for 'Innsbruck' on Discover page
    When I chose at least 6 hours tour duration
    Then I see tours around 'Innsbruck'
      And first tour has tour duration at least 6 hours

  Scenario: DiscoverSearch - Search in Gliwice for run within distance of 10 km radius
    Given I search for 'Gliwice' on Discover page
    When I chose running activity
      And I chose within '10' km radius
    Then I see tours around 'Gliwice'
      And first tour has in title 'Running'