package proyect.template.unit.domain;

import org.junit.jupiter.api.Test;
import proyect.template.domain.exception.WidgetConflictProblem;
import org.zalando.problem.Status;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WidgetConflictProblemTest {

    @Test
    void constructor_setsPropertiesCorrectly() {
        WidgetConflictProblem problem = new WidgetConflictProblem("Widget ya existe");

        assertEquals(Status.CONFLICT, problem.getStatus());
        assertEquals("Conflict", problem.getTitle());
        assertEquals("Widget ya existe", problem.getDetail());
        assertEquals(URI.create("/errors/conflict"), problem.getType());
    }
}

