package com.charlescy.model;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Book implements Cloneable, Serializable
{
  public String id;
  public String title;
  public String publisher;
  public String publishedDate;
  public String description;
  public Thumbnail imageLinks;
  public List<String> authors;
  public List<String> categories;

  public Book()
  {
    id = "-1";
    title = "Not available";
    publisher = "Not available";
    publishedDate = "Not available";
    description = "Not available";
    imageLinks = new Thumbnail();
    authors = new ArrayList<String>() {{ add("Not available"); }};
    categories = new ArrayList<String>() {{ add("Not available"); }};

  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getPublisher()
  {
    return publisher;
  }

  public void setPublisher(String publisher)
  {
    this.publisher = publisher;
  }

  public String getPublishedDate()
  {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate)
  {
    this.publishedDate = publishedDate;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public List<String> getAuthors()
  {
    return authors;
  }

  public void setAuthors(List<String> authors)
  {
    this.authors = authors;
  }

  public List<String> getCategories()
  {
    return categories;
  }

  public void setCategories(List<String> categories)
  {
    this.categories = categories;
  }

  public Thumbnail getImageLinks()
  {
    return imageLinks;
  }

  public void setImageLinks(Thumbnail imageLinks)
  {
    this.imageLinks = imageLinks;
  }

  public boolean getAvailability()
  {
    return new Random(System.currentTimeMillis()).nextInt(65536) % 2 == 0 ? true : false;
  }

}
