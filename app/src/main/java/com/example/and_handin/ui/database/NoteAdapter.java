package com.example.and_handin.ui.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_handin.R;

import java.util.List;

public  class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private Context context;
    private List<Note> noteList;
    public NoteAdapter(Context context) {
        this.context=context;
    }
    public void setNoteList(List<Note> noteList) {
        for (int i = 0; i <noteList.size() ; i++) {
            if(noteList.get(i).title==null &&noteList.get(i).content==null)
            {
                noteList.remove(i);
            }
        }
        this.noteList = noteList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
        holder.title.setText(this.noteList.get(position).title);
        holder.content.setText(this.noteList.get(position).content);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvFirstName);
            content = view.findViewById(R.id.tvLastName);

        }
    }
    }

