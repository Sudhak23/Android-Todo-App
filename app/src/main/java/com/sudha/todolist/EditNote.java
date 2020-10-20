package com.sudha.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {

    EditText edtTitle,edtDescription;
    Button btnsave,btncancel;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        linearLayout=findViewById(R.id.btn_holder);

        edtDescription=findViewById(R.id.edt_edit_discription);
        edtTitle=findViewById(R.id.edt_edit_title);

        btncancel=findViewById(R.id.btn_cancel);
        btnsave=findViewById(R.id.btn_save);
        final Intent intent=getIntent();

        //When we click cancel it comes out

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //animation after onback pressed
                onBackPressed();

            }
        });

        //save the edited code

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note=new Note(edtTitle.getText().toString(),edtDescription.getText().toString());
                note.setId(intent.getIntExtra("id",1));
                if(new NoteHandler(EditNote.this).update(note)){
                    Toast.makeText(EditNote.this,"Note updated",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditNote.this,"Failed to updating",Toast.LENGTH_SHORT).show();
                }


                //before on back pressed
                onBackPressed();
            }
        });



        // setting title and description to edit note inside

        edtDescription.setText(intent.getStringExtra("description"));
        edtTitle.setText((intent.getStringExtra("title")));

    }

    //Animation effect

    @Override
    public void onBackPressed() {
        btncancel.setVisibility(View.GONE);
        btnsave.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(linearLayout);
        super.onBackPressed();
    }
}