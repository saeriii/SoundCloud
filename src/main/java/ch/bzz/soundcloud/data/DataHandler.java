package ch.bzz.soundcloud.data;

import ch.bzz.soundcloud.model.Artist;
import ch.bzz.soundcloud.model.Lied;
import ch.bzz.soundcloud.model.Genre;
import ch.bzz.soundcloud.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Singleton;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
@Singleton
public class DataHandler {
    private static DataHandler instance;
    private static List<Lied> liedList;
    private static List<Artist> artistList;
    private static List<Genre> genreList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public synchronized static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Lieder
     * @return list of Lieder
     */
    public static List<Lied> readAllLieder() {
        return getLiedList();
    }

    /**
     * reads a Lied by its uuid
     * @param liedUUID
     * @return the Lied (null=not found)
     */
    public static Lied readLiedByUUID(String liedUUID) {
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
    public static List<Artist> readAllArtists() {
        return getArtistList();
    }

    /**
     * reads an artist by its uuid
     * @param artistUUID
     * @return the Artist (null=not found)
     */
    public static Artist readArtistByUUID(String artistUUID) {
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
    public static List<Genre> readAllGenres() {

        return getGenreList();
    }

    /**
     * reads a genre by its uuid
     * @param genreUUID
     * @return the Genre (null=not found)
     */
    public static Genre readGenrebyUUID(String genreUUID) {
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
    private static void readLiedJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("liedJSON")
                    )
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
    private static void readArtistJSON() {
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
    private static void readGenreJSON() {
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
    private static List<Lied> getLiedList() {
        return liedList;
    }

    /**
     * sets liedList
     *
     * @param liedList the value to set
     */
    private static void setLiedList(List<Lied> liedList) {
        DataHandler.liedList = liedList;
    }

    /**
     * gets artistList
     *
     * @return value of artistList
     */
    private static List<Artist> getArtistList() {
        return artistList;
    }

    /**
     * sets artistList
     *
     * @param artistList the value to set
     */
    private static void setArtistList(List<Artist> artistList) {
        DataHandler.artistList = artistList;
    }

    /**
     * gets genreList
     *
     * @return value of genreList
     */
    private static List<Genre> getGenreList() {
        return genreList;
    }

    /**
     * sets genreList
     *
     * @param genreList the value to set
     */
    private static void setGenreList(List<Genre> genreList) {
        DataHandler.genreList = genreList;
    }

}