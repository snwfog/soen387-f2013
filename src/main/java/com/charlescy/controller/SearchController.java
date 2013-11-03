package com.charlescy.controller;

import com.charlescy.model.Book;
import com.charlescy.model.Library;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class SearchController extends HttpServlet
{

  // This is the base path for Google Book API URL
  static final String googleBookApiBasePath = "https://www.googleapis.com/books/v1/volumes";

  // This is the fields that I am asking for return only
  // They match the attributes of the Book object, therefore
  // these attributes are hardcoded and shall not be changed
  static final String googleBookApiQueryFields = "fields=totalItems,items(id,volumeInfo(title,authors,publisher,publishedDate,categories,description,imageLinks(thumbnail)))";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    // Get the query string first, else throw errors
    String q = request.getParameter("q");
    if (q == null)
      throw new IOException("The query string cannot be empty...");

    String queryURL = String.format("%s?q=%s&%s&prettyPrint=false",
        googleBookApiBasePath, URLEncoder.encode(q, "UTF-8"), googleBookApiQueryFields); // Minimalized json
//    String queryURL = String.format("%s?q=%s&%s", googleBookApiBasePath, q, googleBookApiQueryFields);
    BufferedReader reader = null;
    PrintWriter out = null;
    // FYI: You can read directly into Gson's parse method
    StringBuffer apiPayLoad = new StringBuffer();
    Gson gson = new Gson();

    try
    {
      URL url = new URL(queryURL);
      reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
      Library lib = new Library(reader);

      request.setAttribute("library", lib);
      request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
    catch (Throwable t)
    {
      System.out.println("Something went wrong in " + this.getClass().toString() +  ": " + t);
    }
    finally
    {
      reader.close();
    }
  }
}
