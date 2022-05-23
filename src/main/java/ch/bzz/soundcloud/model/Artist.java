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

    /**
     * gets artistUUID
     *
     * @return value of artistUUID
     */
    public String getArtistUUID() {
        return artistUUID;
    }

    /**
     * gets artistUUID
     *
     * @param artistUUID the value to set
     */
    public void setArtistUUID(String artistUUID) {
        this.artistUUID = artistUUID;
    }

    /**
     * gets lieder
     *
     * @return value of lieder
     */
    public List<Lied> getLieder() {
        return lieder;
    }

    /**
     * gets lieder
     *
     * @param lieder the value to set
     */
    public void setLieder(List<Lied> lieder) {
        this.lieder = lieder;
    }

    /**
     * gets vorname
     *
     * @return value of vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * gets vorname
     *
     * @param vorname the value to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * gets nachname
     *
     * @return value of nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * gets nachname
     *
     * @param nachname the value to set
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * gets telNr
     *
     * @return value of telNr
     */
    public String getTelNr() {
        return telNr;
    }

    /**
     * gets telNr
     *
     * @param telNr the value to set
     */
    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    /**
     * gets anzahlLieder
     *
     * @return value of anzahlLieder
     */
    public Integer getAnzahlLieder() {
        return anzahlLieder;
    }

    /**
     * gets anzahlLieder
     *
     * @param anzahlLieder the value to set
     */
    public void setAnzahlLieder(Integer anzahlLieder) {
        this.anzahlLieder = anzahlLieder;
    }
}

