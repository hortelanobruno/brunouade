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
    private Bruno bruno;

    // ------------------------------------------------------------ Constructors

    /**
     * Constructor for BookBean.
     * @param title the book title
     * @param isbn the ISBN
     */
    public BookBean(String isbn, String title, Bruno b) {
        this.isbn = isbn;
        this.title = title;
        this.bruno = b;
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

	public void setBruno(Bruno bruno) {
		this.bruno = bruno;
	}

	public Bruno getBruno() {
		return bruno;
	}


}

