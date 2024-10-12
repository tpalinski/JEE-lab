package com.example.warhammer.config.listener;

import com.example.warhammer.config.AppConfig;
import com.example.warhammer.repository.user.UserMemoryRepository;
import com.example.warhammer.repository.user.UserRepository;
import com.example.warhammer.service.user.SimpleUserService;
import com.example.warhammer.storage.avatar.AvatarStorage;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CreateServices implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {

    UserRepository userRepository = new UserMemoryRepository();
    AppConfig config = (AppConfig) event.getServletContext().getAttribute("config");
    AvatarStorage avatarStorage = (AvatarStorage) event.getServletContext().getAttribute("avatarStorage");


    event.getServletContext().setAttribute(
      "userService", 
      new SimpleUserService(
        userRepository, 
        avatarStorage
      )
    );
  }

}
