package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;

public class SubOrders {

    @SerializedName("sub_order_id")
    @Expose
    private Integer subOrderId;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("cat_level")
    @Expose
    private Integer catLevel;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("color_name")
    @Expose
    private String colorName;
    @SerializedName("color_code")
    @Expose
    private String colorCode;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("updated_rechecked_quantity")
    @Expose
    private Integer updatedRecheckedQuantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("product_price_with_gst")
    @Expose
    private Double productPriceWithGst;
    @SerializedName("updated_product_price_with_gst")
    @Expose
    private Double updatedProductPriceWithGst;


    public Integer getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(Integer catLevel) {
        this.catLevel = catLevel;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Integer getUpdatedRecheckedQuantity() {
        return updatedRecheckedQuantity;
    }

    public void setUpdatedRecheckedQuantity(Integer updatedRecheckedQuantity) {
        this.updatedRecheckedQuantity = updatedRecheckedQuantity;
    }

    public Double getProductPriceWithGst() {
        return productPriceWithGst;
    }

    public void setProductPriceWithGst(Double productPriceWithGst) {
        this.productPriceWithGst = productPriceWithGst;
    }

    public Double getUpdatedProductPriceWithGst() {
        return updatedProductPriceWithGst;
    }

    public void setUpdatedProductPriceWithGst(Double updatedProductPriceWithGst) {
        this.updatedProductPriceWithGst = updatedProductPriceWithGst;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubOrders subOrders = (SubOrders) o;
        return subOrderId.equals(subOrders.subOrderId) &&
                orderId.equals(subOrders.orderId) &&
                catLevel.equals(subOrders.catLevel) &&
                productCode.equals(subOrders.productCode) &&
                productName.equals(subOrders.productName) &&
                image.equals(subOrders.image) &&
                colorName.equals(subOrders.colorName) &&
                colorCode.equals(subOrders.colorCode) &&
                unit.equals(subOrders.unit) &&
                quantity.equals(subOrders.quantity) &&
                updatedRecheckedQuantity.equals(subOrders.updatedRecheckedQuantity) &&
                price.equals(subOrders.price)&&
                productPriceWithGst.equals(subOrders.productPriceWithGst) &&
                updatedProductPriceWithGst.equals(subOrders.updatedProductPriceWithGst);
    }



    public static DiffUtil.ItemCallback<SubOrders> itemCallback= new DiffUtil.ItemCallback<SubOrders>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull SubOrders oldItem, @androidx.annotation.NonNull SubOrders newItem) {
            return oldItem.getOrderId().equals(newItem.getOrderId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull SubOrders oldItem, @androidx.annotation.NonNull SubOrders newItem) {
            return oldItem.equals(newItem);
        }
    };
}
