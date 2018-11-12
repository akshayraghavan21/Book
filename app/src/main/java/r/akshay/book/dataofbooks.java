package r.akshay.book;

public class dataofbooks {
    private String id;
    private String title;
    private String shortdesc;
    private double rating;
    private String image;
    public dataofbooks(){}
    public dataofbooks(String id, String title, String shortdesc, double rating, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }
    public String getImage() {
        return image;
    }
}

