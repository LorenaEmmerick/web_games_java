package br.com.game.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Game {
    private Integer id;
    private String img;
    private String title;
    private Integer star;
    private String reviews;
    private Double prevPrice;
    private Double newPrice;
    private String company;
    private String consoleType;
    private String category;

    public Game(String img, String title, Integer star,
                String reviews, Double prevPrice,
                Double newPrice, String company,
                String consoleType, String category) {
        this.id = null;
        this.img = img;
        this.title = title;
        this.star = star;
        this.reviews = reviews;
        this.prevPrice = prevPrice;
        this.newPrice = newPrice;
        this.company = company;
        this.consoleType = consoleType;
        this.category = category;
    }

    public String toString() {
        return "Game{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", star=" + star +
                ", reviews='" + reviews + '\'' +
                ", prevPrice=" + prevPrice +
                ", newPrice=" + newPrice +
                ", company='" + company + '\'' +
                ", consoleType='" + consoleType + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}
