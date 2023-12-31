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

@WebServlet("/buscaGame")
public class BuscaGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        URLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            // Envia a requisição GET para o servidor externo
            URL url = new URL("http://localhost:9090/games/find/" + id);
            connection = url.openConnection();
            inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String item;
            StringBuilder json = new StringBuilder();

            while ((item = bufferedReader.readLine()) != null) {
                json.append(item);
            }

            System.out.println("GET One Game: " + json.toString());

            PrintWriter out = response.getWriter();
            out.println("<html><body><p> Buscou o game com sucesso! </p><p> Dados do Game na busca: " + json.toString() + " </p></body></html>");
        } finally {
            // Fechar as instâncias no bloco finally para garantir que elas sejam fechadas,
            // mesmo que ocorra uma exceção
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
