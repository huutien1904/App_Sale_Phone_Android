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
import com.example.btl_appbandienthoai.Home;
import com.example.btl_appbandienthoai.R;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHoder> {

    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Product> mListProduct;
    private Home home;

    // Khai báo Interface giúp cho việc click vào phần tử của recycleview
    public interface IClickItemProductListener{
        void onClickItemProduct(Product product);
    }

    //    set dữ liệu vào recycle view
    public void setData(List<Product> mList, Home home) {
        this.mListProduct = mList;
        this.home = home;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoder holder, int position) {
        Product product = mListProduct.get(position);
        if(product == null){
            return;
        }
        else{
            Glide.with(holder.imgPhotoProduct.getContext()).load(product.getUrlImg()).into(holder.imgPhotoProduct);
            holder.tvProductName.setText(product.getProductName());
            holder.tvProductPrice.setText(formatPrice.format(product.getProductPrice()) + " VNĐ");

            holder.setItemClickListener(new IClickItemProductListener() {
                @Override
                public void onClickItemProduct(Product product) {
                    home.toDetailProductFragment(product);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }else return 0;
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgPhotoProduct;
        TextView tvProductName,tvProductPrice;
        IClickItemProductListener iClickItemProductListener;

        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);
            imgPhotoProduct = itemView.findViewById(R.id.img_photo_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(IClickItemProductListener itemClickListener){
            this.iClickItemProductListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.iClickItemProductListener.onClickItemProduct(mListProduct.get(getAdapterPosition()));
        }
    }
}
