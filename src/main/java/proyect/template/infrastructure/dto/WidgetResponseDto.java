package proyect.template.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WidgetResponseDto {
    public UUID id;
    public String name;
    public Instant createdAt;
    public Instant updatedAt;
}
