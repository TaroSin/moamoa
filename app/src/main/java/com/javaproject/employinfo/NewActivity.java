package com.javaproject.employinfo;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Example of using Folding Cell with ListView and ListAdapter
 */
public class NewActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // get our list view
        ListView theListView = findViewById(R.id.mainListView);

        Intent intent = getIntent();
        String field = intent.getStringExtra("filed");
        String emp = intent.getStringExtra("emp");
        String loc = intent.getStringExtra("loc");
        String collectionName = field;
        if(emp!=null){
            collectionName += emp;
        }
        if(loc!=null){
            collectionName += loc;
        }
        System.out.println("colname : " + collectionName);

        ArrayList<Item> items = new ArrayList<>();

        db.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = String.valueOf(document.get("name"));
                                String title = String.valueOf(document.get("title"));
                                String loclong = String.valueOf(document.get("loclong"));
                                String locshort = String.valueOf(document.get("locshort"));
                                String exp = String.valueOf(document.get("exp"));
                                String work = String.valueOf(document.get("work"));
                                String date = String.valueOf(document.get("date"));
                                String edu = String.valueOf(document.get("edu"));
                                String link = String.valueOf(document.get("link"));
                                String etc = String.valueOf(document.get("etc"));
                                Item item = new Item(name, title, loclong, locshort, exp, work, date, edu, link, etc);
                                items.add(item);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }

                            // add custom btn handler to first list item
//                            items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
//                                }
//                            });

                            // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
                            FoldingCellListAdapter adapter = new FoldingCellListAdapter(getApplicationContext(), items);

                            // add default btn handler for each request btn on each item if custom handler not found
                            adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent linkIntent = new Intent(Intent.ACTION_VIEW);
                                    TextView linkView = v.findViewById(R.id.link);
                                    linkIntent.setData(Uri.parse(linkView.getText().toString()));
                                    startActivity(linkIntent);
                                }
                            });

                            // set elements to adapter
                            theListView.setAdapter(adapter);

                            // set on click event listener to list view
                            theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                    // toggle clicked cell state
                                    ((FoldingCell) view).toggle(false);
                                    // register in adapter that state for selected cell is toggled
                                    adapter.registerToggle(pos);
                                }
                            });

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        // prepare elements to display





    }
}
