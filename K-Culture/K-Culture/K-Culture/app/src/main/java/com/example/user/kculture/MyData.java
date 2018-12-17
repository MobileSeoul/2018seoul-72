package com.example.user.kculture;

public class MyData {

    public static final int FILTER_PLACE = 1;
    public static final int FILTER_FOOD = 2;
    public static final int FILTER_TRADITION = 4;
    //public static final int FILTER_TRADITION = 4;


    public int pic; //icon
    public String name;//place name
    public String exp;//place explanation

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    private int category=0; //category 1

    public MyData(int icon,String name,String exp) {
        this.pic=icon;
        this.name=name;
        this.exp=exp;
    }


}
