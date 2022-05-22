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
     * reads all lieder
     * @return list of lieder
     */
    public List<Lied> readAllLied() {
        return getLiedList();
    }

    /**
     * reads a lied by its uuid
     * @param liedUUID
     * @return the lied (null=not found)
     */
    public Lied readLiedByUUID(String liedUUID) {
        Lied lied = null;
        for (Lied entry : getBookList()) {
            if (entry.getLiedUUID().equals(liedUUID)) {
                lied = entry;
            }
        }
        return lied;
    }

    /**
     * reads all Publishers
     * @return list of publishers
     */
    public List<Publisher> readAllPublishers() {

        return getPublisherList();
    }

    /**
     * reads a publisher by its uuid
     * @param publisherUUID
     * @return the Publisher (null=not found)
     */
    public Publisher readPublisherByUUID(String publisherUUID) {
        Publisher publisher = null;
        for (Publisher entry : getPublisherList()) {
            if (entry.getPublisherUUID().equals(publisherUUID)) {
                publisher = entry;
            }
        }
        return publisher;
    }

    /**
     * reads the books from the JSON-file
     */
    private void readBookJSON() {
        try {
            String path = Config.getProperty("bookJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Book[] books = objectMapper.readValue(jsonData, Book[].class);
            for (Book book : books) {
                getBookList().add(book);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private void readPublisherJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("publisherJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Publisher[] publishers = objectMapper.readValue(jsonData, Publisher[].class);
            for (Publisher publisher : publishers) {
                getPublisherList().add(publisher);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets bookList
     *
     * @return value of bookList
     */
    private List<Book> getBookList() {
        return bookList;
    }

    /**
     * sets bookList
     *
     * @param bookList the value to set
     */
    private void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Publisher> getPublisherList() {
        return publisherList;
    }

    /**
     * sets publisherList
     *
     * @param publisherList the value to set
     */
    private void setPublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }


}