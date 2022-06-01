package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Lied;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("lied")
public class LiedService {

    /**
     * reads a list of all lieder
     * @return lieder as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listLied() {
        List<Lied> liedList = DataHandler.readAllLieder();
        return Response
                .status(200)
                .entity(liedList)
                .build();
    }

    /**
     * reads one specific lied
     * @param liedUUID
     * @return lied as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readLied(
            @QueryParam("uuid") String liedUUID
    ) {
        int httpStatus = 200;
        Lied lied = DataHandler.readLiedByUUID(liedUUID);
        if (lied == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(lied)
                .build();
    }

    /**
     * deletes a Lied identified by its uuid
     * @param liedUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLied(
            @QueryParam("uuid") String liedUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteLied(liedUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
