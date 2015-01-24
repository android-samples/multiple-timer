package com.example.multi_timer;

import com.example.multi_timer.R;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		*/
		
		LinearLayout container = (LinearLayout)findViewById(R.id.container);
		container.setLayoutTransition(new LayoutTransition());
		
	}
	
	public void buttonMethodAdd(View v){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction t = fm.beginTransaction();
		t.add(R.id.container, new MyTimerFragment());
		t.commit();
	}
	
}
