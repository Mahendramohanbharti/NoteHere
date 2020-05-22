package com.example.notehere;

 import android.content.Intent;
 import android.os.Bundle;
 import android.text.method.ScrollingMovementMethod;
 import android.view.Menu;
 import android.view.MenuInflater;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.TextView;
 import android.widget.Toast;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;
 import com.google.android.material.floatingactionbutton.FloatingActionButton;
 import com.google.android.material.snackbar.Snackbar;
 public class Details extends AppCompatActivity {
     long id;
     TextView mDetails;
     NoteDatabase db;
     Note note;
     @Override protected void onCreate(
             Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_details);
         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         mDetails=findViewById(R.id.noteDetails);
         Intent i = getIntent();
         id = i.getLongExtra("ID",0);
         db = new NoteDatabase(this);
         note = db.getNote(id);
         getSupportActionBar().setTitle(note.getTitle());
         mDetails.setText(note.getContent());
         mDetails.setMovementMethod(new ScrollingMovementMethod());
         Toast.makeText(this, "Title -> ", Toast.LENGTH_SHORT).show();
         FloatingActionButton fab = findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
             @Override public void onClick(View view) {
                 db.deleteNote(note.getID());
                 Toast.makeText(getApplicationContext(), "Note is deleted", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(getApplicationContext(),MainActivity.class));
             }
         });
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     }
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.edit_menu,menu);
         return true;
     } @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         if(item.getItemId() == R.id.editNote){
             Toast.makeText(this,"Edit Note",Toast.LENGTH_SHORT).show();
             Intent i = new Intent(this,Edit.class);
             i.putExtra("ID",note.getID()); startActivity(i);
         }
         return super.onOptionsItemSelected(item);
     } @Override public void onBackPressed()
     { super.onBackPressed();
     } private void goToMain() {
         Intent i = new Intent(this,MainActivity.class);
         startActivity(i);
     }
 }
