package proyect.template.domain.ports;

import proyect.template.domain.entity.Widget;

import java.util.Optional;
import java.util.UUID;

public interface WidgetPortOut {
    Optional<Widget> findById(UUID id);
    Widget save(Widget widget);
    Widget update(UUID id, Widget widget);
}
