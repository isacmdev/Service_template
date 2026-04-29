package proyect.template.unit.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyect.template.infrastructure.repository.exception.DatabaseException;
import proyect.template.infrastructure.repository.repository.WidgetRepository;
import proyect.template.infrastructure.repository.repository.WidgetJpaRepository;
import proyect.template.domain.entity.Widget;
import proyect.template.domain.exception.WidgetConflictProblem;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WidgetRepositoryTest {

    @Mock
    private WidgetJpaRepository widgetJpaRepository;

    @InjectMocks
    private WidgetRepository widgetRepository;

    @Test
    void save_throwsWidgetConflictProblem_onDataIntegrityViolation() {
        Widget widget = Widget.builder().fullname("Duplicado").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataIntegrityViolationException("constraint"));

        assertThrows(WidgetConflictProblem.class, () -> widgetRepository.save(widget));
    }

    @Test
    void save_throwsDatabaseException_onDataAccessException() {
        Widget widget = Widget.builder().fullname("Error").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.save(widget));
    }

    @Test
    void findById_throwsDatabaseException_onDataAccessException() {
        UUID id = UUID.randomUUID();
        when(widgetJpaRepository.findById(id)).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.findById(id));
    }

    @Test
    void update_throwsDatabaseException_onDataAccessException() {
        UUID id = UUID.randomUUID();
        Widget widget = Widget.builder().fullname("Update").build();
        when(widgetJpaRepository.save(any())).thenThrow(new DataAccessException("DB error") {});

        assertThrows(DatabaseException.class, () -> widgetRepository.update(id, widget));
    }
}


