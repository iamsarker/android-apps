package com.technovalley21.app.addtocardanimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.technovalley21.app.addtocardanimation.R;
import com.technovalley21.app.addtocardanimation.model.Product;

import java.util.List;

/**
 * Created by sarker on 11/20/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> mList;
    private Context mContext;

    private ProductItemActionListener actionListener;
    public ProductAdapter(Context mContext, List<Product> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setActionListener(ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }


    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ProductHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ProductHolder holder, int position) {


        Product product=mList.get(position);

        holder.iv_product.setImageResource(product.getIconId());
        holder.iv_product_copy.setImageResource(product.getIconId());

        holder.iv_product_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionListener!=null)
                    actionListener.onItemTap(holder.iv_product_copy);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class  ProductHolder extends RecyclerView.ViewHolder{


        protected ImageView iv_product;
        protected ImageView iv_product_copy;


        public ProductHolder(View itemView) {
            super(itemView);
            iv_product=(ImageView) itemView.findViewById(R.id.iv_product);
            iv_product_copy=(ImageView) itemView.findViewById(R.id.iv_product_copy);

        }

    }


    public interface ProductItemActionListener{
        void onItemTap(ImageView imageView);
    }
}
