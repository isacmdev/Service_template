package proyect.template.unit.infrastructure.repository;

import org.junit.jupiter.api.Test;
import proyect.template.infrastructure.repository.exception.DatabaseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseExceptionTest {

    @Test
    void constructor_setsMessageAndCause() {
        Throwable cause = new RuntimeException("causa original");
        DatabaseException ex = new DatabaseException("Error de DB", cause);

        assertEquals("Error de DB", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}

