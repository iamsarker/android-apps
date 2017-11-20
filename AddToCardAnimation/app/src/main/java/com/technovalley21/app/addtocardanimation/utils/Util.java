package com.technovalley21.app.addtocardanimation.utils;

import com.technovalley21.app.addtocardanimation.R;
import com.technovalley21.app.addtocardanimation.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarker on 11/20/17.
 */

public class Util {
    public static List<Product> demoProducts(){
        List<Product> list = new ArrayList<>();

        for(int i=1 ; i<4 ; i++){
            list.add( new Product(i, "Samsung Galaxy S" + i, 11150 * (float)i, R.drawable.default_icon ) );
        }

        return list;
    }
}
