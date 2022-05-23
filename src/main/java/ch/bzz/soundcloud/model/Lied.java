package ch.bzz.soundcloud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ch.bzz.soundcloud.data.DataHandler;

import java.time.LocalDate;

/**
 * Ein Lied eines Artists
 */

public class Lied {

    private Artist artist;
    private String liedUUID;
    private String titel;
    private Genre genre;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate hochladedatum;

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

    /**
     * gets hochladedatum
     *
     * @return value of hochladedatum
     */
    public LocalDate getHochladedatum() {
        return hochladedatum;
    }

    /**
     * sets hochladedatum
     *
     * @param hochladedatum the value to set
     */
    public void setHochladedatum(LocalDate hochladedatum) {
        this.hochladedatum = hochladedatum;
    }
}
