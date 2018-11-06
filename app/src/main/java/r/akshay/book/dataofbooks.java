package r.akshay.book;

public class dataofbooks {
    private String id;
    private String title;
    private String shortdesc;
    private double rating;
    private int image;
    public dataofbooks(String id, String title, String shortdesc, double rating, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.image = image;
    }
//    public dataofbooks(int id, String title, String shortdesc, double rating) {
//        this.id = id;
//        this.title = title;
//        this.shortdesc = shortdesc;
//        this.rating = rating;
//    }

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
    public int getImage() {
        return image;
    }
}

