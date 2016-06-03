/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.appschool.contract;

import com.school.appschool.entities.UsersTbl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sala305
 */
@Path("users")
public interface UserServices {

    @Path("/getUser")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UsersTbl getUsuario(@QueryParam("email") String email);
    
    
    @Path("/validateGetUser")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UsersTbl getUsuario(@QueryParam("email") String email, @QueryParam("pass") String pass);
}
