package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Artist;
import ch.bzz.soundcloud.model.Genre;
import ch.bzz.soundcloud.model.Song;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertGenre(
            @Valid @BeanParam Genre genre
    ) {
        genre.setGenreUUID(UUID.randomUUID().toString());
        DataHandler.insertGenre(genre);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new artist
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateArtist(
            @Valid @BeanParam Genre genre
    ) {
        int httpStatus = 200;
        Genre oldGenre = DataHandler.readGenrebyUUID(genre.getGenreUUID());
        if (oldGenre != null) {
            oldGenre.setGenre(genre.getGenre());
            oldGenre.setPopularity(genre.getPopularity());

            DataHandler.updateGenre();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
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
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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


    /**
     * sets the attributes for the genre-object
     * @param genre1  the genre-object
     * @param genre  the genre name
     * @param popularity  the popularity
     */
    private void setAttributes(
            Genre genre1,
            String genre,
            Integer popularity
    ) {
        genre1.setGenre(genre);
        genre1.setPopularity(popularity);
    }

}
