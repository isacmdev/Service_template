package proyect.template.domain.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class WidgetConflictProblem extends AbstractThrowableProblem {

    public WidgetConflictProblem(String detail) {
        super(
                URI.create("/errors/conflict"),
                "Conflict",
                Status.CONFLICT,
                detail
        );
    }
}