package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Lied;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

}
