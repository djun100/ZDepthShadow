package com.cy.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cy.host.ActivityBaseHost;
public class MainActivity extends ActivityBaseHost {

    /**@see gen */
    /**@see gen.Tag*/
    /**{@link gen.Tag}*/
    /**{@link gen.TagStatic}*/
    /**{@link #ALLOCATED_REQUEST_INDICIES_TAG}*/
    /**{@link gen#tag_defind_start}*/
    /**{@link gen#tag_define_start()}*/
    /**@see gen#tag_define_start() */
    /**{@linkplain gen}*/
    /**{@literal }*/
    /**{@link gen.Define_start}*/
    /**{@link com.cy.testapp.gen.Define_start}*/
    /**@see com.cy.testapp.gen.Define_start*/
    //genDefine_start
    private TextView mTvTitle;
    /**{@code mTvTitle = (TextView)findViewById(R.id.mTvTitle);<br/>
    mBtnTitle = (Button)findViewById(R.id.mBtnTitle);}*/
    private Button mBtnTitle;
    /**@see com.cy.testapp.gen.Define_start
    */
    private TextView mTvHW;

    private View.OnClickListener mOnClickListener;
    //genDefine_end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("");
        //genFindClick_start
        mTvTitle = (TextView)findViewById(R.id.mTvTitle);
        mBtnTitle = (Button)findViewById(R.id.mBtnTitle);
        mTvHW = (TextView)findViewById(R.id.mTvHW);
        mBtnHW = (Button)findViewById(R.id.mBtnHW);

        mOnClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.mBtnTitle:
                    break;
                    case R.id.mBtnHW:
                    break;
                }
            }
        };
        mBtnTitle.setOnClickListener(mOnClickListener);
        mBtnHW.setOnClickListener(mOnClickListener);
        //genFindClick_end
    }

    @Override
    protected void init1_data() {

    }

    @Override
    protected int init2_rootViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init3_view() {
        Log.writeE("sdfsd");
    }
}
