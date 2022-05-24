package com.yukimstore.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.yukimstore.R;

import java.util.List;

import com.yukimstore.db.entity.Store;

public class StoreListAdapter extends ArrayAdapter<Store> {
    private int resourceLayout;

    public StoreListAdapter(Context c, int resource,  List<Store> stores) {
        super(c, resource, stores);
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

        Store s = getItem(position);

        if (s != null) {
            ImageView image_store = v.findViewById(R.id.image_store);
            TextView name_store = v.findViewById(R.id.name_store);
            ConstraintLayout btn_store = v.findViewById(R.id.btn_store);
            btn_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Not implemented Yet",Toast.LENGTH_SHORT).show();
                }
            });

            if (image_store != null ) {
                Bitmap bmp;
                if ( s.img_store != null) {
                    bmp = BitmapFactory.decodeByteArray(s.img_store, 0, s.img_store.length);
                } else {
                    bmp = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.store);
                    Log.i("test",bmp.toString());
                }
                image_store.setImageBitmap(bmp);
            }

            if (name_store != null) {
                name_store.setText(s.name);
            }
        }

        return v;
    }
}
