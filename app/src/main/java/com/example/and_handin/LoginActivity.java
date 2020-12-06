package com.example.and_handin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.and_handin.ui.database.User;
import com.example.and_handin.ui.database.UserDatabase;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText email;
   EditText password;
   Button login;
   Button createAcc;
   private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        createAcc=findViewById(R.id.createAcc);
        userDatabase=UserDatabase.getDbInstance(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();

            }
    });
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAcc();
            }
        });
    }

    private void createAcc() {
        User temp=new User();
        temp.email=email.getText().toString();
        temp.password=password.getText().toString();
        userDatabase.userDao().insertUser(temp);
        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();

    }

    private void checkLogin() {
        boolean login=false;
        ArrayList<User> userList= (ArrayList<User>) userDatabase.userDao().getAllUsers();
        for (int i = 0; i <userList.size() ; i++) {
            if(email.getText().toString().equals(userList.get(i).email) && password.getText().toString().equals(userList.get(i).password))
            {
                Intent intent= new Intent(this,MainActivity.class);
                startActivity(intent);
                login=true;
                finish();
            }
        }
        if (login==false){
            email.setText("");
            password.setText("");
            Toast.makeText(this, "Wrong email or password", Toast.LENGTH_SHORT).show();
        }

    }
}