import com.charlescy.model.Book;
import com.charlescy.model.Library;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GsonTest
{
  public static void main(String[] args)
  {
    Book b = new Book();
    b.setTitle("Hello world");
    b.setPublisher("Oh Reilly");
    b.setPublishedDate("2013-10-23");
    b.setAuthors(new ArrayList<String>(){{ add("Charles Yang"); }});
    b.setCategories(new ArrayList<String>(){{ add("novel"); }});

    List<Book> lib = new ArrayList<Book>(5);
    for (int i = 0; i < 5; i++)
      lib.add(b);

    Gson gson = new Gson();
    System.out.println(gson.toJson(lib));

    String str = "{\n" +
        "  \"items\": [\n" +
        "    {\n" +
        "      \"book1\": {\n" +
        "        \"title\":\"Hello world\",\n" +
        "        \"publisher\":\"Oh Reilly\",\n" +
        "        \"publishedDate\":\"2013-10-23\",\n" +
        "        \"authors\":[\"Charles Yang\"],\n" +
        "        \"categories\":[\"novel\"]\n" +
        "      }\n" +
        "    }\n" +
        "  ]\n" +
        "}\n" +
        "\n";

    JsonParser parser = new JsonParser();
    JsonObject rootObj = parser.parse(str).getAsJsonObject();
    JsonArray bookList = rootObj.getAsJsonArray("items");
    Iterator<JsonElement> it = bookList.iterator();
    ArrayList<Book> bks = new ArrayList<Book>();
    while (it.hasNext())
    {
      JsonObject ob = it.next().getAsJsonObject().getAsJsonObject("book1");
      bks.add(gson.fromJson(ob, Book.class));
    }

    System.out.println(bks);

//    JsonElement el = rootObj.get("items");

//    Type collection = new TypeToken<ArrayList<Book>>(){}.getType();
//    ArrayList<Book> books = gson.fromJson(el, collection);
//    Book b = gson.fromJson()
  }
}
