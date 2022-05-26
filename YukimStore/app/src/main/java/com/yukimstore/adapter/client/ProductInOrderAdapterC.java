package com.yukimstore.adapter.client;
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
import com.yukimstore.activity.concrete_activity.client.ConsultProductActivityC;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Order;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.ProductInOrder;

import java.util.List;

public class ProductInOrderAdapterC extends ArrayAdapter<Product> {
    private int resourceLayout;
    private Order order;

    public ProductInOrderAdapterC(Context c, List<Product> products, Order o) {
        super(c, R.layout.c_product_item_order, products);
        this.resourceLayout = R.layout.c_product_item_order;
        order = o;
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
            ProductInOrder pio = AppDatabase.getInstance(getContext()).productInOrderDAO().getWithOrderAndProduct(order.id_order,p.id_product);
            TextView product_name = v.findViewById(R.id.product_name);
            TextView quantity = v.findViewById(R.id.quantity);
            TextView product_category_name = v.findViewById(R.id.product_category_name);
            TextView total_price = v.findViewById(R.id.total_price_item);

            ConstraintLayout btn_store = v.findViewById(R.id.btn_item);
            btn_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    intent = new Intent(getContext(), ConsultProductActivityC.class);
                    intent.putExtra("product",p);
                    getContext().startActivity(intent);
                }
            });

            if (product_name != null ) {
                product_name.setText(p.name);
            }

            if (quantity != null ) {
                quantity.setText(pio.quantity+ getContext().getResources().getString(R.string.ordered));
            }

            if (product_category_name != null ) {
                Category c = AppDatabase.getInstance(getContext()).categoryDAO().get(p.id_category);
                product_category_name.setText(c.name);
            }

            if (total_price != null ) {
                total_price.setText(String.valueOf(pio.total_price)+ getContext().getResources().getString(R.string.euro));
            }
        }

        return v;
    }
}
