package com.charlescy.controller;

import com.charlescy.model.User;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginController extends HttpServlet
{
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException
  {
    User u = new User(request.getParameter("username"), request.getParameter("password"));

    try
    {
      if (userIsRegistered(u))
      {
        response.sendRedirect("/search.jsp");
      }
      else
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
    catch (IOException e)
    {

    }
  }

  private boolean userIsRegistered(User user)
  {
    for (User u : loadRegisteredUsers())
      if (u.getUsername().equals(user.getUsername()))
        return u.getPassword().equals(user.getPassword());

    return false;
  }

  private List<User> loadRegisteredUsers()
  {
    String yamlPath = getServletContext().getRealPath("/resources");
    Yaml yaml = new Yaml();
    try
    {
      return (List<User>)yaml.load(new FileInputStream(yamlPath + "/users.yml"));
    }
    catch (IOException ioe)
    {
      return null;
    }
  }
}
