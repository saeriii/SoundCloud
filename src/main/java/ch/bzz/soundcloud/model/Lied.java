package ch.bzz.soundcloud.model;

import ch.bzz.soundcloud.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Date;

/**
 * Ein Lied eines Artists
 */

public class Lied {

    private String liedUUID;
    private String titel;
    private Date hochladedatum;

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
     * gets liedUUID
     *
     * @return value of liedUUID
     */
    public String getLiedUUID() {
        return liedUUID;
    }

    /**
     * sets liedUUID
     *
     * @param liedUUID the value to set
     */
    public void setLiedUUID(String liedUUID) {
        this.liedUUID = liedUUID;
    }

    /**
     * gets titel
     *
     * @return value of titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * sets titel
     *
     * @param titel the value to set
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * gets hochladedatum
     *
     * @return value of hochladedatum
     */
    public Date getHochladedatum() {
        return hochladedatum;
    }

    /**
     * sets hochladedatum
     *
     * @param hochladedatum the value to set
     */
    public void setHochladedatum(Date hochladedatum) {
        this.hochladedatum = hochladedatum;
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
