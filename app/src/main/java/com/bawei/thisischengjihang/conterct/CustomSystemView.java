package com.bawei.thisischengjihang.conterct;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomSystemView extends EditText {

    Handler mHandler = new Handler();
    String str;

    public CustomSystemView(Context context) {
        super(context);
        init();
    }

    public CustomSystemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                str = s.toString();
                mHandler.removeCallbacks(runnable);
                mHandler.postDelayed(runnable, 1000);
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(mOnTextChanged != null){
                mOnTextChanged.onChanged(str);
            }
        }
    };


    private OnTextChanged mOnTextChanged;
    public void setOnTextChanged(OnTextChanged onTextChanged){
        mOnTextChanged = onTextChanged;
    }

    public interface OnTextChanged{
        void onChanged(String str);
    }

}
