package com.example.calender;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText edtDateOne, edtDateTwo;
    Button btnTinhNgay;
    TextView txtNgayXa;
    Calendar calendarOne,calendarTwo;
    int ngayXaNhau;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDateOne = (EditText) findViewById(R.id.editTextDateOne);
        edtDateTwo = (EditText) findViewById(R.id.editTextDateTwo);
        btnTinhNgay = (Button) findViewById(R.id.buttonTinhNgay);
        txtNgayXa = (TextView) findViewById(R.id.textViewNgayXa);


        edtDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgayOne();
            }
        });

        edtDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgayTwo();
            }
        });

        btnTinhNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngayOne = edtDateOne.getText().toString().trim();
                String ngayTwo = edtDateTwo.getText().toString().trim();

                if (ngayOne.equals("") || ngayTwo.equals("")){
                    Toast.makeText(MainActivity.this, "Bạn chưa chọn ngày", Toast.LENGTH_SHORT).show();
                }else{
                    ngayXaNhau = (int) ((calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis()) / (1000*60*60*24));
                    if (ngayXaNhau < 0){
                        Toast.makeText(MainActivity.this, "Ngày thứ hai phải lớn hơn ngày thứ nhất", Toast.LENGTH_SHORT).show();
                    }else{
                        txtNgayXa.setText("Khoảng cách ngày: "+ngayXaNhau+"");
                    }
                }
            }
        });
    }
    private void ChonNgayOne(){
        //tao bien de lay ngay hien tai
        calendarOne= Calendar.getInstance();
        int ngay = calendarOne.get(Calendar.DATE);
        int thang = calendarOne.get(Calendar.MONTH);
        int nam = calendarOne.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarOne.set(year,month,dayOfMonth);
                edtDateOne.setText(simpleDateFormat.format(calendarOne.getTime()));

            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    private void ChonNgayTwo(){
        calendarTwo= Calendar.getInstance();
        int ngay = calendarTwo.get(Calendar.DATE);
        int thang = calendarTwo.get(Calendar.MONTH);
        int nam = calendarTwo.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarTwo.set(year,month,dayOfMonth);
                edtDateTwo.setText(simpleDateFormat.format(calendarTwo.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}
