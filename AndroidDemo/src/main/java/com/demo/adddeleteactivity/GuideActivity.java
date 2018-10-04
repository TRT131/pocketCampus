package com.demo.adddeleteactivity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.adddeleteactivity.R;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{

    private ViewPager vp;
    private int []imageIdArray;//鍥剧墖璧勬簮鐨勬暟缁�
    private List<View> viewList;//鍥剧墖璧勬簮鐨勯泦鍚�
    private ViewGroup vg;//鏀剧疆鍦嗙偣

    //瀹炰緥鍖栧師鐐筕iew
    private ImageView iv_point;
    private ImageView []ivPointArray;

    //鏈�鍚庝竴椤电殑鎸夐挳
    private ImageButton ib_start;
    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        ib_start = (ImageButton) findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });

        //鍔犺浇ViewPager
        initViewPager();

        //鍔犺浇搴曢儴鍦嗙偣
        initPoint();
        
    }
   
    /**
     * 鍔犺浇搴曢儴鍦嗙偣
     */
    private void initPoint() {
        //杩欓噷瀹炰緥鍖朙inearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //鏍规嵁ViewPager鐨刬tem鏁伴噺瀹炰緥鍖栨暟缁�
        ivPointArray = new ImageView[viewList.size()];
        //寰幆鏂板缓搴曢儴鍦嗙偣ImageView锛屽皢鐢熸垚鐨処mageView淇濆瓨鍒版暟缁勪腑
        int size = viewList.size();
        for (int i = 0;i<size;i++){
            iv_point = new ImageView(this);
            layoutParams = new LinearLayout.LayoutParams(15,15);

            //绗竴涓〉闈㈤渶瑕佽缃负閫変腑鐘舵�侊紝杩欓噷閲囩敤涓ゅ紶涓嶅悓鐨勫浘鐗�
            if (i == 0){
                iv_point.setBackgroundResource(R.drawable.shape_bg_point_enable);
            }else{
                layoutParams.leftMargin=20;
                iv_point.setBackgroundResource(R.drawable.shape_bg_point_disable);
            }
            iv_point.setLayoutParams(layoutParams);
            iv_point.setPadding(30,0,30,0);//left,top,right,bottom
            ivPointArray[i] = iv_point;

            //灏嗘暟缁勪腑鐨処mageView鍔犲叆鍒癡iewGroup
            vg.addView(ivPointArray[i]);
        }
    }

    /**
     * 鍔犺浇鍥剧墖ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //瀹炰緥鍖栧浘鐗囪祫婧�
        imageIdArray = new int[]{R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};
        viewList = new ArrayList();
        //鑾峰彇涓�涓狶ayout鍙傛暟锛岃缃负鍏ㄥ睆
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        //寰幆鍒涘缓View骞跺姞鍏ュ埌闆嗗悎涓�
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){
            //new ImageView骞惰缃叏灞忓拰鍥剧墖璧勬簮
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //灏咺mageView鍔犲叆鍒伴泦鍚堜腑
            viewList.add(imageView);
        }

        //View闆嗗悎鍒濆鍖栧ソ鍚庯紝璁剧疆Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //璁剧疆婊戝姩鐩戝惉
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 婊戝姩鍚庣殑鐩戝惉
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //寰幆璁剧疆褰撳墠椤电殑鏍囪鍥�
        int length = imageIdArray.length;
        for (int i = 0;i<length;i++){
            ivPointArray[position].setBackgroundResource(R.drawable.shape_bg_point_enable);
            if (position != i){
                ivPointArray[i].setBackgroundResource(R.drawable.shape_bg_point_disable);
            }
        }

        //鍒ゆ柇鏄惁鏄渶鍚庝竴椤碉紝鑻ユ槸鍒欐樉绀烘寜閽�
        if (position == imageIdArray.length - 1){
            ib_start.setVisibility(View.VISIBLE);
        }else {
            ib_start.setVisibility(View.GONE);
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
//    class FrgmenAdapter extends FragmentPagerAdapter{
//
//		private List<Fragment> list;
//		private FragmentManager fm;
//
//		public FrgmenAdapter(FragmentManager fm) {
//			super(fm);
//			// TODO Auto-generated constructor stub
//		}
//		public FrgmenAdapter(FragmentManager fm,List<Fragment> list) {
//			super(fm);
//			this.list=list;
//			this.fm=fm;
//			// TODO Auto-generated constructor stub
//		}
//
//		@Override
//		public android.support.v4.app.Fragment getItem(int arg0) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public int getCount() {
//			if (list!=null) {
//				return list.size();
//			}else{
//				return 0;
//			}
//		}
//    	
//    }
}