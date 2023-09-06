package com.musso.megliodellavisa.servlet.carta;

import com.musso.megliodellavisa.model.CartaService;
import com.musso.megliodellavisa.model.Utente;
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

@WebServlet(name = "creacarta", value = "/creacarta")
public class CreaCarta extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");

        if (utente!=null && Objects.equals(utente.getRuolo(), "admin")){
            getServletContext().getRequestDispatcher("/WEB-INF/view/creaCarta.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");

        if (utente!=null && Objects.equals(utente.getRuolo(), "admin")){
            try {
                CartaService.creaCarta(Float.valueOf(request.getParameter("credito")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.setContentType("application/json");
            Integer numeroCarta = null;
            try {
                numeroCarta = CartaService.getUltimoNumeroCarta();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String message = numeroCarta+","+request.getParameter("credito");
            request.setAttribute("message",message );
            getServletContext().getRequestDispatcher("/WEB-INF/view/creaCarta.jsp").forward(request, response);

        }
        }
    public void destroy() {
    }
}