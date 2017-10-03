package com.redhat.coolstore.inventory;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Karsten Gresch on 03.10.17.
 */

@RunWith(Arquillian.class)
public class RestApiTest
{

  @CreateSwarm
  public static Swarm newContainer() throws Exception {
    return new Swarm().withProfile("local");
  }

  @Deployment
  public static Archive createDeployment() {
    Archive shrinkWrapArchive = ShrinkWrap.create(WebArchive.class)
                                          .addPackages(true, RestApplication.class.getPackage())
                                          .addAsResource("project-local.yml", "project-local.yml")
                                          .addAsResource("META-INF/test-persistence.xml",  "META-INF/persistence.xml")
                                          .addAsResource("META-INF/test-load.sql",  "META-INF/test-load.sql");
    return shrinkWrapArchive;
  }


  @Test
  @RunAsClient
  public void shouldReturnInventoryViaRest() {
    String itemId = "123456";
    String link = "link";
    String location = "location";
    Integer quantity = 99;

    Client restClient = ClientBuilder.newClient();
    WebTarget webTarget = restClient.target("http://localhost:8080").path("/inventory").path("/123456");
    Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();
    JsonObject responseJsonObject = Json.parse(response.readEntity(String.class)).asObject();

    Assert.assertEquals(responseJsonObject.get("itemId").asString(), itemId);
    Assert.assertEquals(responseJsonObject.get("link").asString(), link);
    Assert.assertEquals(responseJsonObject.get("location").asString(), location);
    Assert.assertEquals((Integer.valueOf(responseJsonObject.get("quantity").asInt())), quantity);
  }


  @Test
  @RunAsClient
  public void shouldReturnHealthInformation() {

    Client restClient = ClientBuilder.newClient();
    WebTarget webTarget = restClient.target("http://localhost:8080").path("/status");
    Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();
    JsonObject responseJsonObject = Json.parse(response.readEntity(String.class)).asObject();

    Assert.assertNotNull(responseJsonObject);
  }


}
