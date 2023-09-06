package com.musso.megliodellavisa.servlet.carta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import com.musso.megliodellavisa.model.CartaService;

@WebServlet(name = "creditoresiduo", value = "/creditoresiduo")
public class CreditoResiduo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("errors", new HashMap<>());
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");

        Boolean checkCarta = null;
        try {
            checkCarta = CartaService.checkStatus(request.getParameter("numeroCarta"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(checkCarta==null){
            String message = "Carta non esistente, impossibile proseguire";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);
        }
        String credito = "0";
        try {
            credito = String.valueOf(CartaService.creditoResiduo(request.getParameter("numeroCarta")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String message = request.getParameter("numeroCarta")+","+credito;
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/WEB-INF/view/verificaCredito.jsp").forward(request, response);

    }

    public void destroy() {
    }
}