package com.demo.adddeleteactivity;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GuidePageAdapter extends PagerAdapter {

    private List<View> viewList;

    public GuidePageAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    /**
     * @return ����ҳ��ĸ���
     */
    @Override
    public int getCount() {
        if (viewList != null){
            return viewList.size();
        }
        return 0;
    }

    /**
     * �ж϶����Ƿ����ɽ���
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * ��ʼ��positionλ�õĽ���
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}
