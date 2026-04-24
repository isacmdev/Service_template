package proyect.template.unit.infrastructure;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import proyect.template.infrastructure.exception.ProblemExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProblemExceptionHandlerTest {

    @Mock
    private Tracer tracer;

    @Mock
    private Span span;

    @InjectMocks
    private ProblemExceptionHandler handler;

    @Test
    void process_returnsEntityUnchanged_whenEntityIsNull() {
        ResponseEntity<Problem> result = handler.process(null);
        assertNull(result);
    }

    @Test
    void process_returnsEntityUnchanged_whenNoActiveSpan() {
        ResponseEntity<Problem> entity = ResponseEntity.ok(Problem.valueOf(Status.OK));
        when(tracer.currentSpan()).thenReturn(null);

        ResponseEntity<Problem> result = handler.process(entity);
        assertEquals(entity, result);
    }

    // test for active span omitted due to tracing implementation types in test classpath
}


