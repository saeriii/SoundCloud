package ch.bzz.soundcloud.data;

import ch.bzz.soundcloud.model.Artist;
import ch.bzz.soundcloud.model.Lied;
import ch.bzz.soundcloud.model.Genre;
import ch.bzz.soundcloud.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Lied> liedList;
    private List<Artist> artistList;
    private List<Genre> genreList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setLiedList(new ArrayList<>());
        readLiedJSON();
        setArtistList(new ArrayList<>());
        readArtistJSON();
        setGenreList(new ArrayList<>());
        readGenreJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Lieder
     * @return list of Lieder
     */
    public List<Lied> readAllLieder() {
        return getLiedList();
    }

    /**
     * reads a Lied by its uuid
     * @param liedUUID
     * @return the Lied (null=not found)
     */
    public Lied readLiedByUUID(String liedUUID) {
        Lied lied = null;
        for (Lied entry : getLiedList()) {
            if (entry.getLiedUUID().equals(liedUUID)) {
                lied = entry;
            }
        }
        return lied;
    }

    /**
     * reads all artists
     * @return list of artists
     */
    public List<Artist> readAllArtists() {
        return getArtistList();
    }

    /**
     * reads an artist by its uuid
     * @param artistUUID
     * @return the Artist (null=not found)
     */
    public Artist readArtistByUUID(String artistUUID) {
        Artist artist = null;
        for (Artist entry : getArtistList()) {
            if (entry.getArtistUUID().equals(artistUUID)) {
                artist = entry;
            }
        }
        return artist;
    }

    /**
     * reads all genres
     * @return list of genres
     */
    public List<Genre> readAllGenres() {

        return getGenreList();
    }

    /**
     * reads a genre by its uuid
     * @param genreUUID
     * @return the Genre (null=not found)
     */
    public Genre readGenrebyUUID(String genreUUID) {
        Genre genre = null;
        for (Genre entry : getGenreList()) {
            if (entry.getGenreUUID().equals(genreUUID)) {
                genre = entry;
            }
        }
        return genre;
    }

    /**
     * reads the Lieder from the JSON-file
     */
    private void readLiedJSON() {
        try {
            String path = Config.getProperty("liedJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Lied[] lieder = objectMapper.readValue(jsonData, Lied[].class);
            for (Lied lied : lieder) {
                getLiedList().add(lied);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the artists from the JSON-file
     */
    private void readArtistJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("artistJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Artist[] artists = objectMapper.readValue(jsonData, Artist[].class);
            for (Artist artist : artists) {
                getArtistList().add(artist);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the genre from the JSON-file
     */
    private void readGenreJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("genreJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Genre[] genres = objectMapper.readValue(jsonData, Genre[].class);
            for (Genre genre : genres) {
                getGenreList().add(genre);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets liedList
     *
     * @return value of liedList
     */
    private List<Lied> getLiedList() {
        return liedList;
    }

    /**
     * sets liedList
     *
     * @param liedList the value to set
     */
    private void setLiedList(List<Lied> liedList) {
        this.liedList = liedList;
    }

    /**
     * gets artistList
     *
     * @return value of artistList
     */
    private List<Artist> getArtistList() {
        return artistList;
    }

    /**
     * sets artistList
     *
     * @param artistList the value to set
     */
    private void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    /**
     * gets genreList
     *
     * @return value of genreList
     */
    private List<Genre> getGenreList() {
        return genreList;
    }

    /**
     * sets genreList
     *
     * @param genreList the value to set
     */
    private void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }


}