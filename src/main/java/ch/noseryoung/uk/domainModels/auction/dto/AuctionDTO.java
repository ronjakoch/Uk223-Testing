package ch.noseryoung.uk.domainModels.auction.dto;

// This an example class on how a DTO could be built
// A DTO is pretty much just a regular POJO, there is no need for any fancy annotations
public class AuctionDTO {


    // Representative attributes, make sure they are called the same way as the attribute that they represent
    private int id;

    private String auctionArticle;

    private String startOfAuction;

    private double price;


    // Standard empty constructor
    public AuctionDTO() {

    }

    // Standard getters and setters


    public String getStartOfAuction() {
        return startOfAuction;
    }

    public void setStartOfAuction(String startOfAuction) {
        this.startOfAuction = startOfAuction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuctionArticle() {
        return auctionArticle;
    }

    public void setAuctionArticle(String auctionArticle) {
        this.auctionArticle = auctionArticle;
    }
}