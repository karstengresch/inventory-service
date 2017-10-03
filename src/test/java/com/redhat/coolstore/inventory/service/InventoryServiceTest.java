package com.redhat.coolstore.inventory.service;

import com.redhat.coolstore.inventory.model.Inventory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

/**
 * Created by Karsten Gresch on 03.10.17.
 */
@RunWith(Arquillian.class)
public class InventoryServiceTest
{

  @Inject
  InventoryService inventoryService;

  @CreateSwarm
  public static Swarm newContainer() throws Exception {
    return new Swarm().withProfile("local");
  }

  @Deployment
  public static Archive createDeployment() {
    Archive shrinkWrapArchive = ShrinkWrap.create(WebArchive.class).addPackages(true, InventoryService.class.getPackage()).addPackages(true, Inventory.class.getPackage()).addAsResource("project-local.yml", "project-local.yml").addAsResource("META-INF/test-persistence.xml",  "META-INF/persistence.xml").addAsResource("META-INF/test-load.sql",  "META-INF/test-load.sql");
    return shrinkWrapArchive;
  }

  public void should_return_inventory () {
    Inventory inventory = inventoryService.getInventory("123456");



  }



}
