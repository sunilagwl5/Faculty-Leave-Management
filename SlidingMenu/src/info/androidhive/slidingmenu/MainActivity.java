package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	public static String name="";
	public static  String password="";
	public static UserSession session;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	Boolean isInternetPresent = false;
    
    // Connection detector class
	connection cd;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
    	
		cd = new connection(getApplicationContext());
        
		isInternetPresent = cd.isConnectingToInternet();
		 
		
			session = new UserSession(getApplicationContext());
        	mTitle = mDrawerTitle = getTitle();

        	// load slide menu items
        	navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        	// nav drawer icons from resources
        	navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

        	mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        	mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        	navDrawerItems = new ArrayList<NavDrawerItem>();
        	
        	// adding nav drawer items to array
        	// Home
        	navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        	// Find People
        	navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        	// Photos
        	navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        	// Communities, Will add a counter here
        	navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        	// Pages
        	navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        	// What's hot, We  will add a counter here
        	//navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
		

        	// Recycle the typed array
        	navMenuIcons.recycle();

        	mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        	
        	// setting the nav drawer list adapter
        	adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
        	mDrawerList.setAdapter(adapter);

        	// enabling action bar app icon and behaving it as toggle button
        	getActionBar().setDisplayHomeAsUpEnabled(true);
        	getActionBar().setHomeButtonEnabled(true);
		
        	mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
        	R.drawable.ic_drawer, //nav menu toggle icon
        	R.string.app_name, // nav drawer open - description for accessibility
        	R.string.app_name // nav drawer close - description for accessibility
        			)
        	{
        		public void onDrawerClosed(View view) 
        		{
        			getActionBar().setTitle(mTitle);
        			// calling onPrepareOptionsMenu() to show action bar icons
        			invalidateOptionsMenu();
        		}

        		public void onDrawerOpened(View drawerView) 
        		{
        			getActionBar().setTitle(mDrawerTitle);
        			// calling onPrepareOptionsMenu() to hide action bar icons
        			invalidateOptionsMenu();
        		}
        	};
        	mDrawerLayout.setDrawerListener(mDrawerToggle);

        	if (savedInstanceState == null) 
        	{
        		// on first time display view for first nav item
        		displayView(0);
        	}
        	
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements ListView.OnItemClickListener 
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) 
		{
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) 
		{
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) 
		{
			case R.id.action_settings:
			return true;
			default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) 
	{
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	
	void displayView(int position) 
	{
				Fragment fragment = null;
		switch (position) 
		{
			case 0:
			if (isInternetPresent) 
			{
				if(name=="")
				{	
					fragment = new Signout();
				}
				else
				{
					fragment = new Profile(); 
				}
	        }
	        else 
	        {
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);  
		        builder.setMessage("No Internet Connected")  
		            .setCancelable(false).setPositiveButton("Close", new DialogInterface.OnClickListener() {  
		                public void onClick(DialogInterface dialog, int id) {  
		                  finish();
		                }  
		            });  
		        AlertDialog alert = builder.create();  
	        	alert.setTitle("No Internet ");  
	        	alert.show(); 
	         }
			break;
			case 1:
				if(name=="")
				{	
					fragment = new Signout();
				}
				else
				{
					fragment = new Create_leave();
				}
			break;
			case 2:
				if(name=="")
				{	
					fragment = new Signout();
				}
				else
				{
					fragment = new Current_status();
				}
			break;
			case 3:
			if(name!="")
			{	
				AlertDialog.Builder builder = new AlertDialog.Builder(this);  
		        builder.setMessage("Do you want to Signout ?")  
		            .setCancelable(false).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() 
		            {  
		            	public void onClick(DialogInterface dialog, int id) 
		            	{  
		            		MainActivity.session.logoutUser();
							MainActivity.name="";
							MainActivity.password="";
							displayView(3);
		                }  
		            })  
		            .setNegativeButton("No", new DialogInterface.OnClickListener() 
		            {  
		                public void onClick(DialogInterface dialog, int id) 
		                {  
		                	//  Action for 'NO' Button
		                	displayView(0);
			                dialog.cancel();  
		                }  
		            });  
		  
		        	//Creating dialog box  
		        	AlertDialog alert = builder.create();  
		        	//Setting the title manually  
		        	alert.setTitle("Signout");  
		        	alert.show(); 
			}
			else
			{//fragment = new Exit();
			//finish();
            //System.exit(0);
			fragment = new Signout();
			}break;
			case 4:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);  
		        builder.setMessage("Do you want to close this application ?")  
		            .setCancelable(false).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {  
		                public void onClick(DialogInterface dialog, int id) {  
		                  finish();
		                }  
		            })  
		            .setNegativeButton("No", new DialogInterface.OnClickListener() {  
		                public void onClick(DialogInterface dialog, int id) {  
		                //  Action for 'NO' Button
		                displayView(0);
		                dialog.cancel();  
		             }  
		            });  
		  
		        //Creating dialog box  
		        AlertDialog alert = builder.create();  
		        //Setting the title manually  
		        alert.setTitle("Exit");  
		        alert.show(); 
			break;
			default:
			break;
		}
		if (fragment != null) 
		{
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} 
		else 
		{
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
		Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();  
	}
	@Override
	public void setTitle(CharSequence title) 
	{
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) 
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) 
	{
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
