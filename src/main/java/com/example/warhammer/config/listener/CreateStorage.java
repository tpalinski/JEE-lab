package com.example.warhammer.config.listener;

import com.example.warhammer.config.AppConfig;
import com.example.warhammer.storage.avatar.LocalAvatarStorage;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CreateStorage implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    AppConfig config = (AppConfig) event.getServletContext().getAttribute("config");
    event.getServletContext().setAttribute("avatarStorage", new LocalAvatarStorage(config));
  }
}
