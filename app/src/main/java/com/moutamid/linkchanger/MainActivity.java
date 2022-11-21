package com.moutamid.linkchanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText link;
    Button btn;
    String s;
    Map<String, Object> updateLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        link = findViewById(R.id.et_link);
        btn = findViewById(R.id.updateBtn);

        updateLink = new HashMap<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("VideoPlayer");
        db.keepSynced(true);

        btn.setOnClickListener(v -> {
            s = link.getText().toString();
            updateLink.put("link", s);

            db.child("link").updateChildren(updateLink).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        link.setText("");
                        Toast.makeText(MainActivity.this, "Link is Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}