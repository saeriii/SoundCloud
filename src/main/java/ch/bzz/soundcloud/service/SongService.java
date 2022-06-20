package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
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

@Path("song")
public class SongService {

    /**
     * reads a list of all songs
     * @return song as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSong() {
        List<Song> songList = DataHandler.readAllSongs();
        return Response
                .status(200)
                .entity(songList)
                .build();
    }

    /**
     * reads one specific song
     * @param songUUID
     * @return song as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSong(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String songUUID
    ) {
        int httpStatus = 200;
        Song song = DataHandler.readSongbyUUID(songUUID);
        if (song == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(song)
                .build();
    }

    /**
     * inserts a new song
     * @param artistUUID
     * @param genreUUID
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSong(
            @Valid @BeanParam Song song,

            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("artistUUID") String artistUUID,

            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("genreUUID") String genreUUID
    ) {
        song.setSongUUID(UUID.randomUUID().toString());
        song.setArtistUUID(artistUUID);
        song.setGenreUUID(genreUUID);

        DataHandler.insertSong(song);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new song
     * @param artistUUID
     * @param genreUUID
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSong(
            @Valid @BeanParam Song song,

            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("artistUUID") String artistUUID,

            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("genreUUID") String genreUUID
    ) {
        int httpStatus = 200;
        Song oldSong = DataHandler.readSongbyUUID(song.getSongUUID());
        if (oldSong != null) {
            oldSong.setTitle(song.getTitle());
            oldSong.setArtistUUID(artistUUID);
            oldSong.setGenreUUID(genreUUID);
            oldSong.setUploadDate(song.getUploadDate());

            DataHandler.updateSong();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


    /**
     * deletes a song identified by its uuid
     * @param songUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSong(
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String songUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteSong(songUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * sets the attributes for the song-object
     * @param song  the song-object
     * @param title  the title
     * @param artistUUID  the uuid of the artist
     * @param genreUUID  the uuid of the genre
     * @param uploadDate  the upload date
     */
    private void setAttributes(
            Song song,
            String title,
            String artistUUID,
            String genreUUID,
            String uploadDate
    ) {
        song.setTitle(title);
        song.setArtistUUID(artistUUID);
        song.setGenreUUID(genreUUID);
        song.setUploadDate(uploadDate);
    }

}
