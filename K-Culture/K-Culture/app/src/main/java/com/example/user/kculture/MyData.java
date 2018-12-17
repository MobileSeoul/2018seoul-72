package com.example.user.kculture;

//import com.google.android.gms.maps.model.LatLng;

public class MyData {

    public static final int FILTER_PLACE = 1;
    public static final int FILTER_FOOD = 2;
    public static final int FILTER_TRADITION = 4;
    //public static final int FILTER_TRADITION = 4;


    public int pic; //icon
    public long pkey;
    //public LatLng loc;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    private int category=0; //category 1

    public MyData(int icon,long pkey, int category) {
        this.pic=icon;
        this.pkey = pkey;
        this.category = category;
    }


}
