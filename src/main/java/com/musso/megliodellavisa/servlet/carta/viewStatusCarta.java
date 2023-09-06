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

@WebServlet(name = "viewstatus", value = "/viewstatus")
public class viewStatusCarta extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");
        String ruolo = utente.getRuolo();
        request.setAttribute("errors", new HashMap<>());
        if (utente!=null && (Objects.equals(ruolo, "admin")||Objects.equals(ruolo,"negoziante"))){
            getServletContext().getRequestDispatcher("/WEB-INF/view/viewStatus.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        Boolean stato = null;
        try {
            stato = CartaService.checkStatus(request.getParameter("numeroCarta"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(stato==null){
            String message = "Carta non esistente, impossibile proseguire, ";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/editCredito.jsp").forward(request, response);
        }
        String message = request.getParameter("numeroCarta")+","+stato;
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/viewStatus.jsp").forward(request, response);


    }
    public void destroy() {
    }
}