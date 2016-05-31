package com.cy.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cy.floatingwindow.FloatWindowService;

public class MainActivity2 extends Activity {

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
    /**{@link gen.Define_start}*/
    /**@see gen.Define_start*/
    //@see com.cy.testapp.gen
    //{@link com.cy.testapp.gen#}
    //genDefine_start
    private TextView mTvTitle;
    /**{@code mTvTitle = (TextView)findViewById(R.id.mTvTitle);<br/>
    mBtnTitle = (Button)findViewById(R.id.mBtnTitle);}*/
    private Button mBtnTitle;
    /**@see gen
    */
    private TextView mTvHW;
    /**@see gen.Tag*/
    /***/
    private Button mBtnHW;
    private View.OnClickListener mOnClickListener;
    //genDefine_end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.writeE("dsf");
        //genFindClick_start
        setContentView(R.layout.activity_main);
        mTvTitle = (TextView)findViewById(R.id.mTvTitle);
        mBtnTitle = (Button)findViewById(R.id.mBtnTitle);
        mTvHW = (TextView)findViewById(R.id.mTvHW);
        mBtnHW = (Button)findViewById(R.id.mBtnHW);

        mOnClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.mBtnTitle:
                        startService(new Intent(MainActivity2.this, FloatWindowService.class));
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


}
