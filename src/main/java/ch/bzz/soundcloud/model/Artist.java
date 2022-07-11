package ch.bzz.soundcloud.model;

import ch.bzz.soundcloud.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;

/**
 * The artist of a song
 */

public class Artist {
    @JsonIgnore
    private List<Song> songs;

    @FormParam("artistUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String artistUUID;

    @FormParam("firstname")
    @NotEmpty
    @Size(min=1,max=50)
    private String firstname;

    @FormParam("surname")
    @NotEmpty
    @Size(min=1,max=50)
    private String surname;

    @FormParam("tel")
    @NotEmpty
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$")
    private String tel;

    @FormParam("numberOfSongs")
    @Positive
    private Integer numberOfSongs;

    /**
     * gets the songList
     *
     * @return value of songs
     */
    public List<Song> getSongList() {
        return songs;
    }

    /**
     * sets the song list
     *
     * @param songs the value to set
     */
    public void setSongList(List<Song> songs) {
        this.songs = songs;
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

    /**
     * sets the songs from their UUIDs
     *
     * @param uuidList  list of song-uuids
     */
    public void setSongUUID(List<String> uuidList) {

        this.setSongList(new ArrayList<>());
        for (String uuid : uuidList) {
            Song song = DataHandler.readSongbyUUID(uuid);
            this.getSongList().add(song);
        }
    }

    /**
     * gets all the songs of an artist
     * @return  all songs as comma separated string
     */
    @JsonIgnore
    public String getSongs() {
        StringBuilder songs = new StringBuilder();
        if (this.getSongList() != null) {
            List<String> uuidList = new ArrayList<>();
            for (Song song : this.getSongList()) {
                songs.append(song.getTitle()).append(", ");
                songs.append(song.getUploadDate()).append(", ");
            }

        }
        return (songs.length() == 0)
                ? null
                : (songs.substring(0, songs.length() - 2));
    }

}

