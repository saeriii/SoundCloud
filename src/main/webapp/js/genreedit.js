/**
 * view-controller for genreedit.html
 * @author Saranya Wenger
 */
document.addEventListener("DOMContentLoaded", () => {
    readGenre();

    document.getElementById("genreeditForm").addEventListener("submit", saveGenre);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a genre
 * @param event
 */
function saveGenre(event) {
    event.preventDefault();

    const genreForm = document.getElementById("genreeditForm");
    const formData = new FormData(genreForm);
    const data = new URLSearchParams(formData)

    let method;
    let url = "./resource/genre/";
    const genreUUID = getQueryParam("uuid")
    if (genreUUID == null) {
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
 * reads a genre
 */
function readGenre() {
    const genreUUID = getQueryParam("uuid");
    fetch("./resource/genre/read?uuid=" + genreUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showGenre(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a genre
 * @param data  the genre-data
 */
function showGenre(data) {
    document.getElementById("genreUUID").value = data.genreUUID;
    document.getElementById("genreName").value = data.genreName;
    document.getElementById("popularity").value = data.popularity;
}

/**
 * redirects to soundcloud.html
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./soundcloud.html";
}