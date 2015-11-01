package info.androidhive.slidingmenu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Profile extends Fragment 
{
	Thread ther;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    EditText text1,text2,text3,text4,text5,text6,text7;
    View rootView;
	public Profile(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
				//System.out.println("check0");
				rootView = inflater.inflate(R.layout.profile , container, false); 
				
					 ther  = new Thread(new Runnable() {public void run() 
		        		{
		                        profile();                          
		                }});
		        ther.start();
				 try {
					ther.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 //ther.destroy();
        return rootView;
	}

	void profile()
	{
		String result = null;
    	InputStream is = null;
    	text1 = (EditText)rootView.findViewById(R.id.editText1);
    	text2 = (EditText)rootView.findViewById(R.id.EditText01);
    	text3 = (EditText)rootView.findViewById(R.id.EditText02);
    	text4 = (EditText)rootView.findViewById(R.id.EditText05);
    	
    	text5 = (EditText)rootView.findViewById(R.id.EditText04);
    	text6 = (EditText)rootView.findViewById(R.id.EditText03);
    	
    	text7 = (EditText)rootView.findViewById(R.id.EditText06);
    	String v1=MainActivity.name;
    	//System.out.println(v1);
    	//System.out.println("check01");
        
    	try
       	{
    		//System.out.println("check2");
    	    httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://designghar.com/flm/profile.php"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>();

        	nameValuePairs.add(new BasicNameValuePair("username",v1));
        	//System.out.println("check21");
    	    
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            //System.out.println("check22");
    	    response=httpclient.execute(httppost);
    	    //System.out.println("check2312");
            HttpEntity entity = response.getEntity();
            //System.out.println("check23");
            is = entity.getContent();
	        //System.out.println("check24");
    	        
            Log.e("log_tag", "connection success ");
            //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
       	}
    	catch(Exception e)
       	{
    	   System.out.println("Connection fail");
    	   		Log.e("log_tag", "Error in http connection "+e.toString());
    	        //Toast.makeText(getActivity(), "Connection fail", Toast.LENGTH_SHORT).show();

       	}
    	//convert response to string
       	try{
    	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
    	        StringBuilder sb = new StringBuilder();
    	        String line = null;
    	        while ((line = reader.readLine()) != null) 
    	        {
    	                sb.append(line + "\n");
    	              //  Toast.makeText(getApplicationContext(), "Input Reading pass", Toast.LENGTH_SHORT).show();
    	        }
    	        is.close();
    	      //  System.out.println("check 3");
    	        
    	        result=sb.toString();
    	       // System.out.println(result);
    	        
       	}
       	catch(Exception e)
       	{
    	       Log.e("log_tag", "Error converting result "+e.toString());
    	       	System.out.println("Input reading fail");
    	        //Toast.makeText(getActivity(), " Input reading fail", Toast.LENGTH_SHORT).show();

       	}

    	//parse json data
    	try{
    			JSONObject object = new JSONObject(result);
    			String ch=object.getString("re");
    			//System.out.println("check 4");
    			if(ch.equals("success"))
    			{  
    				JSONObject no = object.getJSONObject("0");
    				//System.out.println("check 5");
    				//long q=object.getLong("f1");
    				String w= no.getString("0");
    				System.out.println(w);
    				//editText1.setText(w);
    				//w= no.optString("Name");
    				text1.setText(w);
    				System.out.println(w);
    				w= no.optString("Name");
    				text2.setText(w);
    				System.out.println(w);
    				w= no.optString("leave1");
    				text3.setText(w);
    				System.out.println(w);
    				w= no.optString("leave2");
    				text4.setText(w);
    				System.out.println(w);
    				w= no.optString("leave3");
    				text5.setText(w);
    				System.out.println(w);
    				w= no.optString("leave4");
    				text6.setText(w);
    				System.out.println(w);
    				w= no.optString("leave5");
    				text7.setText(w);
    				System.out.println(w);
    				//editText1.setText(w);
    				/*     
            			w= no.getString("0");
               			editText1.setText(w);
            			w= no.getString("0");
            			editText1.setText(w); 
            			w= no.getString("0");
               			editText1.setText(w);
            			long e=no.getLong("f3");
              			String myString = NumberFormat.getInstance().format(e);
						editText2.setText(myString);
    				 */ 
	            }
    			else
    			{
    				System.out.println("Record is not available.. Enter valid number");
    				//Toast.makeText(getActivity(), "Record is not available.. Enter valid number", Toast.LENGTH_SHORT).show();
    			}
    		}	
    		catch(JSONException e)
    		{
    			Log.e("log_tag", "Error parsing data "+e.toString());
    	        System.out.println("Record is not available.. Enter valid number");
    	        //Toast.makeText(getActivity(), "JsonArray fail", Toast.LENGTH_SHORT).show();
    		}
	}
}
