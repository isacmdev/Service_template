package proyect.template.unit.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyect.template.application.WidgetServiceUseCase;
import proyect.template.domain.entity.Widget;
import proyect.template.domain.exception.WidgetNotFoundProblem;
import proyect.template.domain.ports.WidgetPortOut;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WidgetServiceUseCaseTest {
    @Mock
    private WidgetPortOut widgetPortOut;
    @InjectMocks
    private WidgetServiceUseCase widgetServiceUseCase;

    private Widget widget;
    private UUID widgetId;

    @BeforeEach
    void setUp() {
        widgetId = UUID.randomUUID();
        widget = Widget.builder()
                .id(widgetId)
                .fullname("Test Widget")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    void getById_returnsWidget_whenFound() {
        when(widgetPortOut.findById(widgetId)).thenReturn(Optional.of(widget));
        Widget result = widgetServiceUseCase.getById(widgetId);
        assertNotNull(result);
        assertEquals(widgetId, result.getId());
        verify(widgetPortOut).findById(widgetId);
    }

    @Test
    void getById_throwsException_whenNotFound() {
        when(widgetPortOut.findById(widgetId)).thenReturn(Optional.empty());
        assertThrows(WidgetNotFoundProblem.class, () -> widgetServiceUseCase.getById(widgetId));
        verify(widgetPortOut).findById(widgetId);
    }

    @Test
    void getById_throwsException_whenIdIsNull() {
        // Espera IllegalArgumentException si el id es null
        assertThrows(IllegalArgumentException.class, () -> widgetServiceUseCase.getById(null));
        verify(widgetPortOut, never()).findById(any());
    }

    @Test
    void create_setsTimestampsAndSavesWidget() {
        // Arrange
        Widget inputWidget = Widget.builder()
                .fullname("Nuevo")
                .build();

        ArgumentCaptor<Widget> captor = ArgumentCaptor.forClass(Widget.class);

        when(widgetPortOut.save(any(Widget.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Widget result = widgetServiceUseCase.create(inputWidget);

        // Assert
        verify(widgetPortOut).save(captor.capture());

        Widget saved = captor.getValue();

        assertEquals("Nuevo", saved.getFullname());
        assertNotNull(saved.getCreatedAt());
        assertNotNull(saved.getUpdatedAt());

        // Opcional pero importante: validar que el resultado también lo tiene
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    void update_updatesWidget_whenFound() {
        Widget updateData = Widget.builder().fullname("Actualizado").build();
        Widget existingWidget = Widget.builder()
                .id(widgetId)
                .fullname("Viejo")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        Widget updatedWidget = Widget.builder()
                .id(widgetId)
                .fullname("Actualizado")
                .createdAt(existingWidget.getCreatedAt())
                .updatedAt(Instant.now())
                .build();
        when(widgetPortOut.findById(widgetId)).thenReturn(Optional.of(existingWidget));
        when(widgetPortOut.update(eq(widgetId), any(Widget.class))).thenReturn(updatedWidget);

        Widget result = widgetServiceUseCase.update(widgetId, updateData);
        assertEquals("Actualizado", result.getFullname());
        verify(widgetPortOut).findById(widgetId);
        verify(widgetPortOut).update(eq(widgetId), any(Widget.class));
    }

    @Test
    void update_throwsException_whenWidgetNotFound() {
        UUID id = UUID.randomUUID();

        when(widgetPortOut.findById(id)).thenReturn(Optional.empty());

        Widget updateData = Widget.builder()
                .fullname("Nuevo")
                .build();

        assertThrows(WidgetNotFoundProblem.class,
                () -> widgetServiceUseCase.update(id, updateData));

        verify(widgetPortOut).findById(id);
        verify(widgetPortOut, never()).update(any(), any());
    }
}
