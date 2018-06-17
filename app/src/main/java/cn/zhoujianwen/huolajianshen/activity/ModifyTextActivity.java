package cn.zhoujianwen.huolajianshen.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;

public class ModifyTextActivity extends BaseActivity {

    @Bind(cn.zhoujianwen.huolajianshen.R.id.toolbar)
    Toolbar toolbar;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.et_modify_text)
    EditText etModifyText;
    @Bind(cn.zhoujianwen.huolajianshen.R.id.tv_modify_complete)
    TextView tvModifyComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.zhoujianwen.huolajianshen.R.layout.activity_modify_text);
        ButterKnife.bind(this);
        initView();
        initListener();


    }


    private void initView() {
        initToolbar();

    }

    private void initListener() {
        tvModifyComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modifyResult = etModifyText.getText().toString().trim();
                getIntent().putExtra("modifyResult", modifyResult);
                setResult(RESULT_OK,getIntent());
                finish();
            }
        });
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(cn.zhoujianwen.huolajianshen.R.id.toolbar);
        toolbar.setTitle("填写备注");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
