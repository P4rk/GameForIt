package com.lukecorpe.gameforit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class MainControllerAdvice {

    @Autowired
    public AssertLocator assertLocator;
    
    @ModelAttribute("assets")
    public AssertLocator assetLocator(){
        return assertLocator;
        
    }
}
