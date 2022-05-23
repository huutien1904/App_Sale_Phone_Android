package com.example.btl_appbandienthoai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_appbandienthoai.Class.DetailOrder;
import com.example.btl_appbandienthoai.Class.Order;
import com.example.btl_appbandienthoai.Home;
import com.example.btl_appbandienthoai.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryProductAdapter extends RecyclerView.Adapter<HistoryProductAdapter.HistoryProductViewHolder> {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<DetailOrder> listDetailOrder;
    private List<Order> listOrder;
    private Order orderInfo;
    private Home home;

    public void setData(List<DetailOrder> listDetailOrder, List<Order> listOrder,Home home) {
        this.listDetailOrder = new ArrayList<>();
        this.listDetailOrder = listDetailOrder;
        this.listOrder = listOrder;
        this.home = home;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HistoryProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new  HistoryProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryProductViewHolder holder, int position) {
        DetailOrder detailOrder = listDetailOrder.get(position);
        if(detailOrder == null){
            return;
        }else{
            Glide.with(holder.imgHitoryProduct.getContext()).load(detailOrder.getUrlImg()).into(holder.imgHitoryProduct);
            holder.tvHitoryProductName.setText(detailOrder.getProductName());
            holder.tvHitoryProductNum.setText(String.valueOf(detailOrder.getNumProduct()));
            holder.tvHitoryProductPrice.setText(formatPrice.format(detailOrder.getProductPrice()) + "VNĐ");
            holder.tvHitoryProductStatus.setText(detailOrder.getStatus());
            holder.tvHitoryProductOrderNo.setText(detailOrder.getOrderNo().toUpperCase());
        }
        for (Order order : listOrder) {
            if(order.getOrderNo().equals(detailOrder.getOrderNo())){
                holder.tvHitoryProductDate.setText(order.getDateOrder());
                break;
            }
        }

        holder.tvHitoryProductOrderNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Order order:listOrder) {
                    if(order.getOrderNo().equals(detailOrder.getOrderNo())){
                        orderInfo = order;
                        break;
                    }
                }
                for (DetailOrder itemDetailOrder : listDetailOrder){
                    if(detailOrder.getOrderNo().equals(itemDetailOrder.getOrderNo())){
                        orderInfo.addToListDetailOrder(itemDetailOrder);
                    }
                }
                home.toOrderInfoFragment(orderInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listDetailOrder.size() != 0){
            return listDetailOrder.size();
        }else{
            return 0;
        }
    }

    public class HistoryProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHitoryProduct;
        TextView tvHitoryProductName,tvHitoryProductNum,tvHitoryProductPrice,tvHitoryProductDate
                ,tvHitoryProductStatus,tvHitoryProductOrderNo;

        public HistoryProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHitoryProduct = itemView.findViewById(R.id.img_hitory_product);
            tvHitoryProductName = itemView.findViewById(R.id.tv_hitory_product_name);
            tvHitoryProductNum = itemView.findViewById(R.id.tv_hitory_product_num);
            tvHitoryProductPrice = itemView.findViewById(R.id.tv_hitory_product_price);
            tvHitoryProductDate = itemView.findViewById(R.id.tv_hitory_product_date);
            tvHitoryProductStatus = itemView.findViewById(R.id.tv_hitory_product_status);
            tvHitoryProductOrderNo = itemView.findViewById(R.id.tv_hitory_product_orderNo);
        }
    }
}
