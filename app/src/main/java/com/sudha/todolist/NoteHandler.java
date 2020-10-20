package com.sudha.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
    //method to return notes
    public ArrayList<Note> readNotes(){
        ArrayList<Note> notes = new ArrayList<>();

        String sqlQuery = "SELECT * FROM Note ORDER BY ID ASC";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);

        if(cursor.moveToFirst()){
            do{
                int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                Note note = new Note(title,description);
                note.setId(id);
                notes.add(note);

            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return notes;
    }

    //fetch one single note this used when we want to edit specific note

    public  Note readSingleNote(int id){
        Note note= null ;
        String sqlQuery = "SELECT * FROM Note WHERE id="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sqlQuery,null);

        if(cursor.moveToFirst()){

            int noteid=Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            note=new Note(title,description);
            note.setId(noteid);
        }
        cursor.close();
        db.close();
        return note;
    }

    //updating notes

    public  boolean update(Note note){

        ContentValues values=new ContentValues();

        values.put("title",note.getTitle());
        values.put("description",note.getDescription());
        SQLiteDatabase db=this.getWritableDatabase();
        boolean isSuccessfull=db.update("Note",values,"id='"+note.getId()+"'",null)>0;
        db.close();
        return isSuccessfull;
    }
    public boolean delete(int id){
        boolean isDeleted;
        SQLiteDatabase db=this.getWritableDatabase();
        isDeleted=db.delete("Note","id='"+id+"'",null)>0;
        db.close();
        return isDeleted;
    }

}
