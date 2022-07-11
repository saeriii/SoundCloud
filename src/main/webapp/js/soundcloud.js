/**
 * view-controller for soundcloud.html
 * @author Saranya Wenger
 */
document.addEventListener("DOMContentLoaded", () => {
    readSongs();
});

/**
 * reads all songs
 */
function readSongs() {
    fetch("./resource/song/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showSonglist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the songlist as a table
 * @param data the songs
 */
function showSonglist(data) {
    let tBody = document.getElementById("songlist");
    data.forEach(song => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = song.title;
        row.insertCell(-1).innerHTML = song.uploadDate;
        row.insertCell(-1).innerHTML = song.genre.genreName;

        let button = document.createElement("button");
        button.innerHTML = "Löschen";
        button.type = "button";
        button.name = "deleteSong";
        button.setAttribute("data-songuuid", song.songUUID);0
        button.addEventListener("click", deleteSong);

        row.insertCell(-1).appendChild(button);
    });
}

/**
 * deletes a song
 * @param event the click-event
 */
function deleteSong(event) {
    const button = event.target;
    const songUUID = button.getAttribute("data-songuuid");

    fetch("./resource/song/delete?uuid=" + songUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./soundcloud.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}