package com.musso.megliodellavisa.servlet.utente;


import com.musso.megliodellavisa.model.CartaService;
import com.musso.megliodellavisa.model.Utente;
import com.musso.megliodellavisa.model.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

@WebServlet(name = "cambiaruolo", value = "/cambiaruolo")
public class cambiaRuolo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");
        if (utente!=null && Objects.equals(utente.getRuolo(), "admin")){
            getServletContext().getRequestDispatcher("/WEB-INF/view/modificaRuolo.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");

        if (utente!=null && Objects.equals(utente.getRuolo(), "admin")) {

            Boolean checkMail = null;
            try {
                checkMail = UtenteService.checkEmail(request.getParameter("email"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(checkMail==null){
                String message = "email non esistente, impossibile proseguire, ";
                request.setAttribute("message",message);
                getServletContext().getRequestDispatcher("/WEB-INF/view/modificaRuolo.jsp").forward(request, response);
            }


            try {
                UtenteService.cambiaRuolo(request.getParameter("ruolo").toLowerCase(), request.getParameter("email"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.setContentType("application/json");
            String ruolo = null;
            try {
                ruolo = UtenteService.getRuolo(request.getParameter("email"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String message = request.getParameter("email") + "," + ruolo;
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/modificaRuolo.jsp").forward(request, response);


        }

    }
    public void destroy() {
    }
}