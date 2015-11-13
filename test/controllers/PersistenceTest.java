package controllers;
import controllers.PaceMakerAPI;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import models.Activity;
import models.Location;
import models.User;

import org.junit.Test;

import utils.Serializer;
import utils.XMLSerializer;
import static models.Fixtures.users;
import static models.Fixtures.activities;
import static models.Fixtures.locations;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistenceTest {

	PaceMakerAPI pacemaker;
	 
	 @Test
	  public void testPopulate()
	  { 
	    pacemaker = new PaceMakerAPI(null);
	    assertEquals(0, pacemaker.getUsers().size());
	    populate (pacemaker);

	    assertEquals(users.length, pacemaker.getUsers().size());
	    assertEquals(2, pacemaker.getUserByEmail(users[0].email).activities.size());
	    assertEquals(2, pacemaker.getUserByEmail(users[1].email).activities.size());   
	    Long activityID = pacemaker.getUserByEmail(users[0].email).activities.keySet().iterator().next();
	    assertEquals(locations.length, pacemaker.getActivity(activityID).route.size());   
	  }
	 
	 @Test
	  public void testXMLSerializer() throws Exception
	  { 
	    String datastoreFile = "testdatastore.xml";
	    deleteFile (datastoreFile);

	    Serializer serializer = new XMLSerializer(new File (datastoreFile));

	    pacemaker = new PaceMakerAPI(serializer); 
	    populate(pacemaker);
	    pacemaker.store();

	    PaceMakerAPI pacemaker2 =  new PaceMakerAPI(serializer);
	    pacemaker2.load();

	    assertEquals (pacemaker.getUsers().size(), pacemaker2.getUsers().size());
	    for (User user : pacemaker.getUsers())
	    {
	      assertTrue (pacemaker2.getUsers().contains(user));
	    }
	    deleteFile ("testdatastore.xml");
	  }
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**
 * UTILITY	
 * @param pacemaker
 */
//Creates DataSet for paceMakerAPI (NOT A TEST)
	 void populate (PaceMakerAPI pacemaker)
	  {
	    for (User user : users)
	    {
	      pacemaker.createUser(user.firstName, user.lastName, user.email, user.password);
	    }
	    User user1 = pacemaker.getUserByEmail(users[0].email);
	    Activity activity = pacemaker.createActivity(user1.id, activities[0].type, activities[0].location, activities[0].distance);
	    pacemaker.createActivity(user1.id, activities[1].type, activities[1].location, activities[1].distance);
	    User user2 = pacemaker.getUserByEmail(users[1].email);
	    pacemaker.createActivity(user2.id, activities[2].type, activities[2].location, activities[2].distance);
	    pacemaker.createActivity(user2.id, activities[3].type, activities[3].location, activities[3].distance);

	    for (Location location : locations)
	    {
	      pacemaker.addLocation(activity.id, location.latitude, location.longitude);
	    }
	  }

	 /**
	  * UTILITY
	  */
	 //Deletes File
	 void deleteFile(String fileName)
	  {
	    File datastore = new File ("testdatastore.xml");
	    if (datastore.exists())
	    {
	      datastore.delete();
	    }
	  }
	 
}
