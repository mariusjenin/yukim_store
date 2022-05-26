package com.yukimstore.manager;

import android.content.Context;

import com.yukimstore.activity.concrete_activity.client.ConsultBasketActivityC;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Offer;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.ProductInBasket;
import com.yukimstore.db.entity.ProductInOrder;

import java.util.Date;
import java.util.List;

public class BasketManager {
    public static float getSumBasket(Context context, int id_user){
        AppDatabase apd =  AppDatabase.getInstance(context);
        List<ProductInBasket> pibs = apd.productInBasketDAO().getWithUser(id_user);
        int size_basket = pibs.size();
        float sum_basket = 0;

        for(int i = 0; i < size_basket;i++){
            Product product =  apd.productDAO().get(pibs.get(i).id_product);

            float price;
            Offer offer = apd.offerDAO().get(product.id_product,new Date());
            if(offer == null ){
                price = product.price;
            } else {
                price = offer.price;
            }

            sum_basket += pibs.get(i).quantity * price;
        }

        return Math.round(sum_basket*100.0f)/100.0f;
    }
}
