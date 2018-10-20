package com.jevua.demo.controller;

import com.jevua.demo.service.Service;
import com.jevua.framework.annotation.MyAutowired;
import com.jevua.framework.annotation.MyController;
import com.jevua.framework.annotation.MyRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
@MyRequestMapping("/controller")
public class Controller {

    @MyAutowired
    private Service service;

    @MyRequestMapping("/test.spr")
    public void test() {
        service.test();
    }

    @MyRequestMapping("/add.spr")
    public void add(HttpServletRequest request, HttpServletResponse response, String name) {

    }
}
