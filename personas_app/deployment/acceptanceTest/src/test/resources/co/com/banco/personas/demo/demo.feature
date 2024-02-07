# We suggest to consulting the Karate Framework documentation: https://github.com/intuit/karate
@acceptanceTest
Feature: Here is the full description of the test suite to be run DEMO

    How     ...
    Required ...
    To     ...


    Background:
        # This property is taken from the karate-config.js file
        * url urlBase
        # This property is taken from the karate-config.js file
        * def oasUrl = oasUrl
        # This property is taken from the karate-config.js file
        * configure headers = headers
        * def ValidatorTestUtils = Java.type('co.com.banco.personas.utils.ValidatorTestUtils')
    # * def json = read('classpath:more-json.json') -> you can declare a variable that calls a json to be used as request


    #   Reference
    #   https://github.com/karatelabs/karate#request
    #   https://github.com/karatelabs/karate#reading-files
    Scenario: This is the description of this scenario to be tested and its objective
        Given path 'clientes'
        And request read('addClient.json')
        When method post
        Then status 200