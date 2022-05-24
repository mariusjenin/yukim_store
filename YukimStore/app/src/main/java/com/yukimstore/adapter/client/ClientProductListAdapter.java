package com.yukimstore.adapter.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yukimstore.R;

import java.util.List;

import com.yukimstore.db.entity.Product;

public class ClientProductListAdapter extends ArrayAdapter<Product> {
    private int resourceLayout;

    public ClientProductListAdapter(Context c, List<Product> products) {
        super(c, R.layout.c_product_item, products);
        this.resourceLayout = R.layout.c_product_item;
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

        Product p = getItem(position);

        if (p != null) {
            TextView name_product = v.findViewById(R.id.product_name);
            TextView details_product = v.findViewById(R.id.product_details);
            TextView price_product = v.findViewById(R.id.product_price);
            Button add_to_basket = v.findViewById(R.id.add_to_basket);

            ConstraintLayout btn_store = v.findViewById(R.id.btn_item);
            btn_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Not implemented Yet",Toast.LENGTH_SHORT).show();
                }
            });

            add_to_basket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Not implemented Yet",Toast.LENGTH_SHORT).show();
                }
            });

            if (name_product != null ) {
                name_product.setText(p.name);
            }

            if (details_product != null ) {
                details_product.setText(p.details);
            }

            if (price_product != null ) {
                price_product.setText(String.valueOf(p.price));
            }
        }

        return v;
    }
}
