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
        year = 0;
        month = 0;
        day = 0;
        hour =0;
        min = 0;
        spa_num = null;
        piz_num = null;
        vip_membership = false;
        total_price = 0;
    }

    public void putTableDate(Table t , int year, int month, int day) {
        t.year = year;
        t.month = month;
        t.day = day;
    }
    public void putTableTime (Table t,  int hour, int min)    {
        t.hour = hour;
        t.min = min;
    }
    public void putTableMenu (Table t,String spa_num,String piz_num,boolean vip_membership){

        t.spa_num = spa_num;
        int spa_numi= Integer.parseInt(spa_num);
        t.piz_num = piz_num;
        int piz_numi = Integer.parseInt(piz_num);
        t.vip_membership = vip_membership;
        if(vip_membership == true ) this.total_price =  (int) (((10000 * spa_numi) + (12000 * piz_numi)) * 0.7);
        else t.total_price =    (int) (( (10000 * spa_numi) + (12000 * piz_numi)) * 0.9);

        t.is_void = false;
    }

    public void putTableData(Table receiver, Table sender){
        receiver.Tablename = sender.Tablename;
        receiver.is_void =sender.is_void;
        receiver.year = sender.year;
        receiver.month=sender.month;
        receiver.day = sender.day;
        receiver.hour = sender.hour;
        receiver.min = sender.min;
        receiver.spa_num = sender.spa_num;
        receiver.piz_num = sender.piz_num;
        receiver.vip_membership = sender.vip_membership;
        receiver.total_price = sender.total_price;


    }
}
