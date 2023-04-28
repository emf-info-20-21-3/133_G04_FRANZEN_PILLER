/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package controllers;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:TeamManager [generic]<br>
 * USAGE:
 * <pre>
 *        TeamManager client = new TeamManager();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author PillerD
 */
public class TeamManager {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/serviceREST1/webresources";

    public TeamManager() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public String modifyTeam() throws ClientErrorException {
        return webTarget.path("ModifyTeam").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, String.class);
    }

    public String deleteTeam() throws ClientErrorException {
        return webTarget.path("DeleteTeam").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).delete(String.class);
    }

    public String addTeam(String name, String code, int fk_location) throws ClientErrorException {
        return webTarget.path("AddTeam").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, String.class);
    }

    public String getXml() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public String getTeams() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("GetTeams");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
