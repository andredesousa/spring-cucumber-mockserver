Feature: Users

    Scenario: Get a list of users
        Given a resource for "/user/" endpoint via "GET"
        """
        [
            {
                "id": 1,
                "username": "user",
                "email": "user@email.com"
            }
        ]
        """
        When the "/user/" endpoint is requested
        Then a list of users is received
        """
        [
            {
                "id": 1,
                "username": "user",
                "email": "user@email.com"
            }
        ]
        """
        And the resource "/user/" was called

    Scenario: Get a user by id
        Given a resource for "/user/1" endpoint via "GET"
        """
        {
            "id": 1,
            "username": "user",
            "email": "user@email.com"
        }
        """
        When an object is requested via "/user/1" endpoint
        Then a user object is received
        """
        {
            "id": 1,
            "username": "user",
            "email": "user@email.com"
        }
        """
        And the resource "/user/1" was called

    Scenario: Insert a new user
        Given a resource for "/user/" endpoint via "POST"
        """
        {
            "id": 1,
            "username": "user",
            "email": "user@email.com"
        }
        """
        When an object is sent to "/user/" endpoint
        """
        {
            "username": "user",
            "email": "user@email.com"
        }
        """
        Then a user object is received
        """
        {
            "id": 1,
            "username": "user",
            "email": "user@email.com"
        }
        """
        And the resource "/user/" was called

    Scenario: Update a user by id
        Given a resource for "/user/1" endpoint via "PUT"
        """
        {
            "id": 1,
            "username": "other",
            "email": "other@email.com"
        }
        """
        When an object is updated via "/user/1" endpoint
        """
        {
            "id": 1,
            "username": "other",
            "email": "other@email.com"
        }
        """
        Then a successful message is received
        And the resource "/user/1" was called

    Scenario: Delete a user by id
        Given a resource for "/user/1" endpoint via "DELETE"
        """
        {
            "id": 1,
            "username": "user",
            "email": "user@email.com"
        }
        """
        When an object is deleted via "/user/1" endpoint
        Then a successful message is received
        And the resource "/user/1" was called
