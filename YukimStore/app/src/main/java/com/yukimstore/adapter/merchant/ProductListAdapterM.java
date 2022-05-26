package com.yukimstore.adapter.merchant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yukimstore.R;
import com.yukimstore.activity.concrete_activity.merchant.ConsultProductActivityM;
import com.yukimstore.db.entity.Product;

import java.util.List;

public class ProductListAdapterM extends ArrayAdapter<Product> {
    private int resourceLayout;

    public ProductListAdapterM(Context c, List<Product> products) {
        super(c, R.layout.m_product_item, products);
        this.resourceLayout = R.layout.m_product_item;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @SuppressLint("SetTextI18n")
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

            ConstraintLayout btn_item = v.findViewById(R.id.btn_item);
            btn_item.setOnClickListener(view -> {
                Intent intent;
                intent = new Intent(getContext(), ConsultProductActivityM.class);
                intent.putExtra("product",p);
                getContext().startActivity(intent);
            });

            if (name_product != null ) {
                name_product.setText(p.name);
            }

            if (details_product != null ) {
                details_product.setText(p.details);
            }

            if (price_product != null ) {
                price_product.setText(p.price +getContext().getResources().getString(R.string.euro));
            }
        }

        return v;
    }
}
