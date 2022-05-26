package com.yukimstore.activity.concrete_activity.merchant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yukimstore.InterestCheckbox;
import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedMerchantWithStoreActivity;
import com.yukimstore.adapter.InterestCheckboxAdapter;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Interest;
import com.yukimstore.db.entity.InterestForCategory;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.manager.ConnectionManager;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryActivityM extends ConnectedMerchantWithStoreActivity {
    private Store store;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        setContentView(R.layout.m_add_category);
        List<Interest> interests = AppDatabase.getInstance(this).interestDAO().getAll();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button validate = findViewById(R.id.validate);

        store = AppDatabase.getInstance(this).storeDAO().get(ConnectionManager.getInstance().getUtilisateur().id_user);
        TextView title = findViewById(R.id.title_add_category);
        title.setText(getResources().getString(R.string.add_a_category_to)+store.name);
        TextView category_name = findViewById(R.id.category_name);


        ArrayList<InterestCheckbox> list_interests = new ArrayList<>();

        int size_interests = interests.size();
        for (int i = 0 ; i < size_interests;i++) {
            InterestCheckbox icb = new InterestCheckbox(interests.get(i));
            icb.setSelected(false);
            list_interests.add(icb);
        }

        InterestCheckbox icb = new InterestCheckbox(new Interest("Interests"));
        icb.setSelected(false);
        list_interests.add(0,icb);

        InterestCheckboxAdapter adapter = new InterestCheckboxAdapter(this, 0,list_interests);
        spinner.setAdapter(adapter);


        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category_name_str = category_name.getText().toString();

                if(category_name_str.isEmpty()) {
                    Toast.makeText(AddCategoryActivityM.this,"Please enter a valid category name",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<InterestCheckbox> icbs = adapter.getInterests();
                Category category = new Category(category_name_str,store.id_user_store);
                int id_category = (int) AppDatabase.getInstance(AddCategoryActivityM.this).categoryDAO().insert(category);

                int size_interests = icbs.size();
                for(int i = 0; i < size_interests;i++){
                    if(icbs.get(i).isSelected()){
                        InterestForCategory ifc = new InterestForCategory(icbs.get(i).getInterest().id_interest,id_category);
                        AppDatabase.getInstance(AddCategoryActivityM.this).interestForCategoryDAO().insert(ifc);
                    }
                }

                finish();
                Toast.makeText(AddCategoryActivityM.this,"Your category have been saved",Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(AddCategoryActivityM.this, ConsultListProductsActivityM.class);
                intent.putExtra("title", category.name);
                ArrayList<Product> products = (ArrayList<Product>) AppDatabase.getInstance(AddCategoryActivityM.this).categoryDAO().getProductsOfCategory(id_category);
                intent.putExtra("products",products);
                startActivity(intent);
            }
        });
    }
}
