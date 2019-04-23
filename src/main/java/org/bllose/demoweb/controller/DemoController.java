package org.bllose.demoweb.controller;

import org.bllose.demoweb.model.dto.OrderDto;
import org.bllose.demoweb.repository.RepositoryDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class DemoController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(String hello){
        System.out.println("收到"+hello);
        List<OrderDto> orderDtoList = repositoryDemo.find();
        System.out.println(orderDtoList);
        return hello;
    }

    @Autowired
    private RepositoryDemo repositoryDemo;
}
