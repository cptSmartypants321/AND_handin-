package com.example.and_handin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.and_handin.ui.database.AppDatabase;
import com.example.and_handin.ui.database.Note;
import com.example.and_handin.ui.database.UserDatabase;

//import com.example.and_handin.ui.note.Note1;


public class AddNoteFragment extends Fragment {//implements View.OnClickListener {



    private EditText title;
    private EditText content;
    private Button saveBtn;
    private Button cancelBtn;
    private static Context context;
    int c;


    public AddNoteFragment() {
        super(R.layout.fragment_blank);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        Log.d("test", "Fragment created");
        if (context != null) {
            Log.d("test1", "context is not null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        title = v.findViewById(R.id.addTitle);
        content = v.findViewById(R.id.addContent);
        saveBtn = v.findViewById(R.id.btnSave);
       // saveBtn.setOnClickListener(this);
        cancelBtn = v.findViewById(R.id.btnCancel);
       // cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick", "in the onClick");
                Toast.makeText(context, ""+title.getText().toString(), Toast.LENGTH_SHORT).show();
                saveNewNote(title.getText().toString().trim(),content.getText().toString().trim());
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBtnAction(v);
            }
        });


        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        saveBtn = getActivity().findViewById(R.id.button_save);
//        saveBtn.setOnClickListener(this);
//        cancelBtn = getActivity().findViewById(R.id.button_cancel);
//        cancelBtn.setOnClickListener(this);
//    }

    private void cancelBtnAction(View v) {
        getChildFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }
//
//    private void saveNewNote(View v) {
//        Note temp = new Note();
//        temp.title = title.getText().toString();
//        temp.content = content.getText().toString();
//        if(noteViewModel!=null)
//        {
//            noteViewModel.setNote(temp);
//        }
//        Log.d("SaveNote", "" + temp.title);
////        AppDatabase database = AppDatabase.getDbInstance(context);
////        database.noteDao().insertNote(temp);
////        Log.d("insert", "Note inserted in db ");
////        Toast.makeText(context, "Added data", Toast.LENGTH_SHORT).show();
//        getChildFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
//    }
    private void saveNewNote(String title, String content) {
        Note temp = new Note();
        temp.title = title;
        temp.content = content;
        Log.d("SaveNote", "" + temp.title);
        AppDatabase database = AppDatabase.getDbInstance(context);
        database.noteDao().insertNote(temp);
        Log.d("insert", "Note inserted in db ");
        Toast.makeText(context, "Added data", Toast.LENGTH_SHORT).show();
        getChildFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

}