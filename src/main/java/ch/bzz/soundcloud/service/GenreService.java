package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Genre;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("genre")
public class GenreService {

    /**
     * reads a list of all genres
     * @return genres as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGenre() {
        List<Genre> genreList = DataHandler.readAllGenres();
        return Response
                .status(200)
                .entity(genreList)
                .build();
    }

    /**
     * reads one specific genre
     * @param genreUUID
     * @return genre as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGenre(
            @QueryParam("uuid") String genreUUID
    ) {
        Genre genre = DataHandler.readGenrebyUUID(genreUUID);
        return Response
                .status(200)
                .entity(genre)
                .build();
    }


}
