package com.yukimstore.adapter.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yukimstore.R;
import com.yukimstore.db.entity.Category;

import java.util.List;

public class ClientCategoryListAdapter extends ArrayAdapter<Category> {
    private int resourceLayout;

    public ClientCategoryListAdapter(Context c, int resource, List<Category> categories) {
        super(c, resource, categories);
        this.resourceLayout = resource;
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
                    Toast.makeText(getContext(),"Not implemented Yet",Toast.LENGTH_SHORT).show();
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
