package com.redhat.coolstore.inventory.rest;

import org.wildfly.swarm.health.Health;
import org.wildfly.swarm.health.HealthStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Karsten Gresch on 03.10.17.
 */
@Path("/")
public class HealthCheckResource
{
  @GET
  @Health
  @Produces("application/json")
  @Path("/status")
  public HealthStatus check() {
    HealthStatus healthStatus = HealthStatus.named("server-state").up();
    System.out.println("HealthStatus: " + healthStatus.toJson());
    return healthStatus;
  }

}
