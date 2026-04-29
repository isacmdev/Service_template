package proyect.template.infrastructure.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyect.template.infrastructure.repository.entity.WidgetData;

import java.util.UUID;

public interface WidgetJpaRepository extends JpaRepository<WidgetData, UUID> {
}
