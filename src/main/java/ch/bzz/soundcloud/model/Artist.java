package ch.bzz.soundcloud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * The artist of a song
 */

public class Artist {

    private String artistUUID;

    @JsonIgnore
    private List<Song> songs;

    private String firstname;
    private String surname;
    private String tel;
    private Integer numberOfSongs;

    public Artist() {
        setSongs(new ArrayList<>());
    }

    /**
     * gets artistUUID
     *
     * @return value of artistUUID
     */
    public String getArtistUUID() {
        return artistUUID;
    }

    /**
     * sets artistUUID
     *
     * @param artistUUID the value to set
     */
    public void setArtistUUID(String artistUUID) {
        this.artistUUID = artistUUID;
    }

    /**
     * gets songs
     *
     * @return value of songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * sets songs
     *
     * @param songs the value to set
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * gets firstname
     *
     * @return value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * sets firstname
     *
     * @param firstname the value to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * gets surname
     *
     * @return value of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * sets surname
     *
     * @param surname the value to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * gets tel
     *
     * @return value of tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * sets tel
     *
     * @param tel the value to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * gets numberOfSongs
     *
     * @return value of numberOfSongs
     */
    public Integer getNumberOfSongs() {
        return numberOfSongs;
    }

    /**
     * sets numberOfSongs
     *
     * @param numberOfSongs the value to set
     */
    public void setNumberOfSongs(Integer numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }
}

