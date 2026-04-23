package proyect.template.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyect.template.domain.entity.Widget;
import proyect.template.domain.exception.WidgetNotFoundProblem;
import proyect.template.domain.ports.WidgetPortIn;
import proyect.template.domain.ports.WidgetPortOut;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WidgetServiceUseCase implements WidgetPortIn {
    private WidgetPortOut widgetPortOut;

    int x = "esto rompe";

    @Override
    public Widget getById(UUID id) {
        if (id == null) throw new IllegalArgumentException("El id no puede ser null");
        return widgetPortOut.findById(id)
                .orElseThrow(() -> new WidgetNotFoundProblem(id));
    }

    @Override
    public Widget create(Widget widget) {
         widget.setCreatedAt(Instant.now());
         widget.setUpdatedAt(Instant.now());
        return widgetPortOut.save(widget);
    }

    @Override
    public Widget update(UUID id, Widget widget) {
        Widget existingWidget = widgetPortOut.findById(id)
                .orElseThrow(() -> new WidgetNotFoundProblem(id));

        existingWidget.setFullname(widget.getFullname());
        existingWidget.setUpdatedAt(Instant.now());

        return widgetPortOut.update(id, existingWidget);
    }
}