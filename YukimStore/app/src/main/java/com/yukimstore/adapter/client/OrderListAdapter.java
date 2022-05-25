package com.yukimstore.adapter.client;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yukimstore.R;
import com.yukimstore.activity.used.client.ConsultListProductsActivity;
import com.yukimstore.activity.used.client.ConsultOrderActivity;
import com.yukimstore.activity.used.client.ConsultProductActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Order;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Order> {
    private int resourceLayout;

    public OrderListAdapter(Context c, List<Order> orders) {
        super(c, R.layout.c_order_item, orders);
        this.resourceLayout = R.layout.c_order_item;
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

        Order o = getItem(position);

        if (o != null) {
            TextView store_name = v.findViewById(R.id.store_name);
            TextView date_order = v.findViewById(R.id.date_order);
            TextView total_price_order = v.findViewById(R.id.total_price_order);

            ConstraintLayout btn_store = v.findViewById(R.id.btn_item);
            btn_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    intent = new Intent(getContext(), ConsultOrderActivity.class);
                    intent.putExtra("order",o);
                    getContext().startActivity(intent);
                }
            });

            if (store_name != null ) {
                Store s = AppDatabase.getInstance(getContext()).storeDAO().get(o.id_store);
                store_name.setText(s.name);
            }

            if (date_order != null ) {
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date_str = dateFormat.format(o.date_order);
                date_order.setText(date_str);
            }

            if (total_price_order != null ) {
                total_price_order.setText(String.valueOf(AppDatabase.getInstance(getContext()).productInOrderDAO().getSumOrder(o.id_order))+getContext().getResources().getString(R.string.euro));
            }
        }

        return v;
    }
}
