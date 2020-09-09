package ch.noseryoung.uk.domainModels.auction;

import javax.persistence.*;

// This is an example class on a domain model could be built
// These annotations show hibernate that this is an entity
@Entity
// This annotation defines this entity as it's own table with it's given name
// We can also not define it's name, but then it'd take the name of the class itself and that wouldn't be best practice
@Table(name = "auction")
public class Auction {

    // Regular attributes
    // The primary key, this annotation defines that this is a primary key:
    @Id
    // This annotation makes sure that our id gets auto-incremented
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // This annotation simply defines that this attribute has it's own column and how that column is called
    @Column(name = "id")
    private int id;

    // In this example the name has not been defined, if you do this hibernate will plainly use the name of the variable itself
    // The nullable parameter defines if this attribute can be null in the database
    @Column(nullable = false)
    private String auctionArticle;

    // In this example the name has not been defined, if you do this hibernate will plainly use the name of the variable itself
    // The nullable parameter defines if this attribute can be null in the database
    @Column(nullable = false)
    private String startOfAuction;

    @Column(nullable = false)
    private double price;

    // Standard empty constructor
    public Auction() {}

    public Auction(int id, double price) {
        this.id = id;
        this.price = price;
    }

    //Getters & Setters

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Auction setId(int id) {
        this.id = id;
        return this;
    }

    public String getAuctionArticle() {
        return auctionArticle;
    }

    public Auction setAuctionArticle(String auctionArticle) {
        this.auctionArticle = auctionArticle;
        return this;
    }

    public String getStartOfAuction() {
        return startOfAuction;
    }

    public Auction setStartOfAuction(String startOfAuction) {
        this.startOfAuction = startOfAuction;
        return this;
    }
}
