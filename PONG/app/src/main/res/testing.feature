Feature: player activity
    I must be in the app

  Scenario: Move the paddle to the right
      Given gameplay screen
      When I tap the right of the screen
      Then The paddle moves to the right 1 unit

  Scenario: Move the paddle to the left
      Given gameplay screen
      When I tap the left of the screen
      Then The paddle moves to the left 1 unit

  Scenario: Move the paddle to the top
      Given gameplay screen
      When I tap the top of the screen
      Then The paddle moves to the top 1 unit

  Scenario: Move the paddle to the bottom
      Given gameplay screen
      When I tap the bottom of the screen
      Then The paddle moves to the bottom 1 unit

  Scenario: Player shoot
      Given gameplay screen
      When I tap the paddle
      Then The paddle fires a bullet
      