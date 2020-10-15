package com.sudha.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class NoteHandler extends DatabaseHelper {

    public NoteHandler(Context context) {
        super(context);
    }

    //CRUD

    public boolean create(Note note){
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle());
        values.put("description",note.getDescription());
        SQLiteDatabase db=this.getReadableDatabase();
        boolean isSuccessfull = db.insert("Note",null,values)>0;
        db.close();
        return isSuccessfull;

    }
}
