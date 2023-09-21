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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

@WebServlet(name = "editstatus", value = "/editstatus")
public class EditStatusCarta extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());


        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");

        if (utente!=null && Objects.equals(utente.getRuolo(), "admin")){
            getServletContext().getRequestDispatcher("/WEB-INF/view/editStatus.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");

        if (utente!=null && Objects.equals(utente.getRuolo(), "admin")){

            Boolean checkCarta = null;
            try {
                checkCarta = CartaService.checkStatus(request.getParameter("numeroCarta"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(checkCarta==null){
                String message = "Carta non esistente, impossibile proseguire, ";
                request.setAttribute("message",message);
                getServletContext().getRequestDispatcher("/WEB-INF/view/EditCredito.jsp").forward(request, response);
            }

            try {
                CartaService.cambiaStato(request.getParameter("numeroCarta"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.setContentType("application/json");
            Boolean stato = null;
            System.out.println(stato);

            try {
                stato = CartaService.checkStatus(request.getParameter("numeroCarta"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            System.out.println(stato);

            String message = request.getParameter("numeroCarta")+","+stato;
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/editStatus.jsp").forward(request, response);


        }


    }
    public void destroy() {
    }
}