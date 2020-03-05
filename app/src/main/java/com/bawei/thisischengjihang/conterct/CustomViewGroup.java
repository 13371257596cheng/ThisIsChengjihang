package com.bawei.thisischengjihang.conterct;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bawei.thisischengjihang.R;

public class CustomViewGroup extends LinearLayout {
    private CustomSystemView mEt;
    private ImageButton mIb;

    public CustomViewGroup(Context context) {
        super(context);
        init(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 通过 View.inflate 加载布局文件
     * @param context
     */
    private void init(Context context){
        View view = View.inflate(context, R.layout.viewgroup_search, null);
        mEt = view.findViewById(R.id.et_search);
        mIb = view.findViewById(R.id.ib_search);

        mIb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnSearchClick != null){
                    mOnSearchClick.onSearch(mEt.getText().toString());
                }
            }
        });

//        mEt.setOnTextChanged(new CustomSystemView.OnTextChanged() {
//            @Override
//            public void onChanged(String str) {
//                if(mOnSearchClick != null){
//                    mOnSearchClick.onSearch(str);
//                }
//            }
//        });

        addView(view);
    }

    private OnSearchClick mOnSearchClick;
    public void setOnSearchClick(OnSearchClick onSearchClick){
        mOnSearchClick = onSearchClick;
    }


    public interface OnSearchClick{
        void onSearch(String str);
    }
}
