package com.example.and_handin.ui.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAllNotes();

    @Insert
    void insertNote(Note... note);

    @Delete
    void delete(Note note);
    @Query("DELETE FROM note")
    void nukeTable();
}
