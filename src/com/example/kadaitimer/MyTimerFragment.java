package com.example.kadaitimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyTimerFragment extends Fragment {
	Timer mTimer;
	Handler mHandler = new Handler();
	TextView mTextView;
	long mStartTime;

	public MyTimerFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		// ウィジェット取得
		mTextView = (TextView)view.findViewById(R.id.textView1);
		
		// タイマー開始時刻を記録
		mStartTime = SystemClock.elapsedRealtime(); // デバイスが起動してからの経過ミリ秒 (※System.currentTimeMillis … 現在時刻)
				
		// タイマー開始
		mTimer = new Timer(true); // false:ユーザスレッド / true:デーモンスレッド
		mTimer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						long total = SystemClock.elapsedRealtime() - mStartTime;
						SimpleDateFormat f = new SimpleDateFormat("mm:ss.SSS");
						String s = f.format(new Date(total));
						mTextView.setText(s);
					}
				});
			}
		}, 0, 50);	// ※schedule: 前回のタスクが完了してからの時間を指定。
					// ※scheduleAtFixedRate: 前回のタスクの完了時間に関わらず一定の周期で実行。 
		
		// ボタン処理
		Button button = (Button)view.findViewById(R.id.buttonStopDelete);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mTimer != null){
					mTimer.cancel();
					mTimer = null;
				}
				else{
					// 自分自身を破棄
					MyTimerFragment.this.getFragmentManager().beginTransaction().remove(MyTimerFragment.this).commit();
				}
			}
		});
		
	
	}
}
