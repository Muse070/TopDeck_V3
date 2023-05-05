package com.example.topdeckv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

// Create database
public class MyDatabaseHelper extends SQLiteOpenHelper {

    // Initialize database variables
    private Context context;
    public static final String DATABASE_NAME = "CardCollection.db";
    public static final int DATABASE_VERSION = 1;

    // Create database constructor
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Create and initialize variables to be stored in the database
    private static final class CardTable {
        public static final String TABLE_NAME = "my_cards";
        public static final String Column_ID = "_id";
        public static final String Column_FullName = "full_name";
        public static final String Column_EmailAddress = "email_address";
        public static final String Column_PhysicalAddress = "physical_address";
        public static final String Column_PhoneNumber = "phone_number";
    }

    // Create Card table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + CardTable.TABLE_NAME +
                " (" + CardTable.Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CardTable.Column_FullName + " TEXT, " +
                CardTable.Column_EmailAddress + " TEXT, " +
                CardTable.Column_PhysicalAddress + " TEXT, " +
                CardTable.Column_PhoneNumber + " TEXT);";
        db.execSQL(query);
    }

    // Modify the database if changes occur on the card table
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CardTable.TABLE_NAME);
        onCreate(db);
    }

    public void addCardDetails (String fullName, String email, String physicalAddress, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CardTable.Column_FullName, fullName);
        cv.put(CardTable.Column_EmailAddress, email);
        cv.put(CardTable.Column_PhysicalAddress, physicalAddress);
        cv.put(CardTable.Column_PhoneNumber, phoneNumber);
        long result = db.insert(CardTable.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + CardTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
