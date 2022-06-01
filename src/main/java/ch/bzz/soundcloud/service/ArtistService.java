package ch.bzz.soundcloud.service;

        import ch.bzz.soundcloud.data.DataHandler;
        import ch.bzz.soundcloud.model.Artist;
        import ch.bzz.soundcloud.model.Genre;

        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.util.List;

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

}
