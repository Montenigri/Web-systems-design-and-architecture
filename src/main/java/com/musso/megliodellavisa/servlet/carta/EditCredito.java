package com.musso.megliodellavisa.servlet.carta;

import com.musso.megliodellavisa.model.CartaService;
import com.musso.megliodellavisa.model.TransazioniService;
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

@WebServlet(name = "editcredito", value = "/editcredito")
public class EditCredito extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());
            HttpSession session = request.getSession();
            Utente utente = (Utente) session.getAttribute("currentUser");

            if (utente!=null && (Objects.equals(utente.getRuolo(), "admin")||Objects.equals(utente.getRuolo(),"negoziante"))){
                getServletContext().getRequestDispatcher("/WEB-INF/view/editCredito.jsp").forward(request, response);
            }

        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");
        if (utente != null && (Objects.equals(utente.getRuolo(), "admin")||Objects.equals(utente.getRuolo(),"negoziante"))) {

            String email = utente.getEmail();

            Boolean checkCarta = null;
            try {
                checkCarta = CartaService.checkStatus(request.getParameter("numeroCarta"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(checkCarta==null){
                String message = "Carta non esistente, impossibile proseguire, ";
                request.setAttribute("message",message);
                getServletContext().getRequestDispatcher("/WEB-INF/view/editCredito.jsp").forward(request, response);
            }

            try {
                CartaService.editCredito(request.getParameter("numeroCarta"), request.getParameter("editCredito"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            response.setContentType("application/json");
            String credito = null;


            try {
                credito = String.valueOf(CartaService.creditoResiduo(request.getParameter("numeroCarta")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            try {
                TransazioniService.nuovaTransazione(email, request.getParameter("editCredito"), request.getParameter("numeroCarta"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String message = request.getParameter("numeroCarta")+","+credito+"," + email;
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/editCredito.jsp").forward(request, response);


        }
    }
    public void destroy() {
    }
}