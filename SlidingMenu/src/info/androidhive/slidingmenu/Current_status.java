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
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Current_status extends Fragment 
{
	Thread ther1;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    View rootView1;
    EditText text11;
    EditText text12;
    EditText text13;
    EditText text14;
	public Current_status(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		System.out.println("check0");
		rootView1= inflater.inflate(R.layout.leave_status, container, false);

    	text11 = (EditText) rootView1.findViewById(R.id.EditText14);
    	text12= (EditText) rootView1.findViewById(R.id.EditText11);
        text13= (EditText) rootView1.findViewById(R.id.EditText12);
        text14= (EditText) rootView1.findViewById(R.id.EditText13);
        
    	new GetData().execute();
       /* ther1  = new Thread(new Runnable() {public void run() 
		{
        	System.out.println("check1");
               // status(); 
        	
        }});
        ther1.start();
        try {
        	ther1.join();
        } 
        catch (InterruptedException e) {
        	// TODO Auto-generated catch block
        	System.out.println("My status want");
        	e.printStackTrace();
        }
        */return rootView1;
    }
	
	//
	private class GetData extends AsyncTask<String, Void, JSONObject> 
	{
        @Override
        protected void onPreExecute() {
        	super.onPreExecute();

        	System.out.println("check0001");
        	
            
    		System.out.println("check01");
        	
            /*
            progressDialog = ProgressDialog.show(Calendar.this,
                    "", "");
*/
        }

        @Override
        protected JSONObject doInBackground(String... params) 
        {
        	/*
            String response;

            try {

                HttpClient httpclient = new DefaultHttpClient();

                HttpPost httppost = new HttpPost(url);

                HttpResponse responce = httpclient.execute(httppost);

                HttpEntity httpEntity = responce.getEntity();

                response = EntityUtils.toString(httpEntity);

                Log.d("response is", response);

                return new JSONObject(response);

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            return null;*/
        	String result = null;
    		InputStream is =null;
    		String v1=MainActivity.name;
        	System.out.println(v1);
        	JSONObject jObj=null;
        	try
           	{
        		System.out.println("check2");
        	    httpclient=new DefaultHttpClient();
                httppost= new HttpPost("http://designghar.com/flm/status.php"); // make sure the url is correct.
                //add your data
                nameValuePairs = new ArrayList<NameValuePair>();

            	nameValuePairs.add(new BasicNameValuePair("username",v1));
            	System.out.println("check21");
        	    
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //Execute HTTP Post Request
                System.out.println("check22");
        	    response=httpclient.execute(httppost);
        	    System.out.println("check2312");
                HttpEntity entity = response.getEntity();
                System.out.println("check23");
                //result = EntityUtils.toString(entity);
                is = entity.getContent();
    	        System.out.println("check24");
        	        
    	        Log.e("log_tag", "connection success ");
    	        return new JSONObject(result);
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
        	        System.out.println("check 3");
        	        
        	        result=sb.toString();
        	       // System.out.println(result);
        	        
           	}
           	catch(Exception e)
           	{
        	       Log.e("log_tag", "Error converting result "+e.toString());
        	       System.out.println("Input reading fail");
        	        //Toast.makeText(getActivity(), " Input reading fail", Toast.LENGTH_SHORT).show();

           	}
           	try {
           		jObj = new JSONObject(result);
              } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
              }
           	return jObj;
        	
        	//parse json data
        	
        }

        @Override
        protected void onPostExecute(JSONObject result) 
        {
            super.onPostExecute(result);
           try
            	{
            		JSONObject object = result;
            		String ch=object.getString("re");
            		System.out.println("check 4");
            		if(ch.equals("success"))
            		{    	  
            			JSONObject no = object.getJSONObject("0");
            			System.out.println("check 5");    	   
            			//long q=object.getLong("f1");
            			//String w= no.getString("0");
            		//	System.out.println(w);
            			String resphod= no.optString("Hod");
            			text12.setText(resphod);
            			System.out.println(resphod);
            			String respdean= no.optString("Dean");
            			text13.setText(respdean);
            			System.out.println(resphod);
            			String respdir= no.optString("Director");
            			text14.setText(respdir);
            			System.out.println(respdir);
            			if(respdir.equals("Accepted"))
            			{
            				text11.setText("leave is senctioned");
            			}
            			else if(respdean.equals("Accepted"))
            			{
            				text11.setText("At Director's Desk");
            			}
            			else if(resphod.equals("Accepted"))
            			{
            				text11.setText("At Dean's Desk");
            			}
            			else if(resphod.equals("Rejected"))
            			{
            				text11.setText("Leave is Rejected");
            			}
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
            		System.out.print("Record is not available.. Enter valid number");
            		//Toast.makeText(getActivity(), "JsonArray fail", Toast.LENGTH_SHORT).show();
            	}
            
            /*progressDialog.dismiss();

            if(result != null)
            {
                try
                {
                    JSONObject jobj = result.getJSONObject("result");

                    String status = jobj.getString("status");

                    if(status.equals("true"))
                    {
                        JSONArray array = jobj.getJSONArray("data");

                        for(int x = 0; x < array.length(); x++)
                        {
                            HashMap<String, String> map = new HashMap<String, String>();

                            map.put("name", array.getJSONObject(x).getString("name"));

                            map.put("date", array.getJSONObject(x).getString("date"));

                            map.put("description", array.getJSONObject(x).getString("description"));

                            list.add(map);
                        }

                        CalendarAdapter adapter = new CalendarAdapter(Calendar.this, list);

                        list_of_calendar.setAdapter(adapter);
                    }
                }
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(Calendar.this, "Network Problem", Toast.LENGTH_LONG).show();
            }*/
        }

    }
	
	//
	
}
