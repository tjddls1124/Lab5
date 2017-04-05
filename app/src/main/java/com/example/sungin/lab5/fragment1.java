package com.example.sungin.lab5;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

/**
 * Created by SungIn on 2017-04-05.
 */

public class fragment1 extends Fragment{
    Button button_apple,button_grape,button_grapefruit,button_kiwi,button_new,button_mod,button_reset;
    TextView text_name,text_time,text_spa,text_piz,text_mem,text_price;

    Table apple_table = new Table("apple");
    Table kiwi_table = new Table("kiwi");
    Table grape_table = new Table("grape");
    Table grapefruit_table = new Table("grapefruit");
    String last_click="";
    Table last_table = new Table("last");

    DatePicker datePicker ;
    TimePicker timePicker;
    EditText editText_piz ;
    EditText editText_spa ;
    CheckBox checkBox_vip ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1,container,false);

        button_apple = (Button) v.findViewById(R.id.button_appleTable);
        button_grape = (Button) v.findViewById(R.id.button_grapeTable);
        button_kiwi = (Button) v.findViewById(R.id.button_kiwiTable);
        button_grapefruit = (Button) v.findViewById(R.id.button_grapefruitTable);
        button_new = (Button)v.findViewById(R.id.buttonNewOrder);
        button_mod =(Button)v.findViewById(R.id.buttonModify);
        button_reset = (Button)v.findViewById(R.id.button_reset);
        text_name = (TextView) v.findViewById(R.id.textviewTablename);
        text_time =(TextView) v.findViewById(R.id.textViewTime);
        text_spa =(TextView) v.findViewById(R.id.textViewSpa);
        text_piz =(TextView)v.findViewById(R.id.textViewPiz);
        text_mem =(TextView)v.findViewById(R.id.textViewMembership);
        text_price = (TextView)v.findViewById(R.id.textViewTotalPrice);



        clickTable(apple_table,button_apple);
        clickTable(grape_table,button_grape);
        clickTable(grapefruit_table,button_grapefruit);
        clickTable(kiwi_table,button_kiwi);


        setOrder(last_table,button_new);
        setOrder(last_table,button_mod);
        setOrder(last_table,button_reset);

        return v;
    }
    public void setOrder(final Table table, final Button button) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), last_click , Toast.LENGTH_SHORT).show();
                TabHost tabHost;

                View dlgview = View.inflate(getContext(), R.layout.order_dlg, null);
                tabHost = (TabHost)dlgview.findViewById(R.id.tabhost1);
                tabHost.setup();

                tabHost.addTab(tabHost.newTabSpec("A").setContent(R.id.tab1).setIndicator("날짜선택"));
                tabHost.addTab(tabHost.newTabSpec("B").setContent(R.id.tab2).setIndicator("시간선택"));
                tabHost.addTab(tabHost.newTabSpec("C").setContent(R.id.tab3).setIndicator("메뉴선택"));

                datePicker = (DatePicker) dlgview.findViewById(R.id.datePicker);
                timePicker = (TimePicker) dlgview.findViewById(R.id.timePicker);
                editText_piz = (EditText) dlgview.findViewById(R.id.editText_piz);
                editText_spa = (EditText) dlgview.findViewById(R.id.editText_spa);
                checkBox_vip = (CheckBox)dlgview.findViewById(R.id.checkBox_vip);




                if(button ==button_mod) {
                    editText_piz.setText(table.piz_num);
                    editText_spa.setText(table.spa_num);
                    checkBox_vip.setChecked(table.vip_membership);
                }
                if (button == button_reset){
                   table.putTableMenu(table,"","",false);
                   table.putTableTime(table,0,0);
                   table.putTableDate(table,0,0,0);
                    table.is_void = true;
                    text_name.setText("");
                    text_time.setText("");
                    text_spa.setText("");
                    text_piz.setText("");
                    text_mem.setText("");
                    text_price.setText("");

                    return;
                }

                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setView(dlgview)
                        .setTitle("주문정보를 입력하세요")
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                table.putTableDate(table, datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
                                table.putTableTime(table, timePicker.getHour(),timePicker.getMinute());
                                 table.putTableMenu(table, editText_spa.getText().toString(),editText_piz.getText().toString(), checkBox_vip.isChecked() );

                                text_name.setText(table.Tablename);
                                text_time.setText(table.year+" 0"+ table.month +" 0"+ table.day + " " +table.hour+" : "+table.min);
                                text_spa.setText(table.spa_num);
                                text_piz.setText(table.piz_num);
                                if(table.vip_membership == true) text_mem.setText("VIP 멤버쉽");
                                else text_mem.setText("기본 멤버쉽");
                                text_price.setText(table.total_price+"원");



                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                if(last_table.Tablename == apple_table.Tablename) table.putTableData(apple_table, last_table);
                else if (last_table.Tablename == grape_table.Tablename) table.putTableData(grape_table, last_table);
                else if(last_table.Tablename == grapefruit_table.Tablename) table.putTableData(grapefruit_table, last_table);
                else  table.putTableData(kiwi_table, last_table) ;
            }
        });
    }
    public void clickTable(final Table table, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //토스트 + 초기화
                if(table.is_void ==true) {
                    last_click = table.Tablename;
                    Toast.makeText(getActivity().getApplicationContext(), "비어있는 테이블 입니다.", Toast.LENGTH_SHORT).show();
                    text_name.setText("");
                    text_time.setText("");
                    text_spa.setText("");
                    text_piz.setText("");
                    text_mem.setText("");
                    text_price.setText("");
                }
                else { // table에 저장되어있는 정보입력
                    text_name.setText(table.Tablename);
                    text_time.setText(table.year+" 0"+ table.month +" 0"+ table.day + " " +table.hour+" : "+table.min);
                    text_spa.setText(table.spa_num);
                    text_piz.setText(table.piz_num);
                    if(table.vip_membership == true) text_mem.setText("VIP 멤버쉽");
                    else text_mem.setText("기본 멤버쉽");
                    text_price.setText(table.total_price+"원");
                }
                table.putTableData(last_table,table) ;

            }
        });
    }

}
