package ch.bzz.soundcloud.data;

import ch.bzz.soundcloud.model.Artist;
import ch.bzz.soundcloud.model.Genre;
import ch.bzz.soundcloud.model.Lied;
import ch.bzz.soundcloud.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public final class DataHandler {
    private static List<Lied> liedList;
    private static List<Artist> artistList;
    private static List<Genre> genreList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
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
     * inserts a new Lied into the liedList
     *
     * @param lied the Lied to be saved
     */
    public static void insertLied(Lied lied) {
        getLiedList().add(lied);
        writeLiedJSON();
    }

    /**
     * updates the liedList
     */
    public static void updateLied() {
        writeLiedJSON();
    }

    /**
     * deletes a Lied identified by the liedUUID
     * @param liedUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteLied(String liedUUID) {
        Lied lied = readLiedByUUID(liedUUID);
        if (lied != null) {
            getLiedList().remove(lied);
            writeLiedJSON();
            return true;
        } else {
            return false;
        }
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
     * inserts a new artist into the artistList
     *
     * @param artist the artist to be saved
     */
    public static void insertArtist(Artist artist) {
        getArtistList().add(artist);
        writeArtistJSON();
    }

    /**
     * updates the artistList
     */
    public static void updateArtist() {
        writeArtistJSON();
    }

    /**
     * deletes an artist identified by the artistUUID
     * @param artistUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteArtist(String artistUUID) {
        Artist artist = readArtistByUUID(artistUUID);
        if (artist != null) {
            getArtistList().remove(artist);
            writeArtistJSON();
            return true;
        } else {
            return false;
        }
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
     * inserts a new genre into the genreList
     *
     * @param genre the genre to be saved
     */
    public static void insertGenre(Genre genre) {
        getGenreList().add(genre);
        writeGenreJSON();
    }

    /**
     * updates the genreList
     */
    public static void updateGenre() {
        writeGenreJSON();
    }

    /**
     * deletes a genre identified by the genreUUID
     * @param genreUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteGenre(String genreUUID) {
        Genre genre = readGenrebyUUID(genreUUID);
        if (genre != null) {
            getGenreList().remove(genre);
            writeGenreJSON();
            return true;
        } else {
            return false;
        }
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
     * writes the liedList to the JSON-file
     */
    private static void writeLiedJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("liedJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getLiedList());
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
     * writes the artistList to the JSON-file
     */
    private static void writeArtistJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("publisherJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getArtistList());
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
     * writes the genreList to the JSON-file
     */
    private static void writeGenreJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("publisherJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getGenreList());
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
        if (liedList == null) {
            setLiedList(new ArrayList<>());
            readLiedJSON();
        }
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
        if (artistList == null) {
            setArtistList(new ArrayList<>());
            readArtistJSON();
        }
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
        if (genreList == null) {
            setGenreList(new ArrayList<>());
            readGenreJSON();
        }
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