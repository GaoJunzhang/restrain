package com.wx.restrain.controller;

import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2018/1/30.
 */
public class BaseController {
    public String getMessage(HttpServletRequest request, String message) {
        RequestContext requestContext = new RequestContext(request);
        return requestContext.getMessage(message);
    }
}
