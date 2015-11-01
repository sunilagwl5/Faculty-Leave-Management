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
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create_leave extends Fragment 
{	
	Thread ther;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    EditText text1,text2;
	Button button;
	String Reason,type,user;
    public Create_leave(){}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.create_leave, container, false);
		text1 = (EditText) rootView.findViewById(R.id.editText1);
		text2= (EditText) rootView.findViewById(R.id.editText2 );
		 button = (Button) rootView.findViewById(R.id.button1);
		 button.setOnClickListener(new OnClickListener() 
		 {
			 @Override
			 public void onClick(View view) 
			 {
				 sendEmail();
			 }
		 });
		 return rootView;
    }
	protected void sendEmail() 
	{	
	      System.out.println("Send email");
	      String[] TO = {"coolsunila1@gmail.com"};
	      String[] CC = {"sunilagwl5@gmail.com"};
	      String subject = text1.getText().toString() + "";
	      Reason = text2.getText().toString();
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("message/rfc822");
	      String v1=MainActivity.name;
	      user=v1;
	      type="leave1";
	      System.out.println("Send email2");
	      new Thread(new Runnable() 
	      {
              public void run() 
              {
            	  leave();            
              }
            }).start();
	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	      emailIntent.putExtra(Intent.EXTRA_CC, CC);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Sir,\n\t"+Reason+"\n\t please http://designghar.com/flm/hodresponce.php to for give your responce");

	      try 
	      {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         System.out.println("Finished sending email...");
	      } 
	      catch (android.content.ActivityNotFoundException ex)
	      {
	         System.out.println("There is no email client installed.");
	      }
	   }
	void leave()
	{
		String result = null;
		InputStream is = null;
		try
		{            
			httpclient=new DefaultHttpClient();
			httppost= new HttpPost("http://designghar.com/flm/lcreate.php"); // make sure the url is correct.
			//add your data
			nameValuePairs = new ArrayList<NameValuePair>();
			// Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
			nameValuePairs.add(new BasicNameValuePair("Username",user));  
			nameValuePairs.add(new BasicNameValuePair("Type_leave",type));
			nameValuePairs.add(new BasicNameValuePair("Content",Reason));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response=httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

   	       	Log.e("log_tag", "connection success ");
		}	
		catch(Exception e)
		{
			Log.e("log_tag", "Error in http connection "+e.toString());
			//Toast.makeText(this.getActivity(), "Connection fail", Toast.LENGTH_SHORT).show();
		}
       	//convert response to string
       	try
       	{
       		//System.out.println("123 4Hello world");
       		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
       		StringBuilder sb = new StringBuilder();
       		String line = null;
       		while ((line = reader.readLine()) != null) 
       		{
       			sb.append(line + "\n"); 	    
       		}
       		is.close();
       		result=sb.toString();
       		//System.out.println(" 11 2454 Hello world");
       	}
       	catch(Exception e)
       	{
       		Log.e("log_tag", "Error converting result "+e.toString());
       	    //Toast.makeText(this.getActivity(), " Input reading fail", Toast.LENGTH_SHORT).show();
       	}

       	//parse json data
       	try
       	{
       		JSONObject object = new JSONObject(result);
       		String ch=object.getString("re");
       		if(ch.equals("User Found"))
       		{
       			//System.out.println("Hello world");
       			//JSONObject no = object.getJSONObject("0");
       			//String w= no.getString("f2");
       			//long e=no.getLong("f3");
	                
       			//String myString = NumberFormat.getInstance().format(e);
       			//dialog.dismiss();
       			//Toast.makeText(getActivity(),"leave Created", Toast.LENGTH_SHORT).show();
                 
       		}
       		else
       		{
       			//Toast.makeText(this.getActivity(), "Record is not available.. Enter valid number", Toast.LENGTH_SHORT).show();
       		}
       	}	
       	catch(JSONException e)
       	{
       		Log.e("log_tag", "Error parsing data "+e.toString());
       		//Toast.makeText(this.getActivity() , "JsonArray fail", Toast.LENGTH_SHORT).show();
       	}
	}
}
