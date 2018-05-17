package com.setcce;

import org.jooby.Jooby;
import org.jooby.apitool.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  {
    get("/user/:id", promise((req, deferred) -> {
      try {
        deferred.resolve(getUser(req.param("id").intValue()));
      } catch (Throwable x) {
        deferred.reject(x);
      }
    }));

    use(new ApiTool().swagger("/swagger"));

  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

  public JsonObject getUser(int id) {
    Client client = Client.create();

    WebResource webResource = client.resource("http://private-anon-484c3dedd9-setcce.apiary-mock.com/user/1");

    ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
    }
    return new JsonParser().parse(response.getEntity(String.class)).getAsJsonObject();
  }

}
