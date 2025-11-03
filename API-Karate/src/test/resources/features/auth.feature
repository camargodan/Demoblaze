Feature: Pruebas de Autenticación en Demoblaze API
  Como QA Engineer
  Quiero validar los endpoints de signup y login
  Para asegurar el correcto funcionamiento del sistema de autenticación


  Background:
    * url baseUrl = 'https://api.demoblaze.com'
    * def randomUser = 'user' + java.lang.System.currentTimeMillis()
    * def randomPass = 'Pass1234!'


  @signup
  Scenario: Crear un nuevo usuario exitosamente
    Given path 'signup'
    And request { username: '#(randomUser)', password: '#(randomPass)' }
    When method post
    Then status 200

  @signup
  Scenario: Intentar crear un usuario que ya existe
    Given path 'signup'
    And request { username: 'usuarioExistente123', password: 'password123' }
    When method post
    Then status 200
    And match response == { errorMessage: 'This user already exist.' }
    * print 'Error esperado - Usuario ya existe'


  @login
  Scenario: Login exitoso con credenciales válidas
    Given path 'signup'
    And request { username: '#(randomUser)', password: '#(randomPass)' }
    When method post
    Then status 200


    # Login
    Given path 'login'
    And request { username: '#(randomUser)', password: '#(randomPass)' }
    When method post
    Then status 200
    And match response contains 'Auth_token:'
    * def authToken = response.replace('Auth_token: ', '')
    * print 'Login exitoso. Token:', authToken


  @login
  Scenario: Login fallido con usuario correcto y password incorrecto
    Given path 'login'
    And request { username: 'testUser', password: 'wrongPassword123' }
    When method post
    Then status 200
    And match response == { errorMessage: 'Wrong password.' }
    * print 'Error esperado - Password incorrecto'


  @login
  Scenario: Login fallido con usuario inexistente
    Given path 'login'
    And request { username: 'usuarioNoExiste999', password: 'password123' }
    When method post
    Then status 200
    And match response == { errorMessage: 'User does not exist.' }
    * print 'Error esperado - Usuario no existe'


  @signup
  Scenario Outline: Pruebas de signup con diferentes combinaciones
    Given path 'signup'
    And request { username: '<username>', password: '<password>' }
    When method post
    Then status 200
    * print 'Resultado para', '<username>:', response


    Examples:
      | username          | password      |
      | userTest001       | Pass12345!    |
      | userTest002       | 12345678      |


  @login
  Scenario Outline: Pruebas de login con diferentes escenarios
    Given path 'login'
    And request { username: '<username>', password: '<password>' }
    When method post
    Then status <expectedStatus>
    * print 'Test:', '<description>'


    Examples:
      | username       | password   | expectedStatus | description                    |
      | validUser      | validPass  | 200           | Login con credenciales válidas |
      | invalidUser    | anyPass    | 200           | Usuario inexistente            |
