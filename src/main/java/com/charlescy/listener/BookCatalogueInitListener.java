package com.charlescy.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BookCatalogueInitListener implements ServletContextListener
{
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent)
  {
    // Initialize the book catalogue list
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent)
  {
    // Do nothing
  }
}
