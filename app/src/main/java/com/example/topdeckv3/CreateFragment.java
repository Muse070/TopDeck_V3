package com.example.topdeckv3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class CreateFragment extends Fragment {

   EditText name_input, email_input, address_input, number_input;
   Button create_button;

   private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_fragement, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name_input = view.findViewById(R.id.fullName);
        email_input = view.findViewById(R.id.emailAddress);
        address_input = view.findViewById(R.id.physicalAddress);
        number_input = view.findViewById(R.id.phoneNumber);
        create_button = view.findViewById(R.id.create);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            MyDatabaseHelper myDB = new MyDatabaseHelper(requireContext());

            myDB.addCardDetails(name_input.getText().toString().trim(),
                    email_input.getText().toString().trim(),
                    address_input.getText().toString().trim(),
                    number_input.getText().toString().trim());

            saveChanges();
            Toast.makeText(context, "Card details saved", Toast.LENGTH_SHORT).show();

        }});
    }

    private void saveChanges() {
        SharedPreferences sharedPrefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("name", name_input.getText().toString());
        editor.putString("email", email_input.getText().toString());
        editor.putString("address", address_input.getText().toString());
        editor.putString("number", number_input.getText().toString());
        editor.apply();
    }
}