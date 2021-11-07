Feature: Users

    Scenario: Get list of users
        Given an external datasource
        When I request "api/users" endpoint
        Then I should see a list of users

    Scenario: Get a user by id
        Given an external datasource
        When I request "api/users/1" endpoint
        Then I should see a list of users

    Scenario: Insert a new user
        Given an external datasource
        When I request "api/users" endpoint
        Then I should see a list of users

    Scenario: Update a user by id
        Given an external datasource
        When I request "api/users/1" endpoint
        Then I should see a list of users

    Scenario: Delete a user by id
        Given an external datasource
        When I request "api/users/1" endpoint
        Then I should see a list of users
