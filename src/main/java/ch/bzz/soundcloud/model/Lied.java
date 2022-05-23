package ch.bzz.soundcloud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ch.bzz.soundcloud.data.DataHandler;

import java.time.LocalDate;

/**
 * Ein Lied eines Artists
 */

public class Lied {

    @JsonIgnore
    private Artist artist;

    private String liedUUID;
    private String titel;
    private Genre genre;

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hochladedatum;

    /**
     * gets the artistUUID from the Artist-object
     * @return
     */
    public String getArtistUUID() {
        return getArtist().getArtistUUID();
    }

    /**
    public void setArtistUUID(String artistUUID) {
        setArtist(new Artist());
        Artist artist = DataHandler.getInstance().readArtistByUUID(artistUUID);
        getArtist().setArtistUUID(artistUUID);
        getArtist().setArtist(artist.getArtist());

    }
     */

    /**
     * gets artist
     *
     * @return value of artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * gets artist
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
     * gets liedUUID
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
     * gets titel
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
     * gets genre
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
     * gets hochladedatum
     *
     * @param hochladedatum the value to set
     */
    public void setHochladedatum(LocalDate hochladedatum) {
        this.hochladedatum = hochladedatum;
    }

}
