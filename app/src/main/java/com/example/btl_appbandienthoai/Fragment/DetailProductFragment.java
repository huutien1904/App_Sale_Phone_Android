package com.example.btl_appbandienthoai.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.btl_appbandienthoai.Class.Product;
import com.example.btl_appbandienthoai.Home;
import com.example.btl_appbandienthoai.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailProductFragment extends Fragment {
    // region Variable

    private DecimalFormat format = new DecimalFormat("###,###,###");

    // Kiểm tra Product đã được thêm vào cart chưa
    private Boolean isAddToCart;

    // Activity
    private Home home;

    // View
    private View mView;
    private Product detailProduct;
    private List<Product> listCartProduct;
    private TextView tvDetailProductName,tvDetailProductPrice,tvDetailProductDescription;
    private Button btnDetailProductBuy;
    private ImageView imgDetailProductPhoto;


    public DetailProductFragment(Product product,List<Product> listProduct) {
        detailProduct = product;
        listCartProduct = listProduct;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detail_product, container, false);

        initItem();

        setValueItem();

        return  mView;
    }
    // Khởi tạo các item
    private void initItem(){

        isAddToCart = false;
        home = (Home) getActivity();
        tvDetailProductName = mView.findViewById(R.id.tv_detail_product_name);
        tvDetailProductPrice = mView.findViewById(R.id.tv_detail_product_price);
        tvDetailProductDescription = mView.findViewById(R.id.tv_detail_product_description);
        imgDetailProductPhoto = mView.findViewById(R.id.img_detail_product_photo);
        btnDetailProductBuy = mView.findViewById(R.id.btn_detail_product_buy);

        if(listCartProduct == null){
            listCartProduct = new ArrayList<>();
        }
    }

    private void setValueItem(){
        if(detailProduct != null){
            tvDetailProductName.setText(detailProduct.getProductName());
            tvDetailProductPrice.setText(format.format(detailProduct.getProductPrice() ) + " VNĐ");
            Glide.with(getContext()).load(detailProduct.getUrlImg()).into(imgDetailProductPhoto);
            tvDetailProductDescription.setText(detailProduct.getDescription());

            // Kiểm tra sản phẩm đã dc add vào giỏ hay chưa
            for (int i = 0;i< listCartProduct.size();i++){

                // Nếu sản  phẩm đã dc add
                if (listCartProduct.get(i).getProductName().equals(detailProduct.getProductName())){
                    isAddToCart = true;
                    btnDetailProductBuy.setText("Đã Mua");
                    btnDetailProductBuy.setBackgroundResource(R.drawable.custom_button_gray);
                    break;
                }
            }

            btnDetailProductBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("ấn MUA NGAY", "onClick: ");
                    if (isAddToCart){
                        Toast.makeText(getActivity(),"Sản Phẩm này đã tồn tại trong giỏ hàng",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        isAddToCart = true;
                        btnDetailProductBuy.setText("Đã Mua");
                        btnDetailProductBuy.setBackgroundResource(R.drawable.custom_button_gray);
                        home.addToListCartProdct(detailProduct);

                        Toast.makeText(getActivity(),"Đã thêm sản phẩm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
