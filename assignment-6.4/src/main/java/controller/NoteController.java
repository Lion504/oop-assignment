package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Note;
import model.NoteBook;

/**
 * NoteController class - This is the CONTROLLER in MVC pattern
 * It handles all user interactions (button clicks, text input, etc.)
 * and connects the View (UI) with the Model (data)
 *
 * The @FXML annotations tell JavaFX to connect these variables
 * to the elements in our FXML file that have matching fx:id values
 */
public class NoteController {

    // UI ELEMENTS - These connect to the elements in our FXML file
    // JavaFX automatically fills these with the actual UI components

    @FXML private TextField titleField;        // Where user types the note title
    @FXML private TextArea contentArea;        // Where user types the note content
    @FXML private ListView<Note> notesListView; // Shows list of all saved notes
    @FXML private Button addButton;            // Button to add new notes
    @FXML private Button editButton;           // Button to edit existing notes
    @FXML private Button deleteButton;         // Button to delete notes
    @FXML private Button clearButton;          // Button to clear all notes
    @FXML private Label statusLabel;           // Shows status messages to user

    // MODEL - Our data storage it's a private List of Note objects
    private final NoteBook noteBook = new NoteBook();

    // Keep track of which note is currently being edited
    private Note currentlyEditingNote = null;

    /**
     * initialize() method - This runs automatically after the FXML is loaded
     * It's like a constructor but for JavaFX controllers
     * We use it to set up our UI and connect event handlers
     */
    @FXML
    public void initialize() {
        // Set up the ListView selection listener
        // This means "do something when user clicks on a note in the list"
        notesListView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldSelection, newSelection) -> {
                // This code runs whenever user selects a different note
                handleNoteSelection(newSelection);
            }
        );

        // Initially disable edit delete and clear buttons since no note is selected
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        clearButton.setDisable(true);

        // Set initial status message
        updateStatus("Ready to create your first note!");
    }

    /**
     * Handle when user selects a note from the list
     * This method runs when user clicks on a note in the ListView
     */
    private void handleNoteSelection(Note selectedNote) {
        if (selectedNote != null) {
            // User selected a note - fill the input fields with its data
            titleField.setText(selectedNote.getTitle());
            contentArea.setText(selectedNote.getContent());

            // Enable edit and delete and clear buttons since a note is selected
            editButton.setDisable(false);
            deleteButton.setDisable(false);
            clearButton.setDisable(false);

            // Remember which note we're looking at
            currentlyEditingNote = selectedNote;

            updateStatus("Selected note: " + selectedNote.getTitle());
        } else {
            // No note selected - disable edit delete and clear buttons
            editButton.setDisable(true);
            deleteButton.setDisable(true);
            clearButton.setDisable(true);
            currentlyEditingNote = null;
            updateStatus("No note selected");
        }
    }

    /**
     * Handle Add Note button click
     * This method runs when user clicks the "Add Note" button
     */
    @FXML
    private void handleAddNote() {
        // Get the text from input fields and remove extra spaces
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        // Check if user actually typed something
        if (title.isEmpty()) {
            updateStatus("Please enter a title for your note!");
            return; // Stop here - don't create empty note
        }

        // Create a new Note object with the entered data
        Note newNote = new Note(title, content);

        // Add the note to our notebook
        noteBook.addNote(newNote);

        // Update the ListView to show the new note
        refreshNotesList();

        // Clear the input fields so user can create another note
        clearInputFields();

        // Show success message
        updateStatus("Note added successfully!");
    }

    /**
     * Handle Edit Note button click
     * This method runs when user clicks the "Edit Note" button
     */
    @FXML
    private void handleEditNote() {
        // Check if a note is actually selected
        if (currentlyEditingNote == null) {
            updateStatus("Please select a note to edit!");
            return;
        }

        // Get the new text from input fields
        String newTitle = titleField.getText().trim();
        String newContent = contentArea.getText().trim();

        // Check if user entered a title
        if (newTitle.isEmpty()) {
            updateStatus("Please enter a title for your note!");
            return;
        }

        // Find where this note is in our list
        int noteIndex = noteBook.findNoteIndex(currentlyEditingNote);

        if (noteIndex >= 0) {
            // Create updated note with new data
            Note updatedNote = new Note(newTitle, newContent);

            // Replace the old note with the updated one
            noteBook.updateNote(noteIndex, updatedNote);

            // Update the ListView to show changes
            refreshNotesList();

            // Clear input fields
            clearInputFields();

            // Clear selection
            notesListView.getSelectionModel().clearSelection();
            currentlyEditingNote = null;

            updateStatus("Note updated successfully!");
        }
    }

    /**
     * Handle Delete Note button click
     * This method runs when user clicks the "Delete Note" button
     */
    @FXML
    private void handleDeleteNote() {
        // Check if a note is selected
        if (currentlyEditingNote == null) {
            updateStatus("Please select a note to delete!");
            return;
        }

        // Ask user to confirm deletion (optional - makes app more user-friendly)
        String noteTitle = currentlyEditingNote.getTitle();
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Delete Note");
        confirmDialog.setHeaderText("Are you sure?");
        confirmDialog.setContentText("Do you want to delete the note: " + noteTitle + "?");

        // Show the dialog and wait for user response
        if (confirmDialog.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            // User confirmed - delete the note
            noteBook.removeNote(currentlyEditingNote);

            // Update the ListView
            refreshNotesList();

            // Clear input fields and selection
            clearInputFields();
            notesListView.getSelectionModel().clearSelection();
            currentlyEditingNote = null;

            updateStatus("Note deleted successfully!");
        }
    }

    /**
     * Handle Clear All button click
     * This method runs when user clicks the "Clear All" button
     */
    @FXML
    private void handleClearAll() {
        // Check if there are any notes to clear
        if (noteBook.isEmpty()) {
            updateStatus("No notes to clear!");
            return;
        }

        // Ask user to confirm clearing all notes
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Clear All Notes");
        confirmDialog.setHeaderText("Are you sure?");
        confirmDialog.setContentText("This will delete all " + noteBook.getSize() + " notes. This cannot be undone!");

        if (confirmDialog.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            // User confirmed - clear all notes
            noteBook.clearAllNotes();

            // Update UI
            refreshNotesList();
            clearInputFields();
            notesListView.getSelectionModel().clearSelection();
            currentlyEditingNote = null;

            updateStatus("All notes cleared!");
        }
    }

    /**
     * Refresh the ListView to show current notes
     * Call this method whenever we add, edit, or delete notes
     */
    private void refreshNotesList() {
        // Clear the current list display
        notesListView.getItems().clear();

        // Add all notes from our notebook to the display
        notesListView.getItems().addAll(noteBook.getAllNotes());
    }

    /**
     * Clear all input fields
     * This makes the form ready for new input
     */
    private void clearInputFields() {
        titleField.clear();
        contentArea.clear();
        titleField.requestFocus();
    }

    /**
     * Update the status message shown to user
     * This gives feedback about what's happening
     */
    private void updateStatus(String message) {
        statusLabel.setText(message);
    }
}
