package ch.bzz.soundcloud.service;

import ch.bzz.soundcloud.data.UserData;
import ch.bzz.soundcloud.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {

    /**
     * login user
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    )
    {
        int httpStatus;

        User user = UserData.findUser(username, password);
        if (user == null || user.getRole() == null || user.getRole().equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * logout current user
     *
     * @return Response object with guest-Cookie
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {

        return Response
                .status(200)
                .entity("")
                .build();
    }

}
