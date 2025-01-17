package com.jtp.hr.common.exception;

import com.jtp.hr.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;


@RestController
public class RestErrorController implements ErrorController {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public ResponseEntity<?> handleError(WebRequest webRequest) {
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest);
        String error = (String) errorAttributes.get("error");
        int status = (int) errorAttributes.get("status");
        String message = (String) errorAttributes.get("message");
        String path = (String) errorAttributes.get("path");
        logger.error("Error occurred while access[{}]:{}", path, error);
        ErrorRepresentation.ErrorDetail errorDetail = new ErrorRepresentation.ErrorDetail(DefaultErrorCode.SYSTEM_ERROR.getCode(),
                status,
                error,
                path,
                of("detail", message));

        return new ResponseEntity<>(new ErrorRepresentation(errorDetail), new HttpHeaders(), HttpStatus.valueOf(status));
    }

    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
    }
}
