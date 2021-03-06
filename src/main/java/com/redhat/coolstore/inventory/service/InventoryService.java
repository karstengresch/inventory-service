package com.redhat.coolstore.inventory.service;

import com.redhat.coolstore.inventory.model.Inventory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Karsten Gresch on 03.10.17.
 */
@ApplicationScoped
public class InventoryService
{
  @PersistenceContext(name = "primary")
  EntityManager entityManager;



  public Inventory getInventory(String itemId) {
    Inventory inventory = entityManager.find(Inventory.class, itemId);
    System.out.println("***** Got inventory: " + inventory.getItemId());
    return  inventory;
  }


}
