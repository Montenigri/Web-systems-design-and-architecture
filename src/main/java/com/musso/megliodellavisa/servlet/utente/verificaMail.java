package com.musso.megliodellavisa.servlet.utente;

import com.musso.megliodellavisa.model.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "verificamail", value = "/verificamail")
public class verificaMail extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaMail.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        Boolean mail = null;
        try {
            mail = UtenteService.checkEmail(request.getParameter("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(mail==null){
            String message = "email non esistente, impossibile proseguire, ";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/editCredito.jsp").forward(request, response);
        }

        String message = request.getParameter("email")+","+mail;
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaMail.jsp").forward(request, response);

    }
    public void destroy() {
    }
}