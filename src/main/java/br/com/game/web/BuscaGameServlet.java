package br.com.game.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@WebServlet("buscaGame")
public class BuscaGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

            //GET do GAME Externo - Com aplicação rodando local
        URL url = new URL("http://localhost:9090/games/find/" + id);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String item = "";
        StringBuilder json = new StringBuilder();

        while ((item = bufferedReader.readLine()) != null) {
            json.append(item);
        }

        System.out.println("GET Game: " + json.toString());

        PrintWriter out = response.getWriter();
        out.println("<html><body><p> Buscou o game com sucesso! </p><p> Dados do Game Busca: " + json.toString() + " </p></body></html>");
    }
}
