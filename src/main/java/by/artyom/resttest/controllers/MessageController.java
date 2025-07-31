package by.artyom.resttest.controllers;

import by.artyom.resttest.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @RequestMapping("/hello")
    public Message sayHello() {
        return new Message("Hello World");
    }

}
