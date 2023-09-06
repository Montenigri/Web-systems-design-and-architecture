package com.musso.megliodellavisa.servlet.transazioni;

import com.musso.megliodellavisa.model.Transazioni;
import com.musso.megliodellavisa.model.TransazioniService;
import com.musso.megliodellavisa.model.Utente;
import com.musso.megliodellavisa.model.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "report", value = "/report")
public class report extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("currentUser");

        if (utente!=null && (Objects.equals(utente.getRuolo(), "admin")||Objects.equals(utente.getRuolo(),"negoziante"))){
            String email = utente.getEmail();
            String ruolo = utente.getRuolo();
            List<Transazioni> listaTransazioni = new ArrayList<>();
            try {
                listaTransazioni = TransazioniService.getTransazione(email);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("transazioni", listaTransazioni);
            request.setAttribute("errors", new HashMap<>());

            getServletContext().getRequestDispatcher("/WEB-INF/view/report.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    public void destroy() {
    }
}