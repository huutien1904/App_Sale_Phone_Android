package com.example.btl_appbandienthoai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_appbandienthoai.Class.Product;
import com.example.btl_appbandienthoai.Fragment.CartFragment;
import com.example.btl_appbandienthoai.Home;
import com.example.btl_appbandienthoai.R;

import java.text.DecimalFormat;
import java.util.List;
import java.util.PrimitiveIterator;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductCartViewHolder>{

    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Product> listProductCart;
    private int countProduct;
    private Home home;
    private CartFragment cartFragment;

    public void setData(List<Product> listProductCart, Home home,CartFragment cartFragment) {
        this.listProductCart = listProductCart;
        this.home = home;
        this.cartFragment = cartFragment;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new ProductCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCartViewHolder holder, int position) {
        Product product = listProductCart.get(position);
        if(product == null){
            return;
        }
        else{
            Glide.with(holder.imgPhotoCart.getContext()).load(product.getUrlImg()).into(holder.imgPhotoCart);
            holder.tvNameProductCart.setText(product.getProductName());
            holder.tvPriceProductCart.setText(formatPrice.format(product.getProductPrice()) + " VND");
            int count = product.getNumProduct();
            if(count != 0){
                holder.tvCountCart.setText(String.valueOf(count));
            }else {
                holder.tvCountCart.setText(String.valueOf(1));
            }
//            khi nguoi dung bam nut tru
            holder.imgMinusCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countProduct = Integer.parseInt(holder.tvCountCart.getText().toString());
                    if(countProduct > 1){
                        countProduct--;
                        home.setCountForProduct(position,countProduct);
                        cartFragment.setCountForProduct(position,countProduct);
                        holder.tvCountCart.setText(String.valueOf(countProduct));
                        cartFragment.setTotalPrice(0,1,product.getProductPrice());
                        notifyDataSetChanged();
                    }
                }
            });
// khi nguoi dung bam nut cong
            holder.imgPlusCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countProduct = Integer.parseInt(holder.tvCountCart.getText().toString());
                    if (countProduct < 10){
                        countProduct++;
                        home.setCountForProduct(position,countProduct);
                        cartFragment.setCountForProduct(position,countProduct);
                        holder.tvCountCart.setText(String.valueOf(countProduct));
                        cartFragment.setTotalPrice(1,1,product.getProductPrice());
                        notifyDataSetChanged();
                    }
                }
            });

//            khi nguoi dung bam nut xoa
            holder.imgRemoveCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countProduct = Integer.parseInt(holder.tvCountCart.getText().toString());

                    cartFragment.setTotalPrice(0,countProduct,product.getProductPrice());

                    listProductCart.remove(position);
                    if (listProductCart.size() == 0){
                        cartFragment.setVisibilityEmptyCart();
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(listProductCart != null){
            return listProductCart.size();
        }
        return 0;
    }

    public class ProductCartViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhotoCart;
        TextView tvNameProductCart, tvPriceProductCart,tvCountCart ;
        ImageView imgMinusCart,imgPlusCart,imgRemoveCart;

        public ProductCartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhotoCart = itemView.findViewById(R.id.img_photo_cart);
            tvNameProductCart = itemView.findViewById(R.id.tv_name_product_cart);
            tvPriceProductCart = itemView.findViewById(R.id.tv_price_product_cart);
            imgMinusCart = itemView.findViewById(R.id.img_minus_cart);
            tvCountCart = itemView.findViewById(R.id.tv_count_cart);
            imgPlusCart = itemView.findViewById(R.id.img_plus_cart);
            imgRemoveCart = itemView.findViewById(R.id.img_remove_cart);
        }
    }
}
