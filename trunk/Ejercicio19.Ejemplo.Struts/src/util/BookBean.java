package util;

import java.io.Serializable;

public class BookBean implements Serializable{

    // ------------------------------------------------------ Instance Variables
  
	/**
     * International Standard Book Number (ISBN)
     */
    private String isbn;
    
    /**
     * Book Title
     */
    private String title;

    // ------------------------------------------------------------ Constructors

    /**
     * Constructor for BookBean.
     * @param title the book title
     * @param isbn the ISBN
     */
    public BookBean(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    // ------------------------------------------------------ Property Accessors

    /**
     * Returns the ISBN.
     * @return the ISBN
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Returns the book title
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }


}

