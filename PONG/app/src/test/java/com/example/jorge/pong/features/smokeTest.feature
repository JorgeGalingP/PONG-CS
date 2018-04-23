
Feature: player activity
    I must be in the app
    
Scenario: The shot is enable
    Given gameplay screen smoke
    When can shot
    Then shot enabled

Scenario: The player wins
    Given gameplay screen smoke
    When player wins
    Then finish current game

Scenario: The game over works
    Given gameplay screen smoke
    When player looses
    Then finish current game

Scenario: The game finish
    Given gameplay screen smoke
    When player finish
    Then finish current game

Sceneario: The VictoryActivity shows
    Given gameplay screen smoke
    When player wins
    Then VictoryActivity shows

