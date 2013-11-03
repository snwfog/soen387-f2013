package com.charlescy.model;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library implements Cloneable, Serializable
{
  String totalItems;
  List<Book> catalogue;
  public Library(BufferedReader reader)
  {
    catalogue = new ArrayList<Book>();

    // Going to import all the books into the library array through the reader
    // This was inspired by: http://stackoverflow.com/questions/16654042/gson-expected-begin-array-but-was-begin-object
    Gson gson = new Gson();
    JsonParser parser = new JsonParser();
    JsonObject items = parser.parse(reader).getAsJsonObject();
    totalItems = items.get("totalItems").getAsString();
    JsonArray volumes = items.getAsJsonArray("items");
    Iterator<JsonElement> it = volumes.iterator();

    while (it.hasNext())
    {

      // Related: Custom deserializer just in case needed later on
      // http://stackoverflow.com/questions/9296427/gson-deserialization-trying-to-parse-a-json-to-an-object
      JsonElement el = it.next();
      JsonObject ob = el.getAsJsonObject().getAsJsonObject("volumeInfo");
      String id = StringUtils.strip(el.getAsJsonObject().get("id").toString(), "\"");

      Book b = gson.fromJson(ob, Book.class);
      b.setId(id);

      catalogue.add(b);
    }
  }

  public List<Book> getCatalogue()
  {
    return catalogue;
  }

  public void addBook(Book b)
  {
    catalogue.add(b);
  }

  public String getTotalItems()
  {
    return totalItems;
  }

  public void setTotalItems(String totalItems)
  {
    this.totalItems = totalItems;
  }

}
