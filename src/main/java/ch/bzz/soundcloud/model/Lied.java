package ch.bzz.soundcloud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

/**
 * Ein Lied eines Artists
 */

public class Lied {

    private String liedUUID;
    private String titel;
    private String artistUUID;
    private String genreUUID;
    private LocalDate hochladedatum;

    @JsonIgnore
    private Artist artist;
    @JsonIgnore
    private Genre genre;

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
     * gets artistUUID
     *
     * @return value of artistUUID
     */
    public String getArtistUUID() {
        return artistUUID;
    }

    /**
     * sets artistUUID
     *
     * @param artistUUID the value to set
     */
    public void setArtistUUID(String artistUUID) {
        this.artistUUID = artistUUID;
    }

    /**
     * gets genreUUID
     *
     * @return value of genreUUID
     */
    public String getGenreUUID() {
        return genreUUID;
    }

    /**
     * sets genreUUID
     *
     * @param genreUUID the value to set
     */
    public void setGenreUUID(String genreUUID) {
        this.genreUUID = genreUUID;
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
