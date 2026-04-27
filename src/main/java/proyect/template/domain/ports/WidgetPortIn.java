package proyect.template.domain.ports;

import proyect.template.domain.entity.Widget;

import java.util.UUID;

public interface WidgetPortIn {
    Widget getById(UUID id);
    Widget create(Widget widget);
    Widget update(UUID id, Widget widget);
}
