package com.zeng.toolbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeng.toolbar.detail.Partner;
import com.zeng.toolbar.R;
import com.zeng.toolbar.adapter.PartnerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/2 0002.
 */
public class HomeFragmentActivity extends Fragment {

    private SwipeRefreshLayout swipeRefresh;// 刷新
    private RecyclerView recyclerView;
    private TabLayout mTabLayout;
    private List<Partner> partnerList = new ArrayList<>();
    private PartnerAdapter adapter;
    private Partner[] partners =
            {
                    new Partner("路飞",R.mipmap.partner_luffy,R.string.partner_luffy),
                    new Partner("索隆",R.mipmap.partner_zoro,R.string.partner_zoro),
                    new Partner("山治",R.mipmap.partner_sanji,R.string.partner_sanji),
                    new Partner("艾斯",R.mipmap.partner_ace,R.string.partner_ace),
                    new Partner("罗",R.mipmap.partner_law,R.string.partner_law),
                    new Partner("娜美",R.mipmap.partner_nami,R.string.partner_nami),
                    new Partner("罗宾",R.mipmap.partner_robin,R.string.partner_robin),
                    new Partner("薇薇",R.mipmap.partner_vivi,R.string.partner_vivi),
                    new Partner("蕾贝卡",R.mipmap.partner_rebecca,R.string.partner_rebecca),
                    new Partner("汉库克",R.mipmap.partner_hancock,R.string.partner_hancock),

                    new Partner("佐鼬",R.mipmap.partner_1,R.string.no_describe),
                    new Partner("带土",R.mipmap.partner_2,R.string.no_describe),
                    new Partner("带土",R.mipmap.partner_3,R.string.no_describe),
                    new Partner("金木",R.mipmap.partner_4,R.string.no_describe),
                    new Partner("雨路",R.mipmap.partner_5,R.string.no_describe),
                    new Partner("杯子",R.mipmap.partner_6,R.string.no_describe),
                    new Partner("路飞",R.mipmap.partner_7,R.string.no_describe),
                    new Partner("雏田",R.mipmap.partner_8,R.string.no_describe),
                    new Partner("带土",R.mipmap.partner_9,R.string.no_describe),
                    new Partner("代码咖啡",R.mipmap.partner_10,R.string.no_describe),
                    new Partner("龙珠",R.mipmap.partner_11,R.string.no_describe),
                    new Partner("宇智波",R.mipmap.partner_12,R.string.no_describe),
                    new Partner("佐助",R.mipmap.partner_13,R.string.no_describe),
                    new Partner("带土",R.mipmap.partner_14,R.string.no_describe),
                    new Partner("斑",R.mipmap.partner_15,R.string.no_describe),
                    new Partner("木板",R.mipmap.partner_16,R.string.no_describe)

            };



    public static Fragment newInstance(){
        HomeFragmentActivity fragment = new HomeFragmentActivity();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_home_fragment,null);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_one_piece);
        initPartner();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PartnerAdapter(partnerList);
        recyclerView.setAdapter(adapter);



        // 下拉刷新
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);//设置刷新进度条颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 处理刷新逻辑
                refreshPartner();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //ToastUtils.showShort("click on suspend button");
                //Intent intent = new Intent();
                //intent.setClass(MaterialDesignActivity.this,TestActivity.class);
                //MaterialDesignActivity.this.startActivity(intent);
                //IntentUtils.myIntent(MaterialDesignActivity.this, TestActivity.class);
                refreshPartner();
            }
        });



        return view;
    }

    /**
     * 初始化数据，随机挑选50条数据
     */

     private void initPartner()
     {
         partnerList.clear();
         for (int i = 0;i < 50 ;i++)
         {
             Random random = new Random();
             int index = random.nextInt(partners.length);
             partnerList.add(partners[index]);
         }
     }



    /**
     * 下拉刷新数据（为简单起见没和网络交互）
     */

    private void refreshPartner()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        initPartner();//重新生成数据
                        adapter.notifyDataSetChanged();//通知数据变化
                        swipeRefresh.setRefreshing(false);//隐藏刷新进度条
                    }
                });
            }
        }).start();
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**和activity的生命周期类似，
     * 你需要在fragment中的onPause()保存一些数据（比如写入数据库）。
     * 调用onPause时，fragment可能进入后台不可见，
     * 也可能被销毁destroy
     * */
    public void onPause()
    {
        super.onPause();;
    }
}


