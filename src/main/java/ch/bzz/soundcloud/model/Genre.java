package ch.bzz.soundcloud.model;

public class Genre {

    private String genreUUID;
    private String genre;
    private Integer popularity;

    public String getGenreUUID() {
        return genreUUID;
    }

    public void setGenreUUID(String genreUUID) {
        this.genreUUID = genreUUID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
}
