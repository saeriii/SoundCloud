/**
 * view-controller for songedit.html
 * @author Saranya Wenger
 */
document.addEventListener("DOMContentLoaded", () => {
    readGenres();
    readSong();

    document.getElementById("songeditForm").addEventListener("submit", saveSong);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a song
 * @param event
 */
function saveSong(event) {
    event.preventDefault();

    const songForm = document.getElementById("songeditForm");
    const formData = new FormData(songForm);
    const data = new URLSearchParams(formData)

    let method;
    let url = "./resource/song/";
    const songUUID = getQueryParam("uuid")
    if (songUUID == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                console.log(response);
            } else return response;
        })
        .then()
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * reads a song
 */
function readSong() {
    const songUUID = getQueryParam("uuid");
    fetch("./resource/song/read?uuid=" + songUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showSong(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a song
 * @param data  the song-data
 */
function showSong(data) {
    document.getElementById("songUUID").value = data.songUUID;
    document.getElementById("title").value = data.title;
    document.getElementById("uploadDate").value = data.uploadDate;
    document.getElementById("genre").value = data.genreUUID;
}

/**
 * reads all genres as an array
 */
function readGenres() {

    fetch("./resource/genre/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showGenres(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show all genres as a dropdown
 * @param data
 */
function showGenres(data) {
    let dropdown = document.getElementById("genre");
    data.forEach(genre => {
        let option = document.createElement("option");
        option.text = genre.genreName;
        option.value = genre.genreUUID;
        dropdown.add(option)
    })
}

/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./soundcloud.html";
}