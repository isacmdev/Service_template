package proyect.template.infrastructure.repository.mapper;

import proyect.template.domain.entity.Widget;
import proyect.template.infrastructure.repository.entity.WidgetData;

public class WidgetEntityMapper {

    public static WidgetData toData (Widget widget) {
        return WidgetData.builder()
                .id(widget.getId())
                .fullname(widget.getFullname())
                .createdAt(widget.getCreatedAt())
                .updatedAt(widget.getUpdatedAt())
                .build();
    }

    public static Widget toDomain (WidgetData data) {
        return Widget.builder()
                .id(data.getId())
                .fullname(data.getFullname())
                .createdAt(data.getCreatedAt())
                .updatedAt(data.getUpdatedAt())
                .build();
    }
}
