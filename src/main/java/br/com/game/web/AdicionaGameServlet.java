package br.com.game.web;

import br.com.game.web.entity.Game;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("adicionaGame")
public class AdicionaGameServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String img = request.getParameter("img");
        String title = request.getParameter("title");
        String starStr = request.getParameter("star");
        String reviews = request.getParameter("reviews");
        String prevPriceStr = request.getParameter("prevPrice");
        String newPriceStr =request.getParameter("newPrice");
        String company = request.getParameter("company");
        String consoleType = request.getParameter("consoleType");
        String category = request.getParameter("category");

        final Integer star = Integer.valueOf(starStr);
        final Double prevPrice = Double.valueOf(prevPriceStr);
        final Double newPrice = Double.valueOf(newPriceStr);

        final Game game = new Game(img, title, star, reviews, prevPrice, newPrice, company, consoleType, category);

        PrintWriter out = response.getWriter();
        out.println("<html><body><p> Game adicionado com sucesso! </p><p> Dados do Game: "+ game.toString() + " </p></body></html>" );

        System.out.println(game.toString());

        //TODO: fazer a connection com o games_java API
    }
}
