package ch.bzz.soundcloud.model;

import java.util.List;

/**
 * Der Artist eines Liedes
 */

public class Artist {

    private List<Lied> lieder;
    private String vorname;
    private String nachname;
    private Integer anzahlLieder;

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

    public Integer getAnzahlLieder() {
        return anzahlLieder;
    }

    public void setAnzahlLieder(Integer anzahlLieder) {
        this.anzahlLieder = anzahlLieder;
    }
}
