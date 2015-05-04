package com.example.notepad;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	//设置菜单
	private static final int CONFIG_ID = Menu.FIRST;
	//类别菜单
	private static final int TYPE_ID = Menu.FIRST + 1;
	//关于菜单
	private static final int ABOUT_ID = Menu.FIRST + 2;
	
	private static final int INSERT_ID = Menu.FIRST+3;
	private static final int THOUGHTS_ID = Menu.FIRST + 4;
	private static final int EXIT_ID = Menu.FIRST + 5;
	
//	private static final int ACTIVITY_CREATE = 0;
	
	String mSelectDate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		TabHost tabHost = getTabHost();
		Intent listIntent = new Intent(this, DiaryList.class);		
		TabHost.TabSpec listSpec = tabHost.newTabSpec("DiaryList");
		Resources res = getResources();		
		listSpec.setIndicator("记事列表", res.getDrawable(R.drawable.dairy));		
		listSpec.setContent(listIntent);		
		tabHost.addTab(listSpec);
		Intent newIntent = new Intent(this, DiaryNew.class);
		TabHost.TabSpec newSpec = tabHost.newTabSpec("DiaryNew");
		newSpec.setIndicator("新建记事", res.getDrawable(R.drawable.icon_newnote));
		newSpec.setContent(newIntent);
		tabHost.addTab(newSpec);
		Intent canlendarIntent = new Intent(this, CalendarActivity.class);
		TabHost.TabSpec cldSpec = tabHost.newTabSpec("Calendar");
		cldSpec.setIndicator("日历", res.getDrawable(R.drawable.calendars));
		cldSpec.setContent(canlendarIntent);		
		tabHost.addTab(cldSpec);
		initTabs();

	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 1, R.string.menu_diary).setIcon(R.drawable.dairy);
		menu.add(0, THOUGHTS_ID, 2, R.string.menu_thoughts).setIcon(R.drawable.ic_launcher);
		menu.add(0, TYPE_ID, 3, R.string.menu_type).setIcon(R.drawable.ic_launcher);
		menu.add(0, CONFIG_ID, 4, R.string.menu_config).setIcon(R.drawable.setting);
		menu.add(0, ABOUT_ID, 5, R.string.menu_about).setIcon(R.drawable.ifo);
		menu.add(0, EXIT_ID, 6, R.string.menu_exit).setIcon(R.drawable.ic_launcher);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case TYPE_ID:
			Intent intent = new Intent(this,About.class);
			MainActivity.this.startActivity(intent);
			return true;
		case INSERT_ID:
			//createDiary();
			return true;
		case THOUGHTS_ID:
		//	createThoughts();
			return true;
		case ABOUT_ID:
			intent = new Intent(this,About.class);
			MainActivity.this.startActivity(intent);
			return true;
		case CONFIG_ID:
			intent = new Intent(this,About.class);
			MainActivity.this.startActivity(intent);
			return true;
		case EXIT_ID:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
		private void createDiary() {
			Intent i = new Intent(this, DiaryNew.class);
			TextView mSelectDateView = (TextView) getTabHost().getCurrentView().findViewById(R.id.select_date);
			if(mSelectDateView!=null)
			{
			  mSelectDate = mSelectDateView.getText().toString();
			  i.putExtra(DiaryAdapter.KEY_DATE, mSelectDate);
			}
			MainActivity.this.startActivity(i);
		}

		private void createThoughts() {
			Intent i = new Intent(this, About.class);
			TextView mSelectDateView = (TextView) getTabHost().getCurrentView().findViewById(R.id.select_date);
			if(mSelectDateView!=null)
			{
			  mSelectDate = mSelectDateView.getText().toString();
			  i.putExtra(DiaryAdapter.KEY_DATE, mSelectDate);
			}
			MainActivity.this.startActivity(i);
		}
	
		
	@SuppressLint("ResourceAsColor")
	private void initTabs() {
		final TabHost tabs = getTabHost();
		final TabWidget tabWidget = tabs.getTabWidget();

		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			View vvv = tabWidget.getChildAt(i);
			if (tabs.getCurrentTab() == i) {
				vvv.setBackgroundColor(R.color.hit_color);
				//vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower3));
			} else {
				vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.icons));
			}

		}


		tabs.setOnTabChangedListener(new OnTabChangeListener() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void onTabChanged(String tabId) {
				
				for (int i = 0; i < tabWidget.getChildCount(); i++) {
					View vvv = tabWidget.getChildAt(i);
					if (tabs.getCurrentTab() == i) {
						vvv.setBackgroundColor(R.color.hit_color);
						//vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower3));
					} else {
						vvv.setBackgroundDrawable(getResources().getDrawable(
								R.drawable.icons));
					}
				}
			}
		});

	}


}