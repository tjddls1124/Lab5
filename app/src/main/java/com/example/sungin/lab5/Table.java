package com.example.sungin.lab5;

/**
 * Created by SungIn on 2017-04-05.
 */

public class Table {
    String Tablename;
    int year;
    int month;
    int day;
    int hour;
    int min;

    String spa_num;
    String piz_num;
    boolean vip_membership;
    int total_price;

    boolean is_void;

    public Table(String t){
        Tablename = t;
        is_void = true;
    }

    public Table putTable(int year, int month, int day, int hour, int min,String spa_num,String piz_num,boolean vip_membership){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;

        this.spa_num = spa_num;
        int spa_numi= Integer.getInteger(spa_num);
        this.piz_num = piz_num;
        int piz_numi = Integer.getInteger(piz_num);
        this.vip_membership = vip_membership;
        if(vip_membership == true ) this.total_price =   (int) ( ((10000 * spa_numi) + (12000 * piz_numi)) * 0.7);
        else this.total_price =   (int) ( ( (10000 * spa_numi) + (12000 * piz_numi)) * 0.9);

        this.is_void = false;

        return this;
    }
}
