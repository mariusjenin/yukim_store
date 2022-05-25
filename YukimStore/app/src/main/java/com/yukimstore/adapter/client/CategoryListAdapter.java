package com.yukimstore.adapter.client;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yukimstore.R;
import com.yukimstore.activity.used.client.ConsultListProductsActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends ArrayAdapter<Category> {
    private int resourceLayout;

    public CategoryListAdapter(Context c, List<Category> categories) {
        super(c, R.layout.c_category_item, categories);
        this.resourceLayout = R.layout.c_category_item;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(resourceLayout, null);
        }

        Category c = getItem(position);

        if (c != null) {
            TextView name_category = v.findViewById(R.id.category_name);
            TextView interests_category = v.findViewById(R.id.interests_category);

            ConstraintLayout btn_store = v.findViewById(R.id.btn_item);
            btn_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    Context context = getContext();
                    Resources resources = context.getResources();
                    intent = new Intent(context, ConsultListProductsActivity.class);
                    Store store =  AppDatabase.getInstance(context).storeDAO().get(c.id_store);
                    intent.putExtra("title",resources.getString(R.string.products_of)+c.name+resources.getString(R.string.space_of_space)+store.name);
                    ArrayList<Product> products = (ArrayList<Product>) AppDatabase.getInstance(context).categoryDAO().getProductsOfCategory(c.id_category);
                    intent.putExtra("products",products);
                    context.startActivity(intent);
                }
            });

            if (name_category != null ) {
                name_category.setText(c.name);
            }

            if (interests_category != null ) {
                interests_category.setText("interests"); //TODO
            }
        }

        return v;
    }
}