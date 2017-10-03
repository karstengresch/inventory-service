package main.com.redhat.coolstore.inventory.model;

import javax.persistence.Id;

/**
 * Created by Karsten Gresch on 03.10.17.
 */

public class Inventory
{
  @Id
  private String itemId;

  private String location;

  private Integer quantity;

  private String link;



}
