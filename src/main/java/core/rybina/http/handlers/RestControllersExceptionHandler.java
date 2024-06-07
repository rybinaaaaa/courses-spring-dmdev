package core.rybina.http.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "core.rybina.http.rest")
public class RestControllersExceptionHandler extends ResponseEntityExceptionHandler {
}
