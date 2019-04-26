package org.bllose.demoweb.controller;

import org.bllose.demoweb.model.dto.OrderDto;
import org.bllose.demoweb.repository.RepositoryDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class DemoController {

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<String> helloWorld(String hello){
        logger.debug("收到"+hello);
        List<OrderDto> orderDtoList = repositoryDemo.searchTop5();
        List<String> resultList = new ArrayList<>();
        resultList.add(hello);
        resultList.add("DONE!");
        return resultList;
    }

    @Autowired
    private RepositoryDemo repositoryDemo;

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
}
