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



        return v;
    }
    public void newOrder(final Table table,Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dlgview = View.inflate(getContext(), R.layout.order_dlg, null);
                final DatePicker datePicker = (DatePicker) dlgview.findViewById(R.id.datePicker);
                final TimePicker timePicker = (TimePicker) dlgview.findViewById(R.id.timePicker);
                final EditText editText_piz = (EditText) dlgview.findViewById(R.id.editText_piz);
                final EditText editText_spa = (EditText) dlgview.findViewById(R.id.editText_spa);
                final CheckBox checkBox_vip = (CheckBox)dlgview.findViewById(R.id.checkBox_vip);

                if (datePicker.isSelected() == true) {
                    datePicker.setVisibility(View.GONE);
                    timePicker.setVisibility(View.VISIBLE);
                }
                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setTitle("주문정보를 입력하세요")
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                table.putTable(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),timePicker.getHour(),timePicker.getMinute(),editText_spa.getText().toString(),editText_piz.getText().toString(), checkBox_vip.isChecked()  );
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

            }
        });
    }
    public void clickTable(final Table table, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //토스트 + 초기화
                if(table.is_void ==true) {
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
                    text_time.setText(table.year+table.month + table.day + " " +table.hour+" : "+table.min);
                    text_spa.setText(table.spa_num);
                    text_piz.setText(table.piz_num);
                    if(table.vip_membership == true) text_mem.setText("VIP 멤버쉽");
                    else text_mem.setText("기본 멤버쉽");
                    text_price.setText(table.total_price+"원");
                }
            }
        });
    }

}
