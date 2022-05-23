package ch.bzz.soundcloud.service;

        import ch.bzz.soundcloud.data.DataHandler;
        import ch.bzz.soundcloud.model.Artist;

        import javax.ws.rs.GET;
        import javax.ws.rs.Path;
        import javax.ws.rs.Produces;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.util.List;

@Path("artist")
public class ArtistService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listArtists() {
        List<Artist> artistList = DataHandler.getInstance().readAllArtists();
        return Response
                .status(200)
                .entity(artistList)
                .build();
    }

}
