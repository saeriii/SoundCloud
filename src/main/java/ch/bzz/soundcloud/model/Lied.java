package ch.bzz.soundcloud.model;

import java.time.LocalDate;

/**
 * Ein Lied eines Artists
 */

public class Lied {

    private String titel;
    private LocalDate hochladeDatum;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public LocalDate getHochladeDatum() {
        return hochladeDatum;
    }

    public void setHochladeDatum(LocalDate hochladeDatum) {
        this.hochladeDatum = hochladeDatum;
    }
}
