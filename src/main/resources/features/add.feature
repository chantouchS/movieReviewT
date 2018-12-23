Feature: add
    As a manager
    I want to add new movie
    so that I can write movie name

Scenario: add movie with correct id
    Given a manager with id admin and password admin
    When I login to Account with id admin and password admin
    Then I can add new movie

Scenario: add movie with incorrect id
    Given a manager with id admin and password admin
    When I login to Account with id admin and password admim
    Then I cannot add new movie
