/**
 * view-controller for artistedit.html
 * @author Saranya Wenger
 */
document.addEventListener("DOMContentLoaded", () => {
    readArtist();

    document.getElementById("artisteditForm").addEventListener("submit", saveArtist);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of an artist
 * @param event
 */
function saveArtist(event) {
    event.preventDefault();

    const artistForm = document.getElementById("artisteditForm");
    const formData = new FormData(artistForm);
    const data = new URLSearchParams(formData)

    let method;
    let url = "./resource/artist/";
    const artistUUID = getQueryParam("uuid")
    if (artistUUID == null) {
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
 * reads an artist
 */
function readArtist() {
    const artistUUID = getQueryParam("uuid");
    fetch("./resource/artist/read?uuid=" + artistUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showArtist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of an artist
 * @param data  the artist-data
 */
function showArtist(data) {
    document.getElementById("artistUUID").value = data.artistUUID;
    document.getElementById("firstname").value = data.firstname;
    document.getElementById("surname").value = data.surname;
    document.getElementById("tel").value = data.tel;
    document.getElementById("numberOfSongs").value = data.numberOfSongs;
}

/**
 * redirects to soundcloud.html
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./soundcloud.html";
}