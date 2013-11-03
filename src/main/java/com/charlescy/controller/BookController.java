package com.charlescy.controller;

import com.charlescy.model.Book;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class BookController extends HttpServlet
{
  // This is the base path for Google Book API URL
  static final String googleBookApiBasePath = "https://www.googleapis.com/books/v1/volumes";

  // This is the fields that I am asking for return only
  // They match the attributes of the Book object, therefore
  // these attributes are hardcoded and shall not be changed
  static final String googleBookApiQueryFields = "fields=items(id,volumeInfo(title,authors,publisher,publishedDate,categories,description,imageLinks(thumbnail)))";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    // Get the query string first, else throw errors
    String id = request.getParameter("id");
    if (id == null)
      throw new IOException("The query string cannot be empty...");

    String queryURL = String.format("%s?q=%s&%s&prettyPrint=false",
        googleBookApiBasePath, URLEncoder.encode(id, "UTF-8"), googleBookApiQueryFields); // Minimalized json
    BufferedReader reader = null;
    PrintWriter out = null;
    // FYI: You can read directly into Gson's parse method
    StringBuffer apiPayLoad = new StringBuffer();
    Gson gson = new Gson();

    try
    {
      URL url = new URL(queryURL);
      reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
      // Reader from the reader
      JsonParser parser = new JsonParser();
      JsonObject items = parser.parse(reader).getAsJsonObject();
      JsonArray volumes = items.getAsJsonArray("items");
      if (volumes.size() < 1) throw new Throwable("Could not find any result with the search id.");
      Iterator<JsonElement> it = volumes.iterator();

      for (int i = 0; i < 1; i++)
      {
        // Related: Custom deserializer just in case needed later on
        // http://stackoverflow.com/questions/9296427/gson-deserialization-trying-to-parse-a-json-to-an-object
        JsonElement el = it.next();
        JsonObject ob = el.getAsJsonObject().getAsJsonObject("volumeInfo");
        if (!id.equalsIgnoreCase(StringUtils.strip(el.getAsJsonObject().get("id").toString(), "\"")))
          throw new Throwable("The new book record id does not match with the searched id.");

        Book b = gson.fromJson(ob, Book.class);
        b.setId(id);

        request.setAttribute("book", b);
        request.getRequestDispatcher("/book.jsp").forward(request, response);
      }
    }
    catch (Throwable t)
    {
      System.out.println("Something went wrong in " + this.getClass().toString() +  ": " + t);
    }
  }

}
