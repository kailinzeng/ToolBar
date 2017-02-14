package com.zeng.toolbar.app;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.zeng.toolbar.R;
import com.zeng.toolbar.adapter.MyFragmentAdapter;
import com.zeng.toolbar.fragment.AnnounceFragmentActivity;
import com.zeng.toolbar.fragment.HomeFragmentActivity;
import com.zeng.toolbar.fragment.QueryFragmentActivity;
import com.zeng.toolbar.fragment.SystemsettingFragmentActivity;
import com.zeng.toolbar.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/2 0002.
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TabActivity";
    public static final String []sTitle = new String[]{"主页","搜索","动态","设置"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        //判断API版本是否大于等于4.4，应用只需添加该段代码即可
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);// 将 Toolbar 的实例传入

        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);//让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.drawable.category);//设置导航按钮图标
        }



    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[0]).setIcon(R.drawable.home));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[1]).setIcon(R.drawable.query));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[2]).setIcon(R.drawable.announce));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[3]).setIcon(R.drawable.systemsetting));


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabSelected:"+tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragmentActivity.newInstance());
        fragments.add(QueryFragmentActivity.newInstance());
        fragments.add(AnnounceFragmentActivity.newInstance());
        fragments.add(SystemsettingFragmentActivity.newInstance());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(sTitle));
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG,"select page:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // 加载菜单
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // 设置点击事件
        switch (item.getItemId()){
            case R.id.backup:
                ToastUtils.showShort("click on the search");
                break;
            case R.id.delete:
                ToastUtils.showShort("click on the remind");
                break;

            case android.R.id.home:
                //mDrawerLayout.openDrawer(GravityCompat.START);//打开抽屉
                ToastUtils.showShort("click on home button");
                break;

        }
        return true;
    }
}
