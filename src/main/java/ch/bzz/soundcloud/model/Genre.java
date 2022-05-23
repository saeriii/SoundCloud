package ch.bzz.soundcloud.model;

public class Genre {

    private String genreUUID;
    private String genre;
    private Integer popularity;

    /**
     * gets genreUUID
     *
     * @return value of genreUUID
     */
    public String getGenreUUID() {
        return genreUUID;
    }

    /**
     * gets genreUUID
     *
     * @param genreUUID the value to set
     */
    public void setGenreUUID(String genreUUID) {
        this.genreUUID = genreUUID;
    }

    /**
     * gets genre
     *
     * @return value of genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * gets genre
     *
     * @param genre the value to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * gets popularity
     *
     * @return value of popularity
     */
    public Integer getPopularity() {
        return popularity;
    }

    /**
     * gets popularity
     *
     * @param popularity the value to set
     */
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

}
