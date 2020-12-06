package com.example.and_handin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.and_handin.ui.database.AppDatabase;
import com.example.and_handin.ui.database.Note;
import com.example.and_handin.ui.database.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends  AppCompatActivity {
    //FloatingActionButton fab;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private NoteAdapter noteAdapter;
    AppDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=AppDatabase.getDbInstance(this);
        //database.noteDao().nukeTable();
        Note test1=new Note();
        Note test2=new Note();
        test1.title="test1";
        test1.content="This is a test";
        test2.title="test2";
        test2.content="This is a test part 2";

//        database.noteDao().insertNote(test1);
//        database.noteDao().insertNote(test2);
        initRecyclerView();
        loadNotes();
        // initialize database

        //noteData.setAll((ArrayList<NoteData>)database.noteDao().getAll());


       // noteAdapter=new NoteAdapter(MainActivity.this, noteData);
       // recyclerView.setAdapter(noteAdapter);


    }

    private void loadNotes() {

        List<Note> noteData=database.noteDao().getAllNotes();
        noteAdapter.setNoteList(noteData);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_View);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        noteAdapter=new NoteAdapter(this);
        recyclerView.setAdapter(noteAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_weather:
                Intent intent=new Intent(this,WeatherActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }

    public void addNoteButton(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true).add(R.id.fragment_container_view, AddNoteFragment.class, null).commit();


    }

    private void cancelBtnAction(View v) {

    }

    private void saveNewNote(View v) {
        Note temp = new Note();
        temp.title = v.findViewById(R.id.addTitle).toString();
        temp.content = v.findViewById(R.id.addContent).toString();

        Log.d("SaveNote", "" + temp.title);

        database.noteDao().insertNote(temp);
//        Log.d("insert", "Note inserted in db ");
//        Toast.makeText(context, "Added data", Toast.LENGTH_SHORT).show();
//        FragmentManager manager = ( AddNoteFragment.class).getFragmentManager();
//        FragmentTransaction trans = manager.beginTransaction();
//        FragmentTransaction fragmentTransaction=
//        getSupportFragmentManager().beginTransaction().fragmentTransaction.remove(yourfragment).commit()
    }
//    @Override
//    public Note fragmentContactActivity(String title, String content) {
//        Log.d("MainActivity", "adding data");
//        Note temp = new Note();
//        temp.title=title;
//        temp.content=content;
//        database.noteDao().insertNote(temp);
//        Log.d("MainActivity", "note ADDED_________"+temp.title);
//        return 1;
//    }

}