package info.androidhive.slidingmenu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signout extends Fragment
{
	View rootView;
	Thread ther;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    EditText text1,text2;
	public Signout(){}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		rootView = inflater.inflate(R.layout.signin , container, false);
			Button button=(Button)rootView.findViewById(R.id.button1);
			button.setOnClickListener(new View.OnClickListener() 
			{
        		 @Override
        		 public void onClick(View arg0) 
        		 {
        				text1= (EditText)rootView.findViewById(R.id.editText1) ;
        				text2 = (EditText)rootView.findViewById(R.id.editText2) ;
        		 		
        		 		dialog = ProgressDialog.show(getActivity(), "", "Validating user...", true);
        	            ther  = new Thread(new Runnable() {public void run() 
        	            		{
        	                            login();                          
        	                    }});
        	            ther.start();
        	            	 
        		 		MainActivity.session.createUserLoginSession(text1.getText().toString(),text2.getText().toString());
        		 		HashMap<String, String> user = MainActivity.session.getUserDetails();
        		        MainActivity.name = user.get(UserSession.KEY_NAME);
        				MainActivity.password = user.get(UserSession.KEY_EMAIL);    
        		 }
        		 
			}) ;
         return rootView;
	}
	void login()
	{	
		 String result = null;
	     InputStream is = null;
	     try
	     {            
	    	 	httpclient=new DefaultHttpClient();
	            httppost= new HttpPost("http://designghar.com/flm/login.php"); // make sure the url is correct.
	            nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("username",text1.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
	            nameValuePairs.add(new BasicNameValuePair("password",text2.getText().toString().trim())); 
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            response=httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
    	        is = entity.getContent();

    	        Log.e("log_tag", "connection success ");
	        }	
	     	catch(Exception e)
      	   	{
	     		System.out.println("Error in http connection ");
      	   	}
        	//convert response to string
        	try
        	{
        	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
        	        StringBuilder sb = new StringBuilder();
        	        String line = null;
        	        while ((line = reader.readLine()) != null) 
        	        {
        	                sb.append(line + "\n");             
        	        }
        	        is.close();
        	        System.out.println("Check1111");
        	        result=sb.toString();
       		}
        	catch(Exception e)
       		{
        			System.out.println("Error converting result ");
       		}

        	//parse json data
        	try
        	{
        			JSONObject object = new JSONObject(result);
        			String ch=object.getString("re");
        			System.out.println("Check22222");
      	        
        			if(ch.equals("User Found"))
        			{
 	            	  	dialog.dismiss();
        			}
        			else
 	               	{
        				dialog.dismiss();
        				System.out.println("User name or password wrong... ");
 	               	}

        	}
        	catch(JSONException e)
        	{
        	        //Log.e("log_tag", "Error parsing data "+e.toString());
        			dialog.dismiss();
        			System.out.println("User name or password wrong... 12");
    	            
        	        //Toast.makeText(this.getActivity() , "JsonArray fail", Toast.LENGTH_SHORT).show();
        	        
        	}
	}
}