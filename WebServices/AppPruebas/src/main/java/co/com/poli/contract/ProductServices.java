package co.com.poli.contract;

import co.com.poli.entities.Product;
import java.awt.PageAttributes;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/product")
public interface ProductServices {

    @Path("/getAllProducts")
    @GET
    //@Produces("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> getAllProducts();

    @Path("/getSearchProduct")
    @GET
    //@Produces("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Product getSearchProduct(@QueryParam("code") String code);
}
