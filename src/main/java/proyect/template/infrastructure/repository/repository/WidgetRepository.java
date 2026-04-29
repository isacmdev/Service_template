package proyect.template.infrastructure.repository.repository;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import proyect.template.domain.entity.Widget;
import proyect.template.domain.exception.WidgetConflictProblem;
import proyect.template.domain.ports.WidgetPortOut;
import proyect.template.infrastructure.repository.exception.DatabaseException;
import proyect.template.infrastructure.repository.entity.WidgetData;
import proyect.template.infrastructure.repository.mapper.WidgetEntityMapper;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class WidgetRepository implements WidgetPortOut {
    private final WidgetJpaRepository widgetJpaRepository;

    @Override
    public Optional<Widget> findById(UUID id) {
        try {
            return widgetJpaRepository.findById(id)
                    .map(WidgetEntityMapper::toDomain);
        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing DB", e);
        }
    }

    @Override
    public Widget save(Widget widget) {
        try {
            WidgetData widgetData = WidgetEntityMapper.toData(widget);
            WidgetData saved = widgetJpaRepository.save(widgetData);
            return WidgetEntityMapper.toDomain(saved);

        } catch (DataIntegrityViolationException e) {
            throw new WidgetConflictProblem("Widget already exists");
        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing DB", e);
        }
    }

    @Override
    public Widget update(UUID id, Widget widget) {
        try {
            WidgetData data = WidgetEntityMapper.toData(widget);
            WidgetData saved = widgetJpaRepository.save(data);
            return WidgetEntityMapper.toDomain(saved);

        } catch (DataAccessException e) {
            throw new DatabaseException("Error updating widget", e);
        }
    }
}