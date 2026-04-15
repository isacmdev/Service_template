package proyect.template.infrastructure.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
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
}