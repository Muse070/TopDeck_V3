package com.example.topdeckv3;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    RecyclerView recyclerView;

    MyDatabaseHelper myDb;
    ArrayList<String> card_id, full_name, email_address, physical_address, phone_number;
    CustomAdapter customAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        recyclerView = recyclerView.findViewById(R.id.recyclerView);

        myDb = new MyDatabaseHelper(requireContext());
        card_id = new ArrayList<>();
        full_name = new ArrayList<>();
        email_address = new ArrayList<>();
        physical_address = new ArrayList<>();
        phone_number = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        storeDataInArrays();
        customAdapter = new CustomAdapter(requireContext(), card_id, full_name, email_address, physical_address, phone_number);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    void storeDataInArrays() {
        Cursor cursor = myDb.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                card_id.add(cursor.getString(0));
                full_name.add(cursor.getString(1));
                email_address.add(cursor.getString(2));
                physical_address.add(cursor.getString(3));
                phone_number.add(cursor.getString(4));
            }
        }
    }
}