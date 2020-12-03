package com.course.springboot.services.config.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class RestErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RestErrorHandler.class.getName());
    private final MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Process Field Errors
     *
     * @param fieldErrors List
     * @return ValidationErrorDTO
     */
    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

    /**
     * Resolve Localised Errors
     *
     * @param fieldError FieldError
     * @return String
     */
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

    /**
     * MethodArgumentNotValidException
     *
     * @param ex MethodArgumentNotValidException
     * @return ValidationErrorDTO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        LOG.error(ex.getMessage());
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    /**
     * RestException
     *
     * @param response HttpServletResponse
     * @param ex       RestException
     * @return RestExceptionInfo
     */
    @ExceptionHandler(RestException.class)
    @ResponseBody
    public RestExceptionInfo exceptionHandler(HttpServletResponse response, RestException ex) {
        LOG.error(ex.getInfo().name() + ": " + ex.getInfo().getMessage());
        response.setStatus(ex.getInfo().getHttpStatus().value());
        return new RestExceptionInfo(ex.getInfo().getHttpStatus(), ex.getInfo().getId(), ex.getInfo().getMessage());
    }
}