/**
 * view-controller for genrelist.html
 * @author Saranya Wenger
 */
document.addEventListener("DOMContentLoaded", () => {
    readGenres();
});

/**
 * reads all genres
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
            showGenrelist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the genrelist as a table
 * @param data the genres
 */
function showGenrelist(data) {
    let tBody = document.getElementById("genrelist");
    data.forEach(genre => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = genre.genreName;
        row.insertCell(-1).innerHTML = genre.popularity;

        let button = document.createElement("button");
        button.innerHTML = "Löschen";
        button.type = "button";
        button.name = "deleteGenre";
        button.setAttribute("data-genreuuid", genre.genreUUID);
        button.addEventListener("click", deleteGenre);

        row.insertCell(-1).appendChild(button);
    });
}

/**
 * deletes a genre
 * @param event the click-event
 */
function deleteGenre(event) {
    const button = event.target;
    const genreUUID = button.getAttribute("data-genreuuid");

    fetch("./resource/genre/delete?uuid=" + genreUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./genrelist.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}