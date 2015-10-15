package controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.MoreObjects.toStringHelper;
import models.User;

public class Main
{
  public static void main(String[] args) throws IOException
  {    
    PaceMakerAPI pacemakerAPI = new PaceMakerAPI();

    pacemakerAPI.createUser("Bart",  "Simpson", "bart@simpson.com",  "secret");
    pacemakerAPI.createUser("Homer", "Simpson", "homer@simpson.com", "secret");
    pacemakerAPI.createUser("Lisa",  "Simpson", "lisa@simpson.com",  "secret");

    Collection<User> user = pacemakerAPI.getUsers();
    System.out.println(user);
  }
  
}

