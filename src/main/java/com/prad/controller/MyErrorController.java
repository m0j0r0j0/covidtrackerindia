package com.prad.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController  {

    @RequestMapping("/error")
    public String handleError(Model m,HttpServletResponse response,HttpServletRequest request) {
        
    	/*int httpErrorCode = getErrorCode(request);
    	System.out.println("stat:"+response.getStatus());
    	System.out.println("httpErrorCode:"+httpErrorCode);
    	if (httpErrorCode == HttpStatus.UNAUTHORIZED.value()) {
    		return "dashboard";
        }*/
    	
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
    
    
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}