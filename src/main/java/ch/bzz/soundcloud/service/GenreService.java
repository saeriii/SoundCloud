package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Genre;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("genre")
public class GenreService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGenre() {
        List<Genre> genreList = DataHandler.getInstance().readAllGenres();
        return Response
                .status(200)
                .entity(genreList)
                .build();
    }

}
