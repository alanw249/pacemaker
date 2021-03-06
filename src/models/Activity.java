package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import utils.ToJsonString;

import com.google.common.base.Objects;

public class Activity 
{ 
  static Long   counter = 0l;

  public Long   id;

  public String type;
  public String location;
  public double distance;

  public List<Location> route = new ArrayList<>();

  public Activity()
  {
  }

  public Activity(String type, String location, double distance)
  {
    this.id        = counter++;
    this.type      = type;
    this.location  = location;
    this.distance  = distance;
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Activity)
    {
      final Activity other = (Activity) obj;
      return Objects.equal(type, other.type) 
          && Objects.equal(location,  other.location)
          && Objects.equal(distance,  other.distance)
          && Objects.equal(route,     other.route);    
    }
    else
    {
      return false;
    }
  }

  @Override
  public String toString()
  {
    return new ToJsonString(getClass(), this).toString();
  }

  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.type, this.location, this.distance);  
  } 
}