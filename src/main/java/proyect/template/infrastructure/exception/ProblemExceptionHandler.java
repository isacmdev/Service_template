package proyect.template.infrastructure.exception;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.routing.NoHandlerFoundAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.MethodArgumentNotValidAdviceTrait;

@ControllerAdvice
public class ProblemExceptionHandler implements
        ProblemHandling,
        NoHandlerFoundAdviceTrait,
        ConstraintViolationAdviceTrait,
        MethodArgumentNotValidAdviceTrait {

    private final Tracer tracer;

    public ProblemExceptionHandler(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity) {
        if (entity == null || entity.getBody() == null) {
            return entity;
        }

        Span span = tracer.currentSpan();
        if (span == null) {
            return entity;
        }

        Problem problem = entity.getBody();

        ProblemBuilder builder = Problem.builder()
                .withType(problem.getType())
                .withTitle(problem.getTitle())
                .withStatus(problem.getStatus())
                .withDetail(problem.getDetail())
                .withInstance(problem.getInstance());

        problem.getParameters().forEach(builder::with);

        builder.with("traceId", span.context().traceId());
        builder.with("spanId", span.context().spanId());

        return ResponseEntity
                .status(entity.getStatusCode())
                .headers(entity.getHeaders())
                .body(builder.build());
    }
}