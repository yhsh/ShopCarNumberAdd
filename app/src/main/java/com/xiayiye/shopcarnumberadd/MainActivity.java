package com.xiayiye.shopcarnumberadd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.xiayiye.shopcarnumberadd.view.CustomizeGoodsAddView;

public class MainActivity extends Activity {

    private CustomizeGoodsAddView customizeGoodsAddView;
    private TextView tvShowData;
    private int maxNumber = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customizeGoodsAddView = findViewById(R.id.customizeGoodsAddView);
        tvShowData = findViewById(R.id.tv_show_data);
        customizeGoodsAddView.setMaxValue(maxNumber);
        initData();
    }

    private void initData() {
        customizeGoodsAddView.setOnValueChangeListene(new CustomizeGoodsAddView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                if (value > maxNumber) {
                    customizeGoodsAddView.setValue(maxNumber);
                    tvShowData.setText("总计：" + maxNumber * 30 + "元");
                } else {
                    tvShowData.setText("总计：" + value * 30 + "元");
                }
            }
        });
    }
}
