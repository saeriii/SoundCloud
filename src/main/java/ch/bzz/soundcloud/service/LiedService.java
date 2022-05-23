package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Lied;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("lied")
public class LiedService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listLied() {
        List<Lied> liedList = DataHandler.getInstance().readAllLieder();
        return Response
                .status(200)
                .entity(liedList)
                .build();
    }

}
