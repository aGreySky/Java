package per.agreysky.org.vo;
// Generated 2017-9-25 21:10:18 by Hibernate Tools 3.5.0.Final

/**
 * Book generated by hbm2java
 */
public class Book implements java.io.Serializable {

    private int bookId;
    private String ISBN;
    private String bookName;
    private String author;
    private String publisher;
    private float price;
    private int cnum;
    private int snum;
    private String summary;
    private byte[] photo;
    public Book() {
    }

    public Book(int bookId, String ISBN, String bookName, String author,
            String publisher, float price, int cnum, int snum) {
        this.setISBN(ISBN);
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.cnum = cnum;
        this.snum = snum;
    }
    public Book(int bookId, String ISBN, String bookName, String author,
            String publisher, float price, int cnum, int snum, String summary,
            byte[] photo) {
        this.setISBN(ISBN);
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.cnum = cnum;
        this.snum = snum;
        this.summary = summary;
        this.photo = photo;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public int getCnum() {
        return this.cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }
    public int getSnum() {
        return this.snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }
    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}