import com.charlescy.model.User;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class YamlTest
{
  public static void main(String[] args)
  {
    Yaml yml = new Yaml();
    User u1 = new User("charles", "charles");
    User u2 = new User("admin", "admin");

    List<User> users = new ArrayList<User>();
    users.add(u1);
    users.add(u2);

    List<User> newList = null;
    try
    {
      newList = (List<User>)yml.load(yml.dump(users)); // Lol, doesn't work
    }
    catch (Throwable e)
    {

    }
  }
}
