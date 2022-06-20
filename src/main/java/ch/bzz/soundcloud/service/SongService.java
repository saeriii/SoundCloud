package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.DataHandler;
import ch.bzz.soundcloud.model.Song;

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
     * @param title
     * @param artistUUID
     * @param genreUUID
     * @param uploadDate
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSong(
            @FormParam("title") String title,
            @FormParam("artistUUID") String artistUUID,
            @FormParam("genreUUID") String genreUUID,
            @FormParam("uploadDate") String uploadDate
    ) {
        Song song = new Song();
        song.setSongUUID(UUID.randomUUID().toString());
        setAttributes(
                song,
                title,
                artistUUID,
                genreUUID,
                uploadDate
        );
        DataHandler.insertSong(song);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new song
     * @param songUUID the key
     * @param title
     * @param artistUUID
     * @param genreUUID
     * @param uploadDate
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSong(
            @FormParam("songUUID") String songUUID,
            @FormParam("title") String title,
            @FormParam("artistUUID") String artistUUID,
            @FormParam("genreUUID") String genreUUID,
            @FormParam("uploadDate") String uploadDate
    ) {
        int httpStatus = 200;
        Song song = DataHandler.readSongbyUUID(songUUID);
        if (song != null) {
            setAttributes(
                    song,
                    title,
                    artistUUID,
                    genreUUID,
                    uploadDate
            );

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
