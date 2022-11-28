package com.moutamid.linkchanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText link;
    Button btn, rotate;
    String s, sl;
    boolean rr;
    Map<String, Object> updateLink;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        link = findViewById(R.id.et_link);
        btn = findViewById(R.id.updateBtn);
        rotate = findViewById(R.id.rotate);

        updateLink = new HashMap<>();

        db = FirebaseDatabase.getInstance().getReference().child("VideoPlayer");
        db.keepSynced(true);

        // getdata();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model m = snapshot.getValue(Model.class);
                rr = m.isRotate();
                sl = m.getLink();

                updateLink.put("link", sl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(v -> {
            s = link.getText().toString();
            updateLink.put("link", s);

            db.updateChildren(updateLink).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        link.setText("");
                        Toast.makeText(MainActivity.this, "Link is Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        rotate.setOnClickListener(v -> {
            // getdata();

            if (rr){
                updateLink.put("rotate", false);
            } else {
                updateLink.put("rotate", true);
            }

            db.updateChildren(updateLink).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Screen Rotated Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    private void getdata() {

    }
}