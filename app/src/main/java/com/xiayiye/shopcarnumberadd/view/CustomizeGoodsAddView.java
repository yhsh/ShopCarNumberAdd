package com.xiayiye.shopcarnumberadd.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiayiye.shopcarnumberadd.R;


/**
 * @author Dell
 */
public class CustomizeGoodsAddView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;
    private EditText mCountET;
    private Context mContext;

    public CustomizeGoodsAddView(Context context) {
        super(context);
        init(context);
    }

    public CustomizeGoodsAddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = View.inflate(context, R.layout.number_adder_layout, this);
        TextView reduceIV = (TextView) view.findViewById(R.id.tv_reduce);
        mCountET = (EditText) view.findViewById(R.id.et_count);
        mCountET.addTextChangedListener(this);
        TextView addIV = (TextView) view.findViewById(R.id.tv_add);
        reduceIV.setOnClickListener(this);
        addIV.setOnClickListener(this);
        //设置默认值
        int value = getValue();
        setValue(value);
    }

    /**
     * 如果当前值大于最小值   减
     */
    private void reduce() {
        if (value > minValue) {
            value--;
        } else {
            showMaxNumber(mContext, "最小只能输入", minValue);
        }
        setValue(value);
        if (onValueChangeListene != null) {
            onValueChangeListene.onValueChange(value);
        }
    }

    /**
     * 如果当前值小于最小值  加
     */
    private void add() {
        if (value < maxValue) {
            value++;
        } else {
            showMaxNumber(mContext, "最大只能输入", maxValue);
        }
        setValue(value);
        if (onValueChangeListene != null) {
            onValueChangeListene.onValueChange(value);
        }
    }

    /**
     * 弹得吐司
     *
     * @param mContext 上下文
     * @param str      提示语
     * @param maxValue 最大值
     */
    private static void showMaxNumber(Context mContext, String str, int maxValue) {
        Toast.makeText(mContext, str + maxValue, Toast.LENGTH_SHORT).show();
    }

    //获取具体值
    public int getValue() {
        String countStr = mCountET.getText().toString().trim();
        if (!TextUtils.isEmpty(countStr)) {
            value = Integer.valueOf(countStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        mCountET.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_reduce) {
            //减
            reduce();
        } else if (i == R.id.tv_add) {
            //加
            add();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        if (TextUtils.isEmpty(text)) {
            onValueChangeListene.onValueChange(0);
            value = 0;
            return;
        } else {
            int count = Integer.parseInt(text);
            if (count == 0) {
                Toast.makeText(mContext, "您输入的数量超过最大限制,请重新输入", Toast.LENGTH_SHORT).show();
                onValueChangeListene.onValueChange(0);
            } else if (count > maxValue) {
                mCountET.setText(maxValue + "");
                showMaxNumber(mContext, "最大只能输入", maxValue);
            }
        }
        mCountET.setSelection(mCountET.getText().toString().trim().length());
        if (onValueChangeListene != null) {
            onValueChangeListene.onValueChange(Integer.valueOf(text));
            value = Integer.valueOf(text);
        }
    }

    //监听回调
    public interface OnValueChangeListener {
        void onValueChange(int value);
    }

    private OnValueChangeListener onValueChangeListene;

    public void setOnValueChangeListene(OnValueChangeListener onValueChangeListene) {
        this.onValueChangeListene = onValueChangeListene;
    }

}
