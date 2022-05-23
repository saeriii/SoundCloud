package ch.bzz.soundcloud.model;

import java.util.List;

/**
 * Der Artist eines Liedes
 */

public class Artist {

    private String artistUUID;
    private List<Lied> lieder;
    private String vorname;
    private String nachname;
    private String telNr;
    private Integer anzahlLieder;

    public String getArtistUUID() {
        return artistUUID;
    }

    public void setArtistUUID(String artistUUID) {
        this.artistUUID = artistUUID;
    }

    public List<Lied> getLieder() {
        return lieder;
    }

    public void setLieder(List<Lied> lieder) {
        this.lieder = lieder;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public Integer getAnzahlLieder() {
        return anzahlLieder;
    }

    public void setAnzahlLieder(Integer anzahlLieder) {
        this.anzahlLieder = anzahlLieder;
    }

}

