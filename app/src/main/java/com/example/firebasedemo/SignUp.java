package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText e1,e2;
    Button signup;
    TextView tv_login;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        e1=findViewById(R.id.et_reg_unem);
        e2=findViewById(R.id.et_reg_password);
        signup=findViewById(R.id.btn_signup);
        tv_login=findViewById(R.id.tv_login);
        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("register please wait");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void registerUser()
    {
        String userNm=e1.getText().toString().trim();
        String pass=e2.getText().toString().trim();

        progressDialog.show();
        if (TextUtils.isEmpty(userNm))
        {
            e1.setError("please enter user name");
        }

        else if(TextUtils.isEmpty(pass))
        {
            e2.setError("Enter password");
        }
        else
        {
            firebaseAuth.createUserWithEmailAndPassword(userNm,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                startActivity(new Intent(SignUp.this,MainActivity.class));
                                Toast.makeText(SignUp.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this, "Problem in User Creating", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}