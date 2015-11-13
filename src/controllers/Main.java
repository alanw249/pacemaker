package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import static com.google.common.base.MoreObjects.toStringHelper;
import models.User;

public class Main
{
  public static void main(String[] args) throws Exception
  {    
    PaceMakerAPI pacemakerAPI = new PaceMakerAPI();
    File file = new File("datastore.xml");
    pacemakerAPI.load(file);
    Collection<User> users = pacemakerAPI.getUsers();
    System.out.println(users);
    
    User homer = pacemakerAPI.getUserByEmail("homer@simpson.com");
    System.out.println(homer);

    pacemakerAPI.deleteUser(homer.id);
    users = pacemakerAPI.getUsers();
    System.out.println(users);
  }
  
}

