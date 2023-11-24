package br.com.game.web;

import br.com.game.web.entity.Game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/adicionaGame")
public class AdicionaGameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pegar os parâmetros da tela
        String img = request.getParameter("img");
        String title = request.getParameter("title");
        String starStr = request.getParameter("star");
        String reviews = request.getParameter("reviews");
        String prevPriceStr = request.getParameter("prevPrice");
        String newPriceStr = request.getParameter("newPrice");
        String company = request.getParameter("company");
        String consoleType = request.getParameter("consoleType");
        String category = request.getParameter("category");

        // Ajustar os formatos do Objeto
        final Integer star = Integer.valueOf(starStr);
        final Double prevPrice = Double.valueOf(prevPriceStr);
        final Double newPrice = Double.valueOf(newPriceStr);

        final Game game = new Game(img, title, star, reviews, prevPrice, newPrice, company, consoleType, category);

        // Envia a requisição POST para o servidor externo
        String apiUrl = "http://localhost:9090/games/saveOne";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configuração da conexão
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Construção do corpo da requisição
        String jsonInputString = "{"
                + "\"img\":\"" + img + "\","
                + "\"title\":\"" + title + "\","
                + "\"star\":" + star + ","
                + "\"reviews\":\"" + reviews + "\","
                + "\"prevPrice\":" + prevPrice + ","
                + "\"newPrice\":" + newPrice + ","
                + "\"company\":\"" + company + "\","
                + "\"consoleType\":\"" + consoleType + "\","
                + "\"category\":\"" + category + "\""
                + "}";

        // Envio do corpo da requisição
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }

        // Leitura da resposta
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            StringBuilder responseStringBuilder = new StringBuilder();
            String responseLine = null;

            while ((responseLine = br.readLine()) != null) {
                responseStringBuilder.append(responseLine.trim());
            }
            System.out.println("POST One Game Resposta: " + responseStringBuilder.toString());
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body><p> Game adicionado com sucesso! </p><p> Dados do Game: " + game.toString() + " </p></body></html>");

        // Fecha a conexão
        connection.disconnect();
    }
}
