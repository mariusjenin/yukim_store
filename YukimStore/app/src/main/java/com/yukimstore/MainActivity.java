package com.yukimstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yukimstore.db_entity.User;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        db = AppDatabase.getInstance(getApplicationContext());
    }

    public void goRegister(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void resetDatabase(View view) {
        db.userDAO().clear();
        db.productDAO().clear();
        Toast.makeText(this,"Database is now empty",Toast.LENGTH_SHORT).show();
    }

    public void validateLogin(View view) {
        EditText et_mail = (EditText)findViewById(R.id.et_mail);
        EditText et_password = (EditText)findViewById(R.id.et_password);

        String mail = et_mail.getText().toString();
        String hash_pwd = et_password.getText().toString();

        if(mail.equals("") || hash_pwd.equals("")) {
            Toast.makeText(this,"Please fill the form",Toast.LENGTH_SHORT).show();
            return;
        }

        User user = db.userDAO().get(mail);

        if(user == null) {
            Toast.makeText(this,"Account does not exist",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!user.hash_pwd.equals(hash_pwd)) {
            Toast.makeText(this,"Wrong password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(user.is_merchant) {
            startActivity(new Intent(this,MerchantMenuActivity.class));
        }
        else {
            startActivity(new Intent(this,ClientMenuActivity.class));
        }
    }
}