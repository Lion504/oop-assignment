package model;

import java.util.ArrayList;
import java.util.List;

/**
 * NoteBook class - This manages a collection of Note objects
 * Think of it like a notebook that can hold many individual notes
 *
 * This is part of the MODEL in MVC pattern - it manages our data collection
 */
public class NoteBook {

    // Private field to store all our notes
    private List<Note> notes = new ArrayList<>();

    /**
     * Add a new note to our notebook
     * This method takes a Note object and adds it to our list
     *
     * @param note - the Note object to add
     */
    public void addNote(Note note) {
        // Add the note to the end of our list
        notes.add(note);
    }

    /**
     * Return a new ArrayList with copies of our notes
     * This prevents other classes from accidentally changing our data
     *
     * @return a new ArrayList containing all our notes
     */
    public List<Note> getAllNotes() {
        return new ArrayList<>(notes);
    }

    /**
     * This finds the note in our list and removes it
     *
     * @param note - the Note object to remove
     * @return true if the note was found and removed, false otherwise
     */
    public boolean removeNote(Note note) {
        return notes.remove(note);
    }

    /**
     * Update/replace a note at a specific position
     *
     * @param index - the position in the list (starts from 0)
     * @param updatedNote - the new Note object to put in that position
     * @return true if update was successful, false if index is invalid
     */
    public boolean updateNote(int index, Note updatedNote) {
        if (index >= 0 && index < notes.size()) {
            notes.set(index, updatedNote);
            return true;
        }
        return false; // Index was invalid
    }

    /**
     * Get a specific note by its position in the list
     *
     * @param index - the position in the list (starts from 0)
     * @return the Note at that position, or null if index is invalid
     */
    public Note getNote(int index) {
        // Check if the index is valid
        if (index >= 0 && index < notes.size()) {
            return notes.get(index);
        }
        return null; // Index was invalid
    }

    /**
     * Get the total number of notes in our notebook
     *
     * @return the number of notes as an integer
     */
    public int getSize() {
        return notes.size();
    }

    /**
     * Check if the notebook is empty
     *
     * @return true if there are no notes, false if there are notes
     */
    public boolean isEmpty() {
        return notes.isEmpty();
    }

    /**
     * Remove all notes from the notebook
     * This clears the entire notebook
     */
    public void clearAllNotes() {
        notes.clear();
    }

    /**
     * Find the index/position of a specific note in our list
     *
     * @param note - the Note object to find
     * @return the index/position of the note, or -1 if not found
     */
    public int findNoteIndex(Note note) {
        return notes.indexOf(note);
    }
}
