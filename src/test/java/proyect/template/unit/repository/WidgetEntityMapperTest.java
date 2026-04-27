package proyect.template.unit.repository;

import org.junit.jupiter.api.Test;
import proyect.template.infrastructure.repository.entity.WidgetData;
import proyect.template.infrastructure.repository.mapper.WidgetEntityMapper;
import proyect.template.domain.entity.Widget;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WidgetEntityMapperTest {

    @Test
    void toData_and_toDomain_roundtrip() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        Widget widget = Widget.builder().id(id).fullname("X").createdAt(now).updatedAt(now).build();

        WidgetData data = WidgetEntityMapper.toData(widget);
        assertEquals(id, data.getId());
        assertEquals("X", data.getFullname());

        Widget domain = WidgetEntityMapper.toDomain(data);
        assertEquals(id, domain.getId());
        assertEquals("X", domain.getFullname());
    }
}

