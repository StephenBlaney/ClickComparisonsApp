package com.example.craig.firebaseread.Pages;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.craig.firebaseread.Items.Aldi;
import com.example.craig.firebaseread.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/*
This class is used for the home page of the application
It will display the list of Aldi items on the home page.
By accessing the firebase table for aldi. It acts as the launcher of
the application and allows navigation around the application.
 */
public class HomeActivity extends AppCompatActivity {
    /*
    Declares variables globally to be accessed throughout the application
     */
    private ListView listView;
    private ArrayList<Aldi> listAldi;
    private FirebaseDatabase database;
    private DatabaseReference refAldi;
    private ArrayAdapter<Aldi> adapter;

    /*
    Acts as the main method of the application calls all
    the methods for editing and adding
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.listView);
        DatabasePersistentSetUp.getDatabase(); // needed for the offline use of the application
        database = FirebaseDatabase.getInstance();
        refAldi = database.getReference("Aldi");
        getAldi();
    }

    /*
    This method is used for to get the contents of the firebase table.
    It sets the adapter and adds the contents from the list array
    to the list view.
     */
    public void getAldi(){
        listAldi = new ArrayList<>();
        adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, listAldi);
        listView.setAdapter(adapter);
        Query query = refAldi;
        query.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Aldi item = ds.getValue(Aldi.class);
                    listAldi.add(item);
                }
            }

            /*
           Used to handle if the connection to the database can not be made.
           It displays an error message to inform the user.
            */
            public void onCancelled(DatabaseError databaseError) {
                Context context = getApplicationContext();
                CharSequence text = "Error occured with DATABASE CONNECTION TRY AGAIN!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }//end get Aldi

    /*
    Handles the button click for search option
     */
    public void search(View view){
        Intent in = new Intent(this,SearchActivity.class);
        startActivity(in);
        finish();
    }
    /*
    Handles the button click for login option
     */
    public void settings(View view){
        Intent in = new Intent(this,LoginActivity.class);
        startActivity(in);
        finish();
    }
}
