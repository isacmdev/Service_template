package proyect.template.infrastructure.mapper;

import proyect.template.domain.entity.Widget;
import proyect.template.infrastructure.dto.WidgetRequestDto;
import proyect.template.infrastructure.dto.WidgetResponseDto;

public class WidgetMapperDto {

    public static Widget toDomain(WidgetRequestDto dto) {
        return Widget.builder()
                .fullname(dto.getFullname())
                .build();
    }

    public static WidgetResponseDto toResponse (Widget widget) {
        return WidgetResponseDto.builder()
                .id(widget.getId())
                .name(widget.getFullname())
                .createdAt(widget.getCreatedAt())
                .updatedAt(widget.getUpdatedAt())
                .build();
    }
}