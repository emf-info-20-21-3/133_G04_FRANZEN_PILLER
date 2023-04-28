/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import beans.User;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import workers.WrkDB;

/**
 * REST Web Service
 *
 * @author FranzenL
 */
@Path("generic")
public class UserManager {

    @Context
    private UriInfo context;

    private WrkDB wrkDB;

    /**
     * Creates a new instance of GenericResource
     */
    public UserManager() {
        wrkDB = new WrkDB();

    }

    /**
     * Retrieves representation of an instance of controllers.UserManager
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserManager
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @GET
    @Path("GetUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        //On initialise le constructeur.
        Gson builder = new Gson();
        //Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        String toJson = builder.toJson(wrkDB.dbGetUsers());
        //On affiche notre résultat.
        return "{\"type\":" + toJson + "}";
    }

    @POST
    @Path("CreateUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createUser(@FormParam("username") String username, @FormParam("password") String password) {
        String s;
        System.out.println("username" + username);
        System.out.println("password " + password);
        if (wrkDB.dbCreateUser(username, password)) {
            s = "OK";
        } else {
            s = "KO";
        }
        return s;
    }

    @POST
    @Path("ModifyUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String modifyUser(@FormParam("pk_user") int pk_user, @FormParam("username") String username, @FormParam("password") String password) {
        String s;
        try {
            //User u = wrkDB.getUser(username);
            wrkDB.dbModifyUser(pk_user, username, password);
            s = "OK";
        } catch (Exception e) {
            s = "KO";
        }
        return s;
    }

    @POST
    @Path("DeleteUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String deleteUser(@FormParam("pk_user") int pk_user) {
        String s;
        try {
            //User u = wrkDB.getUser(pk_user);
            wrkDB.dbDeleteUser(pk_user);
            s = "OK";
        } catch (Exception e) {
            s = "KO";
        }
        return s;
    }

    @POST
    @Path("isValidUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String isValidUser(@FormParam("username") String username, @FormParam("password") String password) {
        String s;
        try {
            //User u = wrkDB.getUser(pk_user);
            wrkDB.isValidUser(username, password);
            s = "OK";
        } catch (Exception e) {
            s = "KO";
        }
        return s;
    }
}
