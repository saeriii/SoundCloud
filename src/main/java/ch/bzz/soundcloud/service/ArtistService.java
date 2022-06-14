package ch.bzz.soundcloud.service;

        import ch.bzz.soundcloud.data.DataHandler;
        import ch.bzz.soundcloud.model.Artist;
        import ch.bzz.soundcloud.model.Genre;
        import ch.bzz.soundcloud.model.Song;

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
     * @param firstname
     * @param surname
     * @param tel
     * @param numberOfSongs
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertArtist(
            @FormParam("firstname") String firstname,
            @FormParam("surname") String surname,
            @FormParam("tel") String tel,
            @FormParam("numberOfSongs") Integer numberOfSongs
    ) {
        Artist artist = new Artist();
        artist.setArtistUUID(UUID.randomUUID().toString());
        setAttributes(
                artist,
                firstname,
                surname,
                tel,
                numberOfSongs
        );

        DataHandler.insertArtist(artist);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new artist
     * @param artistUUID the key
     * @param firstname
     * @param surname
     * @param tel
     * @param numberOfSongs
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateArtist(
            @FormParam("artistUUID") String artistUUID,
            @FormParam("firstname") String firstname,
            @FormParam("surname") String surname,
            @FormParam("tel") String tel,
            @FormParam("numberOfSongs") Integer numberOfSongs
    ) {
        int httpStatus = 200;
        Artist artist = DataHandler.readArtistByUUID(artistUUID);
        if (artist != null) {
            setAttributes(
                    artist,
                    firstname,
                    surname,
                    tel,
                    numberOfSongs
            );

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
     * deletes an Artist identified by their uuid
     * @param artistUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteArtist(
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
