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

@Path("artist")
public class ArtistService {

    /**
     * reads a list of all artists
     * @return artists as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listArtists() {
        List<Artist> artistList = DataHandler.readAllArtists();
        return Response
                .status(200)
                .entity(artistList)
                .build();
    }

    /**
     * reads one specific artist
     * @param artistUUID
     * @return artist as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readArtist(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String artistUUID
    ) {
        int httpStatus = 200;
        Artist artist = DataHandler.readArtistByUUID(artistUUID);
        if (artist == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(artist)
                .build();
    }

    /**
     * inserts a new artist
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertArtist(
            @Valid @BeanParam Artist artist
    ) {
        artist.setArtistUUID(UUID.randomUUID().toString());
        DataHandler.insertArtist(artist);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates an artist
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateArtist(
            @Valid @BeanParam Artist artist
    ) {
        int httpStatus = 200;
        Artist oldArtist = DataHandler.readArtistByUUID(artist.getArtistUUID());
        if (oldArtist != null) {
            oldArtist.setFirstname(artist.getFirstname());
            oldArtist.setSurname(artist.getSurname());
            oldArtist.setTel(artist.getTel());
            oldArtist.setNumberOfSongs(artist.getNumberOfSongs());

            DataHandler.updateArtist();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes an artist identified by their uuid
     * @param artistUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteArtist(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String artistUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteArtist(artistUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * sets the attributes for the artist-object
     * @param artist  the artist-object
     * @param firstname  the first name
     * @param surname  the surname
     * @param tel  the telephone number
     * @param numberOfSongs  the number of songs
     */
    private void setAttributes(
            Artist artist,
            String firstname,
            String surname,
            String tel,
            Integer numberOfSongs
    ) {
        artist.setFirstname(firstname);
        artist.setSurname(surname);
        artist.setTel(tel);
        artist.setNumberOfSongs(numberOfSongs);
    }


}
