package com.charlescy.model;

import java.io.Serializable;

public class User implements Cloneable, Serializable
{
  private String username;
  private String password;

  public User(String un, String passwd)
  {
    username = un;
    password = passwd;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (!(o instanceof User)) return false;

    User user = (User) o;

    if (!password.equals(user.password)) return false;
    if (!username.equals(user.username)) return false;

    return true;
  }
}
