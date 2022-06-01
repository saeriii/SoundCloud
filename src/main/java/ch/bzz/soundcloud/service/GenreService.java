package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Genre;

import javax.ws.rs.*;
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
        List<Genre> genre = DataHandler.readAllGenres();
        return Response
                .status(200)
                .entity(genre)
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
        int httpStatus = 200;
        Genre genre = DataHandler.readGenrebyUUID(genreUUID);
        if (genre == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(genre)
                .build();
    }

    /**
     * deletes a Genre identified by its uuid
     * @param genreUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGenre(
            @QueryParam("uuid") String genreUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteGenre(genreUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


}
