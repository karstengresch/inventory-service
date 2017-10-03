package com.redhat.coolstore.inventory.rest;

import com.redhat.coolstore.inventory.model.Inventory;
import com.redhat.coolstore.inventory.service.InventoryService;

import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Created by Karsten Gresch on 03.10.17.
 */
@Path("/inventory")
public class InventoryResource
{

  @Inject
  InventoryService inventoryService;

  @GET
  @Produces("application/json")
  @Path("/{itemId}")
  public Inventory getInventory(@PathParam("itemId") String itemId) {
    Inventory inventory = inventoryService.getInventory(itemId);

    if(inventory == null) {
        throw new NotFoundException("Could not find inventory for itemId: " + itemId);
    }

    return  inventory;
  }

}
