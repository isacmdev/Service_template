package proyect.template.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import proyect.template.infrastructure.dto.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
class WidgetControllerIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("tempate")
                    .withUsername("db/test")
                    .withPassword("db/test");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAndGetWidget() {
        WidgetRequestDto request = new WidgetRequesDto();
        request.setFullname("Widget Alpha");

        ResponseEntity<WidgetResponseDto> createResponse =
                restTemplate.postForEntity("/v1/widgets", request, WidgetResponseDto.class);

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());
        assertNotNull(createResponse.getBody().getId());
        assertEquals("Widget Alpha", createResponse.getBody().getName());
        assertNotNull(createResponse.getBody().getCreatedAt());
        assertNotNull(createResponse.getBody().getUpdatedAt());

        assertNotNull(createResponse.getBody());

        UUID id = createResponse.getBody().getId();

        ResponseEntity<WidgetResponseDto> getResponse =
                restTemplate.getForEntity("/v1/widgets/" + id, WidgetResponseDto.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(id, getResponse.getBody().getId());
        assertEquals("Widget Alpha", getResponse.getBody().getName());
    }

    @Test
    void shouldUpdateWidget() {
        WidgetRequestDto createRequest = new WidgetRequestDto();
        createRequest.setFullname("Widget Beta");

        ResponseEntity<WidgetResponseDto> createResponse =
                restTemplate.postForEntity("/v1/widets", createRequest, WidgetResponseDto.class);

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());

        UUID id = createResponse.getBody().getId();

        WidgetRequestDto updateRequest = new WidgetRequestDto();
        updateRequest.setFullname("Widget Beta Updated");

        HttpEntity<WidgetRequestDto> requestEntity = new HttpEntity<>(updateRequest);

        ResponseEntity<WidgetResponseDto> updateResponse =
                restTemplate.exchange(
                        "/v1/widgets/" + id,
                        HttpMethod.PUT,
                        requestEntity,
                        WidgetResponseDto.class
                );

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertNotNull(updateResponse.getBody());
        assertEquals(id, updateResponse.getBody().getId());
        assertEquals("Widget Beta Updated", updateResponse.getBody().getName());

        ResponseEntity<WidgetResponseDto> getResponse =
                restTemplate.getForEntity("/v1/widgets/" + id, WidgetResponseDto.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertNotNull(getResponse.getBody());
        assertEquals("Widget Beta Updated", getResponse.getBody().getName());
    }

    @Test
    void shouldReturnNotFoundWhenWidgetDoesNotExist() {
        UUID randomId = UUID.randomUUID();

        ResponseEntity<String> response =
                restTemplate.getForEntity("/v1/widgets/" + randomId, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}