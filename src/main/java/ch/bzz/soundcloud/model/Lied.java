package ch.bzz.soundcloud.model;

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
    private LocalDate hochladeDatum;

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

    public String getLiedUUID() {
        return liedUUID;
    }

    public void setLiedUUID(String liedUUID) {
        this.liedUUID = liedUUID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getHochladeDatum() {
        return hochladeDatum;
    }

    public void setHochladeDatum(LocalDate hochladeDatum) {
        this.hochladeDatum = hochladeDatum;
    }
}
