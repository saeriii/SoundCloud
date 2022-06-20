package ch.bzz.soundcloud.model;

import ch.bzz.soundcloud.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.xml.crypto.Data;
import java.time.LocalDate;

/**
 * A song of an artist
 */

public class Song {

    @FormParam("songUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String songUUID;

    @FormParam("title")
    @NotEmpty
    @Size(min=1,max=50)
    private String title;

    @FormParam("uploadDate")
    @NotEmpty
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
    private String uploadDate;

    @JsonIgnore
    private Artist artist;
    @JsonIgnore
    private Genre genre;

    /**
     * gets the artistUUID from the Artist-object
     * @return
     */
    public String getArtistUUID() {
        if (getArtist()== null) return null;
        return getArtist().getArtistUUID();
    }

    /**
     * gets the genreUUID from the Genre-object
     * @return
     */
    public String getGenreUUID() {
        if (getGenre()== null) return null;
        return getGenre().getGenreUUID();
    }

    /**
     * creates an Artist-object without the songs
     * @param artistUUID
     */
    public void setArtistUUID(String artistUUID) {
        setArtist(new Artist());
        Artist artist = DataHandler.readArtistByUUID(artistUUID);
        getArtist().setArtistUUID(artistUUID);
        getArtist().setFirstname(artist.getFirstname());
        getArtist().setSurname(artist.getSurname());
        getArtist().setTel(artist.getTel());
        getArtist().setNumberOfSongs(artist.getNumberOfSongs());
    }

    /**
     * creates an Genre-object
     * @param genreUUID
     */
    public void setGenreUUID(String genreUUID) {
        setGenre(new Genre());
        Genre genre = DataHandler.readGenrebyUUID(genreUUID);
        getGenre().setGenreUUID(genreUUID);
        getGenre().setGenre(genre.getGenre());
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
