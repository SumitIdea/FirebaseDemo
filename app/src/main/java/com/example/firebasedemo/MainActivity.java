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

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button login;
    TextView tv_reg;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(MainActivity.this,Home.class));
        }

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("register please wait");

        e1=findViewById(R.id.et_unem);
        e2=findViewById(R.id.et_password);
        login=findViewById(R.id.btn_log);
        tv_reg=findViewById(R.id.tv_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              loginUser();;
            }
        });
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
    public void loginUser()
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
            firebaseAuth.signInWithEmailAndPassword(userNm,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                startActivity(new Intent(MainActivity.this,Home.class));
                                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Problem in LogIn", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}