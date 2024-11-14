package com.example.mynotes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.List;

public class FragmentShow extends Fragment {
    private MyDatabaseHelper dbHelper;
    private ListView listView;
    private NoteAdapter noteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("FragmentShow", "onCreateView called");
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        listView = view.findViewById(R.id.listView);
        dbHelper = new MyDatabaseHelper(getContext());
        List<Note> noteList = dbHelper.getAllNotes();
        noteAdapter = new NoteAdapter(getContext(), noteList);
        listView.setAdapter(noteAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FragmentShow", "onViewCreated called");
    }

    @Override
    public void onResume() {
        super.onResume();
        updateNotes();
        Log.d("FragmentShow", "onResume called");
    }

    public void updateNotes() {
        List<Note> noteList = dbHelper.getAllNotes();
        noteAdapter.clear();
        noteAdapter.addAll(noteList);
        noteAdapter.notifyDataSetChanged();
    }
}