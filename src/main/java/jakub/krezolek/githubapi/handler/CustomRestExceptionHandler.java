package jakub.krezolek.githubapi.handler;

import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
        ErrorMessage errorMessage = new ErrorMessage(status.toString(), "This Api only accepts media type application/json");

        return createResponseEntity(errorMessage, headers, status, request);
    }

//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    public ResponseEntity<ErrorMessage> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, WebRequest request) {
//        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, "This Api only accepts media type application/json");
//
//        return createResponseEntity(errorMessage, new HttpHeaders(), HttpStatusCode.valueOf(406), request);
//    }
}
