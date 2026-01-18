package co.com.sofka.luchotest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteControllerIntegrationTest {

    private RestTemplate restTemplate = new RestTemplate();
    
    @LocalServerPort
    private int port;

    private String initialClienteId = "CLI-TEST-" + System.currentTimeMillis();
    
    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    @DisplayName("Should create a cliente successfully when valid data is provided")
    void shouldCreateClienteSuccessfully() throws Exception {

        var randomClienteId = "CLI-TEST-" + System.currentTimeMillis();
        // Arrange
        String requestJson = "{" +
            "\"nombre\": \"John Doe\"," +
            "\"genero\": \"Masculino\"," +
            "\"edad\": 30," +
            "\"identificacion\": \"123456789\"," +
            "\"direccion\": \"Calle 123\"," +
            "\"telefono\": \"5551234\"," +
            "\"clienteId\": \"" + randomClienteId + "\"," +
            "\"contrasena\": \"securePass\"," +
            "\"estado\": \"Activo\"" +
            "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("testuser", "password");
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        // Act
        ResponseEntity<String> response = restTemplate.postForEntity(
            getBaseUrl() + "/clientes", entity, String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        // Validate response content contains expected data
        String responseBody = response.getBody();
        assertTrue(responseBody.contains("John Doe"), "Response should contain cliente name");
        assertTrue(responseBody.contains("Masculino"), "Response should contain gender");
        assertTrue(responseBody.contains("123456789"), "Response should contain identification");
        assertTrue(responseBody.contains("\"id\""), "Response should contain generated ID");
    }

    @Test
    @DisplayName("Should return validation error when invalid data is provided")
    void shouldReturnValidationError() {
        // Arrange
        String requestJson = "{" +
            "\"nombre\": \"\"," +
            "\"genero\": \"\"," +
            "\"edad\": -1," +
            "\"identificacion\": \"\"," +
            "\"direccion\": \"\"," +
            "\"telefono\": \"\"," +
            "\"clienteId\": \"\"," +
            "\"contrasena\": \"\"," +
            "\"estado\": \"\"" +
            "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("testuser", "password");
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        try {
            // Act
            ResponseEntity<String> response = restTemplate.postForEntity(
                getBaseUrl() + "/clientes", entity, String.class);
            
            // This should not happen - we expect a 400 Bad Request
            fail("Expected validation error but got: " + response.getStatusCode());
        } catch (Exception e) {
            // This is expected for validation errors
            assertTrue(e.getMessage().contains("400"));
            assertTrue(e.getMessage().contains("Valor de Campo inválido"));
        }
    }

    @Test
    @DisplayName("Should update a cliente successfully")
    void shouldUpdateClienteSuccessfully() throws Exception {
        // First create a cliente to update
        Long clienteId = createTestCliente();

        var randomClienteId = "CLI-TEST-" + System.currentTimeMillis();
        
        // Arrange
        String requestJson = "{" +
            "\"nombre\": \"Jane Doe Updated\"," +
            "\"genero\": \"Femenino\"," +
            "\"edad\": 28," +
            "\"identificacion\": \"987654321\"," +
            "\"direccion\": \"Calle 456\"," +
            "\"telefono\": \"5555678\"," +
            "\"clienteId\": \"" + randomClienteId + "\"," +
            "\"contrasena\": \"newSecurePass\"," +
            "\"estado\": \"Activo\"" +
            "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("testuser", "password");
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        // Act
        ResponseEntity<String> response = restTemplate.exchange(
            getBaseUrl() + "/clientes/" + clienteId, HttpMethod.PUT, entity, String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        // Validate response content contains updated data
        String responseBody = response.getBody();
        assertTrue(responseBody.contains("Jane Doe Updated"), "Response should contain updated name");
        assertTrue(responseBody.contains("Femenino"), "Response should contain updated gender");
        assertTrue(responseBody.contains("987654321"), "Response should contain updated identification");
        assertTrue(responseBody.contains("\"edad\":28"), "Response should contain updated age");
    }

    @Test
    @DisplayName("Should delete a cliente successfully")
    void shouldDeleteClienteSuccessfully() throws Exception {
        // First create a cliente to delete
        Long clienteId = createTestCliente();
        
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("testuser", "password");
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Act
        ResponseEntity<String> response = restTemplate.exchange(
            getBaseUrl() + "/clientes/" + clienteId, HttpMethod.DELETE, entity, String.class);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        
        // Verify the cliente was actually deleted by trying to fetch it
        try {
            restTemplate.exchange(
                getBaseUrl() + "/clientes/" + clienteId, HttpMethod.GET, entity, String.class);
            fail("Expected cliente to be deleted, but it still exists");
        } catch (Exception e) {
            // This is expected - the cliente should not be found
            assertTrue(e.getMessage().contains("404") || e.getMessage().contains("Not Found"));
        }
    }
    
    // Helper method to create a test cliente and return its ID
    private Long createTestCliente() throws Exception {

        String requestJson = "{" +
            "\"nombre\": \"Test Cliente\"," +
            "\"genero\": \"Masculino\"," +
            "\"edad\": 30," +
            "\"identificacion\": \"123456789\"," +
            "\"direccion\": \"Test Address\"," +
            "\"telefono\": \"5551234\"," +
            "\"clienteId\": \"" + initialClienteId + "\"," +
            "\"contrasena\": \"testPass\"," +
            "\"estado\": \"Activo\"" +
            "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("testuser", "password");
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            getBaseUrl() + "/clientes", entity, String.class);

        // Extract ID from response using string manipulation
        String responseBody = response.getBody();
        // Find the ID value in the JSON response like: "id":1
        String idPattern = "\"id\":[0-9]+";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(idPattern);
        java.util.regex.Matcher matcher = pattern.matcher(responseBody);
        if (matcher.find()) {
            String idStr = matcher.group().replaceAll("[^0-9]", "");
            return Long.parseLong(idStr);
        }
        throw new RuntimeException("Could not extract ID from response: " + responseBody);
    }
}
