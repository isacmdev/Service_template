package proyect.template.unit.mapper;

import org.junit.jupiter.api.Test;
import proyect.template.infrastructure.dto.WidgetRequestDto;
import proyect.template.infrastructure.dto.WidgetResponseDto;
import proyect.template.infrastructure.mapper.WidgetMapperDto;
import proyect.template.domain.entity.Widget;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WidgetMapperDtoTest {

    @Test
    void toDomain_mapsFullnameCorrectly() {
        WidgetRequestDto dto = new WidgetRequestDto("Test Widget");
        Widget result = WidgetMapperDto.toDomain(dto);

        assertEquals("Test Widget", result.getFullname());
        assertNull(result.getId());
    }

    @Test
    void toResponse_mapsAllFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        Widget widget = Widget.builder().id(id).fullname("Test").createdAt(now).updatedAt(now).build();

        WidgetResponseDto result = WidgetMapperDto.toResponse(widget);

        assertEquals(id, result.getId());
        assertEquals("Test", result.getName());
        assertEquals(now, result.getCreatedAt());
        assertEquals(now, result.getUpdatedAt());
    }
}

