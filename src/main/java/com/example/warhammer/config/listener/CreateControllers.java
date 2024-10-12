package com.example.warhammer.config.listener;

import com.example.warhammer.config.AppConfig;
import com.example.warhammer.controller.user.SimpleUserController;
import com.example.warhammer.dto.user.UserDTOFactory;
import com.example.warhammer.service.user.UserService;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        UserService userService = (UserService) event.getServletContext().getAttribute("userService");
        AppConfig config = (AppConfig) event.getServletContext().getAttribute("config");

        event.getServletContext().setAttribute("userController", new SimpleUserController (
                userService,
                new UserDTOFactory(),
                config
        ));

    }
}
