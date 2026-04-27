package proyect.template.domain.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.UUID;

public class WidgetNotFoundProblem extends AbstractThrowableProblem {

    public WidgetNotFoundProblem(UUID id) {
        super(
                URI.create("/errors/not-found"),
                "Resource not found",
                Status.NOT_FOUND,
                "Widget not found with id: " + id
        );
    }
}