/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PillerD
 */
@WebServlet(name = "Gateway", urlPatterns = {"/Gateway"})
public class Gateway extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20); //temps en seconde

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String adminPassword = request.getParameter("adminPassword");
        String action = request.getParameter("action");

        if ("teams".equals(action)) {
            TeamManager teamManager = new TeamManager();
            String teams = teamManager.getTeams();
            request.setAttribute("teams", teams);
            System.out.println(teams);

        } else if ("login".equals(action)) {
            // call verifyUser(username, password)
            UserManager userManager = new UserManager();
            String users = userManager.getUsers();
            
            
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        // get the teams and add them to the HTML response
        TeamManager teamManager = new TeamManager();
        String teams = teamManager.getTeams();
        out.println("<p>" + teams + "</p>");

        out.println("</body></html>");
        out.close();
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        int league = Integer.parseInt(request.getParameter("location"));

        TeamManager teamManager = new TeamManager();
        String result = teamManager.addTeam(name, code, league);

        out.println("<html><body>");
        out.println("<p>" + result + "</p>");
        out.println("</body></html>");
        out.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
