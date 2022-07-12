/**
 * view-controller for artistlist.html
 * @author Saranya Wenger
 */
document.addEventListener("DOMContentLoaded", () => {
    readArtists();
});

/**
 * reads all artists
 */
function readArtists() {
    fetch("./resource/artist/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showArtistlist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the artistlist as a table
 * @param data the artists
 */
function showArtistlist(data) {
    let tBody = document.getElementById("artistlist");
    data.forEach(artist => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = artist.firstname;
        row.insertCell(-1).innerHTML = artist.surname;
        row.insertCell(-1).innerHTML = artist.tel;
        row.insertCell(-1).innerHTML = artist.numberOfSongs;

        let button = document.createElement("button");
        button.innerHTML = "Löschen";
        button.type = "button";
        button.name = "deleteArtist";
        button.setAttribute("data-artistuuid", artist.artistUUID);0
        button.addEventListener("click", deleteArtist);

        row.insertCell(-1).appendChild(button);
    });
}

/**
 * deletes an artist
 * @param event the click-event
 */
function deleteArtist(event) {
    const button = event.target;
    const artistUUID = button.getAttribute("data-artistuuid");

    fetch("./resource/artist/delete?uuid=" + artistUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./artistlist.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}