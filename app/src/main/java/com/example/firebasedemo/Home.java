  package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

  public class Home extends AppCompatActivity {

    TextView textView;
    Button logout,save,show_data,btn_pick,btn_show,btn_remove;
    EditText et_name,et_occup,et_address,et_phone,et_pin;
    FirebaseAuth firebaseAuth;
    User user1;
    ProgressDialog dialog;
    int i=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);
        textView=findViewById(R.id.tv_user);
        logout=findViewById(R.id.btn_logout);

        btn_remove=findViewById(R.id.btn_remove);
        btn_pick=findViewById(R.id.btn_pick);
        et_name=findViewById(R.id.et_name);
        et_occup=findViewById(R.id.et_occu);
        et_address=findViewById(R.id.et_address);
        et_phone=findViewById(R.id.et_contact_num);
        et_pin=findViewById(R.id.et_pincode);
        save=findViewById(R.id.btn_save);
        show_data=findViewById(R.id.btn_read);
        btn_show=findViewById(R.id.btn_show);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Adding data please wait...");

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
                        {
                            dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Home.this, "Successfully remove", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Home.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ShowImage.class);
                startActivity(intent);
            }
        });

        btn_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ImagePick.class);
                startActivity(intent);
            }
        });
        show_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Home.this,ShowData.class);
                startActivity(intent);
            }
        });

         user1=new User();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("User");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
               sendData();
            }

        });


        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        String user=firebaseUser.getEmail();

        textView.setText("Welcome "+user);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Toast.makeText(Home.this, "Sucessfully Logout", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

      @Override
      public void onBackPressed() {
          super.onBackPressed();
          finishAffinity();
      }


      public void getData()
      {
          user1.setName(et_name.getText().toString());
          user1.setOccupation(et_occup.getText().toString());
          user1.setAddress(et_address.getText().toString());
          user1.setPhone(et_phone.getText().toString());
          user1.setPincode(et_pin.getText().toString());

      }


      public void clearData()
      {
          et_name.setText("");
          et_address.setText("");
          et_occup.setText("");
          et_phone.setText("");
          et_pin.setText("");
      }



      public void sendData()
      {
          databaseReference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot)
              {
                  getData();
                  databaseReference.child("User 0"+i).setValue(user1);
                  dialog.cancel();
                  clearData();
                  Toast.makeText(Home.this, "Data Submitted Successfully", Toast.LENGTH_SHORT).show();

              }

              @Override
              public void onCancelled(@NonNull DatabaseError error)
              {

                  Toast.makeText(Home.this, " "+error, Toast.LENGTH_SHORT).show();
              }

          });

          i++;

      }
  }