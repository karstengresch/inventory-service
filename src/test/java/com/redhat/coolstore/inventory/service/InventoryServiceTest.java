package com.redhat.coolstore.inventory.service;

import com.redhat.coolstore.inventory.model.Inventory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import javax.inject.Inject;

/**
 * Created by Karsten Gresch on 03.10.17.
 */
@RunWith(Arquillian.class)
public class InventoryServiceTest
{

  @Inject
  private InventoryService inventoryService;

  @CreateSwarm
  public static Swarm newContainer() throws Exception {
    return new Swarm().withProfile("local");
  }

  @Deployment
  public static Archive createDeployment() {
    Archive shrinkWrapArchive = ShrinkWrap.create(WebArchive.class)
                                          .addPackages(true, InventoryService.class.getPackage())
                                          .addPackages(true, Inventory.class.getPackage())
                                          .addAsResource("project-local.yml", "project-local.yml")
                                          .addAsResource("META-INF/test-persistence.xml",  "META-INF/persistence.xml")
                                          .addAsResource("META-INF/test-load.sql",  "META-INF/test-load.sql");
    return shrinkWrapArchive;
  }

  @Test
  public void shouldReturnInventoryViaJpa() {
    String itemId = "123456";
    String link = "link";
    String location = "location";
    Integer quantity = 99;

    Inventory inventory = inventoryService.getInventory(itemId);
    System.out.println("inventory: " + inventory.toString());
    Assert.assertNotNull(inventory);
    Assert.assertEquals(itemId, inventory.getItemId());
    Assert.assertEquals(link, inventory.getLink());
    Assert.assertEquals(location, inventory.getLocation());
    Assert.assertEquals(quantity, inventory.getQuantity());
  }



}
