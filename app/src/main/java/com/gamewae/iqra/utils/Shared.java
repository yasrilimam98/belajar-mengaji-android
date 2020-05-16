package com.gamewae.iqra.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Display;
import android.view.WindowManager;

public final class Shared
{
	private static ContextWrapper instance;
	private static SharedPreferences pref;
	public static Typeface mequran;
	public static Typeface appfont;
	public static Typeface appfontBold;
	public static Typeface appfontThin;
	public static Typeface appfontLight;
	public static void initialize(Context base)
	{
		instance = new ContextWrapper(base);
		pref = instance.getSharedPreferences("com.gamewar.iqro", Context.MODE_PRIVATE);
		mequran = Typeface.createFromAsset(instance.getAssets(),"fonts/me_quran_volt_newmet.ttf");
		appfont = Typeface.createFromAsset(instance.getAssets(),"fonts/Roboto-Regular.ttf");
		appfontBold = Typeface.createFromAsset(instance.getAssets(),"fonts/Roboto-Bold.ttf");
		appfontThin = Typeface.createFromAsset(instance.getAssets(),"fonts/Roboto-Thin.ttf");
		appfontLight = Typeface.createFromAsset(instance.getAssets(),"fonts/Roboto-Light.ttf");
		
	}
	
	public static void write(String key, String value)
	{
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static String read(String key)
	{
		return Shared.read(key, null);
	}
	
	public static String read(String key, String defValue)
	{
		return pref.getString(key, defValue);
	}
	
	public static void clear()
	{
		SharedPreferences.Editor editor = pref.edit();
		editor.clear();
		editor.commit();
	}
	
	public static void clear(String key)
	{
		SharedPreferences.Editor editor = pref.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public static Context getContext()
	{
		return instance.getBaseContext();
	}
	
	public static int DipToInt(int value)
	{
		return (int)(instance.getResources().getDisplayMetrics().density * value);
	}
	
	public static int getDisplayHeight()
	{
		
		 WindowManager wm = (WindowManager) instance.getSystemService(Context.WINDOW_SERVICE);
		 Display display = wm.getDefaultDisplay();
		 final int version = android.os.Build.VERSION.SDK_INT;
		 
		 if (version >= 13)
		 {
		     Point size = new Point();
		     display.getSize(size);
		    return size.y;
		 }
		 else
		 {
		     
		     return  display.getHeight();
		 }
	}
	
	public static int getDisplayWidth()
	{
		
		 WindowManager wm = (WindowManager) instance.getSystemService(Context.WINDOW_SERVICE);
		 Display display = wm.getDefaultDisplay();
		 final int version = android.os.Build.VERSION.SDK_INT;
		 
		 if (version >= 13)
		 {
		     Point size = new Point();
		     display.getSize(size);
		     return size.x;
		 }
		 else
		 {
		     
		     return  display.getWidth();
		 }
	}
}

