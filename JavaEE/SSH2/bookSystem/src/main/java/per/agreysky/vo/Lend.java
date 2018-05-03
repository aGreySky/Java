package per.agreysky.vo;

import java.util.Date;

/**
 * Lend entity. @author MyEclipse Persistence Tools
 */

public class Lend implements java.io.Serializable {

    // Fields

    private String bookId;
    private String readerId;
    private String ISBN;
    private Date ltime;//

    // Constructors

    /** default constructor */
    public Lend() {
    }

    /** full constructor */
    public Lend(String bookId, String readerId, String ISBN, Date ltime) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.ISBN = ISBN;
        this.ltime = ltime;
    }

    // Property accessors

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getReaderId() {
        return this.readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getLtime() {
        return this.ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

}