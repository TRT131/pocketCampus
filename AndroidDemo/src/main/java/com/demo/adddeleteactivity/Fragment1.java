package com.demo.adddeleteactivity;

import com.demo.adddeleteactivity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * δ�õ� ��Ҫ�Զ�������ҳʱʹ��
 * @author Administrator
 *
 */
public class Fragment1 extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.item_frament1, container,false);
		return inflate;
	}
}
