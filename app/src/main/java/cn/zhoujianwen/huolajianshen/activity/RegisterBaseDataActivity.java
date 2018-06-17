package cn.zhoujianwen.huolajianshen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;
import cn.zhoujianwen.huolajianshen.event.ExitRegisterEvent;
import cn.zhoujianwen.huolajianshen.fragment.BodyDataFragment;
import cn.zhoujianwen.huolajianshen.utils.DrawerToolUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterBaseDataActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_register_describe)
    TextView tvRegisterDescribe;
    @Bind(R.id.tv_register_base_next)
    TextView tvRegisterBaseNext;
    private BodyDataFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_base_data);
        ButterKnife.bind(this);

        initView();
        initListener();
    }


    private void initView() {
        DrawerToolUtils.initToolbar(this, toolbar, "个人数据");
        fragment = (BodyDataFragment) getSupportFragmentManager().findFragmentById(R.id.fm_register_base_data);

        EventBus.getDefault().register(RegisterBaseDataActivity.this);
    }


    private void initListener() {

        tvRegisterBaseNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyData();

            }
        });

    }


    private void verifyData() {
        String birthday = fragment.tvRegisterBirthday.getText().toString().trim();
        String high = fragment.tvRegisterHigh.getText().toString().trim();
        String weight = fragment.tvRegisterWeight.getText().toString().trim();

        if (TextUtils.isEmpty(birthday)) {
            fragment.tvRegisterBirthday.setText("还没有填写生日");
            return;
        }
        if (TextUtils.isEmpty(high)) {
            fragment.tvRegisterHigh.setText("还没有填写身高");
            return;
        }
        if (TextUtils.isEmpty(weight)) {
            fragment.tvRegisterWeight.setText("还没有填写体重");
            return;
        }
        submitData();
    }


    private void submitData() {


        Intent intent = new Intent(RegisterBaseDataActivity.this, RegisterActionActivity.class);
        intent.putExtra("bodyData", (Serializable) fragment.bodyData);
        intent.putExtra("isRegister", true);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Subscribe
    public void onEventMainThread(ExitRegisterEvent event) {
        if (event.isExit()) {
            finish();
        }
    }

}
