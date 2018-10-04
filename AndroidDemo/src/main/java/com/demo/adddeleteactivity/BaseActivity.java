package com.demo.adddeleteactivity;

import android.app.Activity;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this);
    }
}
