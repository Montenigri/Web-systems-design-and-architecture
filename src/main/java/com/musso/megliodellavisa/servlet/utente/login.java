package com.musso.megliodellavisa.servlet.utente;


import com.musso.megliodellavisa.model.Utente;
import com.musso.megliodellavisa.model.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");
        if (utente!=null){
            getServletContext().getRequestDispatcher("/creditoresiduo").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Utente utente = new Utente();
        try {
            utente = UtenteService.getUtente(request.getParameter("email"), request.getParameter("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (utente.getNome()==null){
            String message = request.getParameter("email");
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);

        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*5);
        session.setAttribute("currentUser", utente);
        getServletContext().getRequestDispatcher("/creditoresiduo").forward(request, response);
    }
    public void destroy() {
    }
}