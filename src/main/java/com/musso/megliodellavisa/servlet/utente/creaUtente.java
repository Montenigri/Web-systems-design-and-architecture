package com.musso.megliodellavisa.servlet.utente;


import com.musso.megliodellavisa.model.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "registrazione", value = "/registrazione")
public class CreaUtente extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());
        getServletContext().getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            UtenteService.creaUtente(request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("email"), request.getParameter("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        Boolean email = null;
        try {
            email = UtenteService.checkEmail(request.getParameter("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!email){
            String message = request.getParameter("email")+","+email;
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/login" ).forward(request, response);
        }
        String message = request.getParameter("email")+","+email;
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/creditoresiduo").forward(request, response);

    }
    public void destroy() {
    }
}