/**
 * view-controller for login.html
 *
 * @author Saranya Wenger
 */

/**
 * register listeners
 */
$(document).ready(function () {
    /**
     * listener for submitting the form sends the login data to the web service
     */
    $("#loginForm").submit(sendLogin);

    /**
     * listener for button (Abmelden)
     */
    $("#logoff").click(sendLogoff());
});

/**
 * sends the login-request
 */
function sendLogin() {
    form.preventDefault()
    $
        .ajax({
            url: "./resource/user/login",
            dataType: "text",
            type: "POST",
            data: $("#loginForm").serialize()
        })
        .done(function () {
            window.location.href = "./soundcloud.html";
        })
        .fail(function (xhr, status, errorThrown){
            if (xhr.status == 404) {
                $("#message").text("Benutzername/Passwort unbekannt");
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        })
}

/**
 * sends the logoff-request
 */
function sendLogoff() {
    form.preventDefault()
    $
        .ajax({
            url: "./resource/user/logout",
            dataType: "text",
            type: "DELETE",
        })
        .done(function () {
            window.location.href = "./login.html";
        })
        .fail(function (xhr, status, errorThrown){

        })
}