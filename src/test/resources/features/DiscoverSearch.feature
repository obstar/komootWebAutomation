Feature: DiscoverSearch

  Background:
    Given existing user is logged in
    Then user visit Discover page

  Scenario: DiscoverSearch - Search in Porto for activity by easy difficulty
    Given I search for 'Porto' on Discover page
    When I chose activity by 'easy' difficulty
    Then I see tours around 'Porto'
      And first tour has difficulty 'easy'

 # Scenario: DiscoverSearch - Search for tour by duration
    Given I search for 'Inn' on Discover page
  #Scenario: DiscoverSearch - Search for run within distance radius