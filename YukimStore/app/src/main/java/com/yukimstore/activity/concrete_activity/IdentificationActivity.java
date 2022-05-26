package com.yukimstore.activity.concrete_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.NotConnectedActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.dao.UserDAO;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;
import com.yukimstore.utils.HashUtil;

public class IdentificationActivity extends NotConnectedActivity {

    private boolean is_sign_up;
    private boolean is_merchant;
    private TextView tv_title;
    private EditText et_email;
    private EditText et_pwd;
    private EditText et_pwd_confirm;
    private EditText et_first_name;
    private EditText et_last_name;
    private Button but_submit;
    private Button but_toggle_identification;
    private FloatingActionButton but_db_clear_identification;
    private CheckBox checkbox_stay_connected;
    private CheckBox checkbox_is_merchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        ConnectionManager cm =ConnectionManager.getInstance();
        if(!cm.isConnected()){
            String token = cm.getTokenUserFromPrefs(this);
            if(token != null){
                User user = AppDatabase.getInstance(this).userDAO().getWithToken(token);
                if(user != null){
                    cm.setConnected(user);
                    connection_middleware.redirect(this);
                }
            }
        }

        //We begin on the signin page as a customer
        is_sign_up = false;
        is_merchant = false;

        //Put the right layout
        this.setContentView(R.layout.identification);

        tv_title = findViewById(R.id.title_identification);
        et_email = findViewById(R.id.input_email_identification);
        et_pwd = findViewById(R.id.input_pwd_identification);
        et_pwd_confirm = findViewById(R.id.input_pwd_confirm_identification);
        et_first_name = findViewById(R.id.input_first_name);
        et_last_name = findViewById(R.id.find_stores);
        but_submit = findViewById(R.id.submit_identification);
        but_toggle_identification = findViewById(R.id.toggle_identification);
        but_db_clear_identification = findViewById(R.id.db_clear_identification);
        checkbox_stay_connected = findViewById(R.id.checkbox_stay_connected_identification);
        checkbox_is_merchant = findViewById(R.id.checkbox_is_merchant_identification);

        //Initialize text and hint of each component
        initializeFormidentification();

    }

    private void actualizeFormIdentification() {
        if (is_sign_up) {
            tv_title.setText(R.string.register);
            but_toggle_identification.setText(R.string.toggle_identification_from_signup);
            et_pwd.setText("");
            et_pwd_confirm.setText("");
            et_pwd_confirm.setVisibility(View.VISIBLE);
            checkbox_is_merchant.setVisibility(View.VISIBLE);
        } else {
            checkbox_is_merchant.setChecked(false);
            checkbox_is_merchant.setVisibility(View.GONE);
            tv_title.setText(R.string.login);
            but_toggle_identification.setText(R.string.toggle_identification_from_signin);
            et_pwd.setText("");
            et_pwd_confirm.setVisibility(View.GONE);
        }
        if(is_merchant || !is_sign_up){
            et_first_name.setVisibility(View.GONE);
            et_last_name.setVisibility(View.GONE);
        } else {
            et_first_name.setVisibility(View.VISIBLE);
            et_last_name.setVisibility(View.VISIBLE);
        }
        but_submit.setText(R.string.validate);
    }

    private void initializeFormidentification() {
        et_email.setHint(R.string.mail);
        et_pwd.setHint(R.string.password);
        et_pwd_confirm.setHint(R.string.password2);


        but_toggle_identification.setOnClickListener(v -> {
            is_sign_up = !is_sign_up;
            actualizeFormIdentification();
        });

        checkbox_is_merchant.setOnClickListener(v -> {
            is_merchant = !is_merchant;
            actualizeFormIdentification();
        });

        but_db_clear_identification.setOnClickListener(v -> {
            AppDatabase.getInstance(this).init();
            Toast.makeText(this,"Database is now initialized with datas",Toast.LENGTH_SHORT).show();
        });

        but_submit.setOnClickListener(v -> submit());

        actualizeFormIdentification();
    }

    private void submit() {
        //Get the value of each input
        String email = et_email.getText().toString();

        if(email.length() == 0){ //TODO Verify email form instead
            Toast.makeText(this,"Email empty",Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = et_pwd.getText().toString();

        if(pwd.length() < 6){
            Toast.makeText(this,"Password must be composed of at least 6 characters",Toast.LENGTH_SHORT).show();
            return;
        }

        boolean stay_connected = checkbox_stay_connected.isChecked();

        UserDAO user_DAO = AppDatabase.getInstance(this).userDAO();

        boolean connect = false;
        User user = user_DAO.get(email);
        if(is_sign_up){//REGISTER
            if(user == null){
                String pwd_confirm = et_pwd_confirm.getText().toString();
                if(pwd.equals(pwd_confirm)){
                    String first_name = "";
                    String last_name = "";
                    if(!is_merchant){
                        first_name = et_first_name.getText().toString();
                        last_name = et_last_name.getText().toString();
                        if(first_name.length() == 0){
                            Toast.makeText(this,"First name empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(last_name.length() == 0){
                            Toast.makeText(this,"Last name empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    user = new User(email,HashUtil.getSHA256SecurePassword(pwd,""), is_merchant, first_name, last_name);
                    user_DAO.insert(user);
                    connect = true;
                }
            } else {
                Toast.makeText(this,"This account already exists",Toast.LENGTH_SHORT).show();
                return;
            }
        } else {//LOGIN
            if(user != null){
                if(HashUtil.getSHA256SecurePassword(pwd,"").equals(user.hash_pwd)){
                    connect = true;
                } else {
                    Toast.makeText(this,"Wrong password",Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                Toast.makeText(this,"Account does not exist",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(connect){
            ConnectionManager cm = ConnectionManager.getInstance();
            cm.setConnected(user);
            if(stay_connected){
                cm.storeTokenUserInPrefs(this);
            } else {
                cm.removeTokenUserFromPrefs(this);
            }
            connection_middleware.redirect(this);
//            if(user.is_merchant) {
//                startActivity(new Intent(this, CreateStoreActivityM.class));
//            }
//            else {
//                startActivity(new Intent(this, MenuActivityC.class));
//            }
        }

    }
}