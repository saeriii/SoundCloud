package ch.bzz.soundcloud.model;

import java.time.LocalDate;

/**
 * Ein Lied eines Artists
 */

public class Lied {

    private String liedUUID;
    private String titel;
    private Genre genre;
    private LocalDate hochladeDatum;

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
