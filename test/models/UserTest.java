package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static models.Fixtures.users;

public class UserTest 
{
	User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret");
	
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
  public void testCreate()
  {
    assertEquals ("homer",               homer.firstName);
    assertEquals ("simpson",             homer.lastName);
    assertEquals ("homer@simpson.com",   homer.email);   
    assertEquals ("secret",              homer.password);   
  }
	
	@Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.length, ids.size());
  }
	
	@Test
  public void testToString()
  {
    assertEquals ("User{" + homer.id + ", homer, simpson, secret, homer@simpson.com}", homer.toString());
  }
	@Test
	public void testEquals()
	{
		User homer2 = new User ("homer", "simpson", "homer@simpson.com",  "secret"); 
    User bart   = new User ("bart", "simpson", "bartr@simpson.com",  "secret"); 

    assertSame(homer, homer);
    assertNotSame(homer, homer2);
    assertNotEquals(homer, bart);
	}
	@After
	public void tearDown() throws Exception {
	}

}
