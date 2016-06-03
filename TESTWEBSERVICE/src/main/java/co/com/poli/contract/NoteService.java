/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.contract;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sala305
 */
@Path("notes")
public interface NoteService {

    @Path("/validateNotes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Boolean getUsuario(@QueryParam("note1") Double note1, 
        @QueryParam("note2") Double note2,
        @QueryParam("note3") Double note3,
        @QueryParam("note4") Double note4);    
}
