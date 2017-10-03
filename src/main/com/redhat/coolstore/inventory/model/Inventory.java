package main.com.redhat.coolstore.inventory.model;

/**
 * Created by Karsten Gresch on 03.10.17.
 */
public class Inventory
{
  private String itemId;
  private String location;
  private Integer quantity;
  private String link;

  

  public String getItemId()
  {
    return itemId;
  }

  public void setItemId(String itemId)
  {
    this.itemId = itemId;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public void setQuantity(Integer quantity)
  {
    this.quantity = quantity;
  }

  public String getLink()
  {
    return link;
  }

  public void setLink(String link)
  {
    this.link = link;
  }
}
