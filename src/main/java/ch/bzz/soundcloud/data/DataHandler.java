package ch.bzz.soundcloud.data;

import ch.bzz.soundcloud.model.Artist;
import ch.bzz.soundcloud.model.Genre;
import ch.bzz.soundcloud.model.Song;
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
    private static List<Song> songList;
    private static List<Artist> artistList;
    private static List<Genre> genreList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setSongList(null);
        DataHandler.setArtistList(null);
        DataHandler.setGenreList(null);
    }

    /**
     * reads all songs
     * @return list of songs
     */
    public static List<Song> readAllSongs() {
        return getSongList();
    }

    /**
     * reads a s6ong by its uuid
     * @param songUUID
     * @return the Song (null=not found)
     */
    public static Song readSongbyUUID(String songUUID) {
        Song song = null;
        for (Song entry : getSongList()) {
            if (entry.getSongUUID().equals(songUUID)) {
                song = entry;
            }
        }
        return song;
    }

    /**
     * inserts a new song into the songList
     *
     * @param song the song to be saved
     */
    public static void insertSong(Song song) {
        getSongList().add(song);
        writeSongJSON();
    }

    /**
     * updates the songList
     */
    public static void updateSong() {
        writeSongJSON();
    }

    /**
     * deletes a song identified by the songUUID
     * @param songUUID
     * @return  success=true/false
     */
    public static boolean deleteSong(String songUUID) {
        Song song = readSongbyUUID(songUUID);
        if (song != null) {
            getSongList().remove(song);
            writeSongJSON();
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
     * @return the artist (null=not found)
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
     * @return the genre (null=not found)
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
     * @param genreUUID
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
     * reads the songs from the JSON-file
     */
    private static void readSongJSON() {
        try {
            String path = Config.getProperty("songJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Song[] songs = objectMapper.readValue(jsonData, Song[].class);
            for (Song song : songs) {
                getSongList().add(song);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the songList to the JSON-file
     */
    private static void writeSongJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String songPath = Config.getProperty("songJSON");
        try {
            fileOutputStream = new FileOutputStream(songPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getSongList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the artists from the JSON-file
     */
    private static void readArtistJSON() {
        try {
            String path = Config.getProperty("artistJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
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

        String artistPath = Config.getProperty("artistJSON");
        try {
            fileOutputStream = new FileOutputStream(artistPath);
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
            String path = Config.getProperty("genreJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
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

        String genrePath = Config.getProperty("genreJSON");
        try {
            fileOutputStream = new FileOutputStream(genrePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getGenreList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets songList
     *
     * @return value of songList
     */
    private static List<Song> getSongList() {
        if (songList == null) {
            setSongList(new ArrayList<>());
            readSongJSON();
        }
        return songList;
    }

    /**
     * sets songList
     *
     * @param songList the value to set
     */
    private static void setSongList(List<Song> songList) {
        DataHandler.songList = songList;
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