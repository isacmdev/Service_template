package proyect.template.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Widget {
    public UUID id;
    public String fullname;
    public Instant  createdAt;
    public Instant updatedAt;

}
