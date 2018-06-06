package exception;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.charset.Charset;

@ControllerAdvice()
public class RestExceptionHandler{

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @ExceptionHandler(value={ServiceException.class,
                             ControlException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleServiceException(Exception exception) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text","html", Charset.forName("utf-8"));
        headers.setContentType(mediaType);
        return new ResponseEntity<>(exception.getMessage(),headers,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception exception) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text","html", Charset.forName("utf-8"));
        headers.setContentType(mediaType);
        return new ResponseEntity<>(exception.getMessage(),headers,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
