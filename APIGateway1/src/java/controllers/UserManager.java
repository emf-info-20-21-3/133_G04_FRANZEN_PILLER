/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package controllers;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:UserManager [generic]<br>
 * USAGE:
 * <pre>
 *        UserManager client = new UserManager();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author PillerD
 */
public class UserManager {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/serviceREST2/webresources";

    public UserManager() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public String modifyUser() throws ClientErrorException {
        return webTarget.path("ModifyUser").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, String.class);
    }

    public String getUsers() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("GetUsers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String deleteUser() throws ClientErrorException {
        return webTarget.path("DeleteUser").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, String.class);
    }

    public String getXml() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public String createUser() throws ClientErrorException {
        return webTarget.path("CreateUser").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, String.class);
    }

    public <T> T isValidUser(Class<T> responseType) throws ClientErrorException {
        return webTarget.path("isValidUser").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, responseType);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void close() {
        client.close();
    }
    
}
