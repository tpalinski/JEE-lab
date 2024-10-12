package com.example.warhammer.config.listener;

import com.example.warhammer.config.AppConfig;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CreateAppConfig implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    event.getServletContext().setAttribute("config", new AppConfig());
  }
}
