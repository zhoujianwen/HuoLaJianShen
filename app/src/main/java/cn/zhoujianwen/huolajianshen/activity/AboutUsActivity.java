package cn.zhoujianwen.huolajianshen.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import cn.zhoujianwen.huolajianshen.R;
import cn.zhoujianwen.huolajianshen.base.BaseActivity;
import cn.zhoujianwen.huolajianshen.utils.DrawerToolUtils;
import cn.zhoujianwen.huolajianshen.utils.SystemUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_about_author)
    TextView tvAboutAuthor;
    @Bind(R.id.tv_project_pager)
    TextView tvProjectPager;
    @Bind(R.id.tv_project_describe)
    TextView tvProjectDescribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        DrawerToolUtils.initToolbar(this, toolbar, "火辣健身");
        tvVersion.setText(SystemUtils.getVersionName());
        tvAboutAuthor.setText("https://github.com/zhoujianwen \nzhoujianwenai@foxmail.com");
        tvProjectPager.setText("https://github.com/zhoujianwen/HuaLaJianShen");
        tvProjectDescribe.setText("团队成员：周健文、赵敏璇、陈立宜");
    }
}
