package bkp.com.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    private EditText nameEdt, phoneEdt, emailEdt;
    private Button submitBtn,retrieveBtn;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdt = findViewById(R.id.name_am);
        phoneEdt = findViewById(R.id.phone_am);
        emailEdt = findViewById(R.id.email_am);
        submitBtn = findViewById(R.id.submit_am);
        retrieveBtn = findViewById(R.id.retrieve_am);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RetrieveActivity.class);
                startActivity(intent);
            }
        });

    }

    private void createUser() {

        String name = nameEdt.getText().toString();
        String phone = phoneEdt.getText().toString();
        String email = emailEdt.getText().toString();

        if (name.isEmpty()){
            Toast.makeText(this, "fill name", Toast.LENGTH_SHORT).show();
        }else if(phone.isEmpty()){
            Toast.makeText(this, "fill phone", Toast.LENGTH_SHORT).show();
        }else if (email.isEmpty()){
            Toast.makeText(this, "fill email", Toast.LENGTH_SHORT).show();
        }else{
            uploadData(name,phone,email);
        }

    }

    private void uploadData(String name, String phone, String email) {

        HashMap<String,Object>mp = new HashMap<>();
        mp.put("name",name);
        mp.put("phone",phone);
        mp.put("email",email);

        databaseReference.child(phone).updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Data entered successfully.", Toast.LENGTH_SHORT).show();
                    nameEdt.setText("");
                    phoneEdt.setText("");
                    emailEdt.setText("");
                }
            }
        });

    }
}