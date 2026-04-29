package proyect.template.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyect.template.domain.entity.Widget;
import proyect.template.domain.ports.WidgetPortIn;
import proyect.template.infrastructure.dto.WidgetRequestDto;
import proyect.template.infrastructure.dto.WidgetResponseDto;
import proyect.template.infrastructure.mapper.WidgetMapperDto;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
@AllArgsConstructor
public class WidgetController {

    private final WidgetPortIn widgetPortIn;

    @Operation(summary = "Health widget")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Active service"),
            @ApiResponse(responseCode = "503", description = "Service not available")
    })
    @GetMapping("/healthz")
    public Map<String, String> healthCheck() {
        return Map.of("status", "ok");
    }

    @GetMapping("/test-500")
    public String test500() {
        throw new RuntimeException("boom");
    }
    @Operation(summary = "Create widget")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Widget created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Conflict"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping("/v1/widgets")
    public ResponseEntity<WidgetResponseDto> createWidget(
           @Valid @RequestBody WidgetRequestDto widgetRequestDto
    ) { 
        Widget toDomain = WidgetMapperDto.toDomain(widgetRequestDto);
        Widget createdWidget = widgetPortIn.create(toDomain);
        WidgetResponseDto savedWidget = WidgetMapperDto.toResponse(createdWidget);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWidget);
    }

    @Operation(summary = "Search widget by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget  found"),
            @ApiResponse(responseCode = "400", description = "ID inválid"),
            @ApiResponse(responseCode = "404", description = "Widget  not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/v1/widgets/{id}")
    public ResponseEntity<WidgetResponseDto> getWidgetById(@PathVariable UUID id) {
        Widget widget = widgetPortIn.getById(id);
        WidgetResponseDto responseDto = WidgetMapperDto.toResponse(widget);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Update widget")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget found"),
            @ApiResponse(responseCode = "404", description = "Widget not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PutMapping("/v1/widgets/{id}")
    public ResponseEntity<WidgetResponseDto> updateWidget(
            @PathVariable UUID id,
            @Valid
            @RequestBody WidgetRequestDto widgetRequestDto
     ) {
        Widget toDomain = WidgetMapperDto.toDomain(widgetRequestDto);
        Widget updatedWidget = widgetPortIn.update(id, toDomain);

        WidgetResponseDto responseDto = WidgetMapperDto.toResponse(updatedWidget);
        return ResponseEntity.ok(responseDto);
     }
}