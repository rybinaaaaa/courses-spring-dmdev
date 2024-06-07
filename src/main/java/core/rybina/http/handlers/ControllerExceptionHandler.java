package core.rybina.http.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "core.rybina.http.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.info(e.getMessage());
        return "error";
    }
}
