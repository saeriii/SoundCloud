package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Genre;
import ch.bzz.soundcloud.model.Song;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

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
     * inserts a new genre
     * @param genre
     * @param popularity
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertGenre(
            @FormParam("genre") String genre,
            @FormParam("popularity") Integer popularity
    ) {
        Genre genre1 = new Genre();
        genre1.setGenreUUID(UUID.randomUUID().toString());
        genre1.setGenre(genre);
        genre1.setPopularity(popularity);

        DataHandler.insertGenre(genre1);
        return Response
                .status(200)
                .entity("")
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
