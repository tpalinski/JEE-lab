package com.example.warhammer.config.listener;

import java.nio.file.Path;

import com.example.warhammer.config.AppConfig;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CreateAppConfig implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    Path avatarPath = Path.of(event.getServletContext().getInitParameter("avatarStoragePath"));
    int initialUserRating = Integer.valueOf(event.getServletContext().getInitParameter("initialUserRating"));
    event.getServletContext().setAttribute("config", AppConfig.builder()
        .initialUserRating(initialUserRating)
        .avatarStoragePath(avatarPath)
        .build()
    );
  }
}
