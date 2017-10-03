package com.redhat.coolstore.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Karsten Gresch on 03.10.17.
 */
@Entity
@Table(name = "PRODUCT_INVENTORY")
public class Inventory
{
  @Id
  @Column(name="ITEMID", unique=true, nullable=false)
  private String itemId;

  // length defaults to 255
  @Column(name="LOCATION")
  private String location;

  @Column(name="QUANTITY")
  private Integer quantity;

  @Column(name="LINK")
  private String link;

  public String getItemId()
  {
    return itemId;
  }

  public String getLocation()
  {
    return location;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public String getLink()
  {
    return link;
  }

  @Override public String toString()
  {
    return getItemId() + " - " + getLink() + " - " + getLocation();
  }
}
