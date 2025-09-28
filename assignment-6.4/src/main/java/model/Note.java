package model;

/**
 * Note class - This represents a single note in our application
 * Think of it like a sticky note that has a title and some content
 *
 * This is part of the MODEL in MVC pattern - it holds our data
 */
public class Note {

    // Private fields - these store the data for each note
    // 'private' means only this class can directly access these variables
    private String title;   // The title/heading of the note
    private String content; // The main text content of the note

    /**
     * Constructor - This is a special method that creates a new Note object
     * It runs every time we create a new note with: new Note("title", "content")
     *
     * @param title - the title for this note
     * @param content - the content for this note
     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // GETTER METHODS - These allow other classes to READ our private data
    // We use getters instead of making fields public for security and control

    /**
     * Get the title of this note
     * @return the title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the content of this note
     * @return the content as a String
     */
    public String getContent() {
        return content;
    }

    // SETTER METHODS - These allow other classes to change our private data
    // We use setters so we can add validation or other logic if needed

    /**
     * Set/change the title of this note
     * @param title - the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set/change the content of this note
     * @param content - the new content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * toString method - This tells Java how to display this object as text
     * When we add notes to a ListView, this method determines what text is shown
     *
     * @return a string representation of this note
     */
    @Override
    public String toString() {
        // We return just the title so the ListView shows note titles
        // If title is empty, we show a placeholder
        return title.isEmpty() ? "(Untitled Note)" : title;
    }

    /**
     * Method to get a formatted display of the full note
     * This is useful when we want to show both title and content
     *
     * @return formatted string with title and content
     */
    public String getFullDisplay() {
        return "Title: " + title + "\n" +
               "Content: " + content + "\n" +
               "------------------------";
    }
}
