package com.yukimstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        db = AppDatabase.getInstance(getApplicationContext());
    }

    public void validateRegister(View view) {
        EditText et_mail = (EditText)findViewById(R.id.et_mail);
        EditText et_password = (EditText)findViewById(R.id.et_password);
        CheckBox cb_is_merchant = (CheckBox)findViewById(R.id.cb_is_merchant);

        String mail = et_mail.getText().toString();
        String hash_pwd = et_password.getText().toString();
        boolean is_merchant = cb_is_merchant.isChecked();

        if(mail.equals("") || hash_pwd.equals("")) {
            Toast.makeText(this,"Please fill the form",Toast.LENGTH_SHORT).show();
            return;
        }

        User userInDatabase = db.userDAO().get(mail);

        if(userInDatabase != null) {
            Toast.makeText(this,"Email already in use",Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(mail,hash_pwd,is_merchant);

        db.userDAO().insert(user);

        if(is_merchant) {
            startActivity(new Intent(this,CreateStoreActivity.class));
        }
        else {
            startActivity(new Intent(this,ClientMenuActivity.class));
        }
    }
}