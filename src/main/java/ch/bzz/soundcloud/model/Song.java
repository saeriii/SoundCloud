package ch.bzz.soundcloud.model;

import ch.bzz.soundcloud.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

/**
 * A song of an artist
 */

public class Song {

    private String songUUID;
    private String title;
    private String uploadDate;

    @JsonIgnore
    private Artist artist;
    @JsonIgnore
    private Genre genre;

    public void setArtistUUID(String artistUUID) {
        setArtist(DataHandler.readArtistByUUID(artistUUID));
    }

    public void setGenreUUID(String genreUUID) {
        setGenre(DataHandler.readGenrebyUUID(genreUUID));
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
     * gets artist
     *
     * @return value of artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * sets artist
     *
     * @param artist the value to set
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
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
