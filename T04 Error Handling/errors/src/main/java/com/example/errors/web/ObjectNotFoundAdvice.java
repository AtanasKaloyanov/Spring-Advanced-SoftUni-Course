package com.example.errors.web;

import com.example.errors.model.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFoundAdvice {
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView onObjectNotFound(ObjectNotFoundException onfe) {

        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", onfe.getObjectId());

        return modelAndView;
    }
}

