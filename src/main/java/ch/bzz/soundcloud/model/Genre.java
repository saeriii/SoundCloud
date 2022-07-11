package ch.bzz.soundcloud.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class Genre {

    @FormParam("genreUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String genreUUID;

    @FormParam("genreName")
    @NotEmpty
    @Size(min=1,max=30)
    private String genreName;

    @FormParam("popularity")
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
     * sets genreUUID
     *
     * @param genreUUID the value to set
     */
    public void setGenreUUID(String genreUUID) {
        this.genreUUID = genreUUID;
    }

    /**
     * gets genreName
     *
     * @return value of genreName
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * sets genre
     *
     * @param genreName the value to set
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
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
     * sets popularity
     *
     * @param popularity the value to set
     */
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
}
