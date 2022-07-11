package ch.bzz.soundcloud.model;

import ch.bzz.soundcloud.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * A song of an artist
 */

public class Song {
    @JsonIgnore
    private Genre genre;

    @FormParam("songUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String songUUID;

    @FormParam("title")
    @NotEmpty
    @Size(min=1,max=50)
    private String title;

    @FormParam("uploadDate")
    @NotEmpty
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")
    private String uploadDate;

    /**
     * gets the genreUUID from the Genre-object
     * @return
     */
    public String getGenreUUID() {
        if (getGenre()== null) return null;
        return getGenre().getGenreUUID();
    }

    /**
     * creates an Genre-object
     * @param genreUUID
     */
    public void setGenreUUID(String genreUUID) {
        setGenre(new Genre());
        Genre genre = DataHandler.readGenrebyUUID(genreUUID);
        getGenre().setGenreUUID(genreUUID);
        getGenre().setGenreName(genre.getGenreName());
        getGenre().setPopularity(genre.getPopularity());
    }

    /**
     * gets songUUID
     *
     * @return value of songUUID
     */
    public String getSongUUID() {
        return songUUID;
    }

    /**
     * sets songUUID
     *
     * @param songUUID the value to set
     */
    public void setSongUUID(String songUUID) {
        this.songUUID = songUUID;
    }

    /**
     * gets title
     *
     * @return value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets title
     *
     * @param title the value to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets uploadDate
     *
     * @return value of uploadDate
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * sets uploadDate
     *
     * @param uploadDate the value to set
     */
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * gets genre
     *
     * @return value of genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * sets genre
     *
     * @param genre the value to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
