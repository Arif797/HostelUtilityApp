package com.code_base_update.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.code_base_update.beans.DashBoardBean;
import com.code_base_update.interfaces.OnItemClickListener;
import com.code_base_update.models.DashboardModel;
import com.code_base_update.presenters.IDashPresenter;
import com.code_base_update.ui.adapters.DashboardRecyclerAdapter;
import com.code_base_update.view.IDashView;
import com.medeveloper.ayaz.hostelutility.AboutSection;
import com.medeveloper.ayaz.hostelutility.R;

import java.util.ArrayList;

public class Dashboard extends BaseRecyclerActivity<IDashView,IDashPresenter, DashboardRecyclerAdapter> implements IDashView{

    private ArrayList<DashBoardBean> list;

    @Override
    protected IDashPresenter createPresenter() {
        return new DashboardModel();
    }

    @Override
    RecyclerView getRecyclerView() {
        return (RecyclerView)getView(R.id.recycler_view);
    }

    @Override
    DashboardRecyclerAdapter getAdapter() {
        return new DashboardRecyclerAdapter(this,R.layout.new_dashboard_cardui,list);
    }

    @Override
    void initViews() {
        list = new ArrayList<>();
        mPresenter.loadData();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:openRegisterComplaint();break;

                    case 1:openRegisterComplaintList();break;

                    case 2:openRegisterApplication();break;

                    case 3:openRegisterApplicationList();break;
                }

            }
        });
        mPresenter.loadUserImageUrl();
        mPresenter.loadUserName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.new_activity_dashboard;
    }

    @Override
    public void onDataLoaded(ArrayList<DashBoardBean> list) {
        this.list = list;
        adapter.update(list);
    }

    @Override
    public void openRegisterComplaint() {
        startActivity(new Intent(this,ComplaintActivity.class));
    }

    @Override
    public void openRegisterComplaintList() {
        startActivity(new Intent(this, ComplaintComplaintListActivity.class));
    }

    @Override
    public void openRegisterApplication() {
        startActivity(new Intent(this,RegisterApplicationActivity.class));
    }

    @Override
    public void openRegisterApplicationList() {
        startActivity(new Intent(this,ApplicationListActivity.class));
    }

    @Override
    public void  onDisplayImageLoaded(Uri imageUrl) {
        if(imageUrl!=null)
            setImageUrl(R.id.iv_display_image,imageUrl.toString(),R.drawable.man,new CircleCrop());
        else
            setImageUrl(R.id.iv_display_image,"",R.drawable.man,new CircleCrop());
    }

    @Override
    public void userNameLoaded(String name) {
        if(name!=null&&name.length()>0)
            setText(R.id.tv_welcome,name);
        else
            setText(R.id.tv_welcome,"No name");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:startActivity(new Intent(this, AboutSection.class));break;
            case R.id.setting:break;

        }
        return super.onOptionsItemSelected(item);
    }
}