package com.mupdf.adopter;

import java.util.ArrayList;
import java.util.List;










import com.mupdf.activities.R;
import com.mupdf.databases.DbHandler;
import com.mupdf.entity.BookMark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BookMarkList extends Activity {
	ListView listView ;
	private DbHandler db;
	private List<BookMark> bookMarks;
	String[] values;
	 @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_book_mark_list);
         db= new DbHandler(this);
         bookMarks = new ArrayList<BookMark>();
         bookMarks = db.getPages();
         // Get ListView object from xml
         listView = (ListView) findViewById(R.id.list);
         values = new String[bookMarks.size()];
         for(int i = 0; i< bookMarks.size(); i++){
        	 if(bookMarks.get(i).getPageId().equals("0"))
        	 {
        		 values[i] ="Title Page";//+bookMarks.get(i).getPageId();
        	 }
        	 else{
        		 values[i] ="Page No."+bookMarks.get(i).getPageId();
        	 }
 		}
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
           android.R.layout.simple_list_item_1, android.R.id.text1, values);
 
 
         // Assign adapter to ListView
         listView.setAdapter(adapter); 
         
         // ListView Item Click Listener
         listView.setOnItemClickListener(new OnItemClickListener() {
        	 
             @Override
             public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
               
              // ListView Clicked item index 
              int itemPosition     = position;
              
              // ListView Clicked item value
              String  itemValue    = (String) listView.getItemAtPosition(position);
              String digit;
               // Show Alert 
          
                 
               if(itemValue.equals("Title Page"))
               {
            	   digit= "0";   
               } else
               {
            	   digit= itemValue.replaceAll("[^0-9]", "");
              }
               Intent returnIntent = new Intent();
               returnIntent.putExtra("result",digit);
               
               setResult(RESULT_OK,returnIntent);
               finish();
            
             }
             

        }); 
	 }

 }