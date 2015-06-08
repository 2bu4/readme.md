package com.example.notepad;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TypeActivity extends Activity {
	
	private TypeAdapter dbUtil;
	private ListView myListView;
	private Cursor eventTypesCursor ;
	private EditText editText;
	private int _id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_type);
		setBtnListener();
		fillData();
	}
	
	private void fillData() {
		dbUtil = new TypeAdapter(this);
		dbUtil.open();
		eventTypesCursor = dbUtil.fetchAllEventTypes();
		startManagingCursor(eventTypesCursor);

		String[] from = new String[] { TypeAdapter.KEY_NAME };
		myListView = (ListView) this.findViewById(R.id.typeListView);
		editText = (EditText)findViewById(R.id.editType);
		
		/* ��eventTypesCursor���� ��ʾ��ݵ��ֶ�ΪKEY_NAME */
	    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.typeitem, eventTypesCursor, from , new int[]
	        { R.id.listTextView1 });
	    myListView.setAdapter(adapter);

	    /* ��myListView���OnItemClickListener */
	    myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
	    {
          public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	      {
	        /* ��myCursor�Ƶ�������ֵ */
	    	eventTypesCursor.moveToPosition(arg2);
	        /* ȡ���ֶ�_id��ֵ */
	        _id = eventTypesCursor.getInt(0);
	        editText.setText(eventTypesCursor.getString(1));
	      }

	    });
	    myListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {
	      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	      {
	        /* getSelectedItem��ȡ�õ���SQLiteCursor */
	        SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();
	        _id = sc.getInt(0);
	        editText.setText(sc.getString(1));
	      }
	      
	      public void onNothingSelected(AdapterView<?> arg0)
	      {
	    	  _id = 0;
	    	  editText.setText("");
	      }
	    });
	}
	
	private void setBtnListener()
	{
		editText = (EditText)findViewById(R.id.editType);
		Button saveBtn = (Button)findViewById(R.id.savebtn);
		saveBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = editText.getText().toString();
				if (name.equals(""))
				{
			         TimeDiary.dailog("�¼����Ͳ���Ϊ�գ�",TypeActivity.this);
					 return;
				}else if(isNameUsed(name))
				{
					TimeDiary.dailog("��Ʋ����ظ����뻻һ����",TypeActivity.this);
					 return;
				}else if(_id == 0)
				{
				    /* �����ݵ���ݿ� */
				    long id = dbUtil.createEventType(name);
                    
				}else{
					dbUtil.updateEventType(_id, name);
				}
				 /* ���²�ѯ */
			    eventTypesCursor.requery();
			    /* ��������myListView */
			    myListView.invalidateViews();
			    editText.setText("");
			    _id = 0;
			}
		});
		Button delBtn = (Button)findViewById(R.id.delbtn);
		delBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 if (_id == 0)
				 {
				      return;
				 }
				 if(isUsed(_id)) {
					 TimeDiary.dailog("�¼������Ѿ���ʹ�ã�����ɾ��",TypeActivity.this);
					 return;
				}
				    /* ɾ����� */
				dbUtil.deleteEventType(_id);
				eventTypesCursor.requery();
			    myListView.invalidateViews();
			    editText.setText("");
			    _id = 0;
			}
		});
		Button cancelBtn = (Button)findViewById(R.id.cancelbtn);
		cancelBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
				Intent intent = new Intent(TypeActivity.this,MainActivity.class);
				TypeActivity.this.startActivity(intent);
			}
			
		});
	}
	
	private boolean isUsed(long id) {
        Cursor mCursor = getContentResolver().query(
				TimeDiary.DIARY_ITEM_CONTENT_URI,
				new String[] { "event_type" }, "event_type = ?",
				new String[] { id + "" }, null);
		if (mCursor.getCount() > 0) {
			return true;
		}
		return false;
	}
	
	private boolean isNameUsed(String name) {
        Cursor mCursor = getContentResolver().query(
				TimeDiary.EVENT_TYPE_CONTENT_URI,
				new String[] { TypeAdapter.KEY_ROWID }, "name = ?",
				new String[] { name }, null);
		if (mCursor.getCount() > 0) {
			return true;
		}
		return false;
	}
	

}
