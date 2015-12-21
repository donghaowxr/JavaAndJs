package com.example.test;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


 public class MainActivity extends Activity {
	   private WebView webview;
	   private Button btnTest;
	   private Button btnTestReturn;
	    @SuppressLint("JavascriptInterface") @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_main);  
	        webview=(WebView) findViewById(R.id.webview);
	        btnTest=(Button) findViewById(R.id.btnTest);
	        btnTestReturn=(Button) findViewById(R.id.btnTestReturn);
	        
	        WebSettings webSettings=webview.getSettings();
	        webSettings.setJavaScriptEnabled(true);
	        webview.setWebChromeClient(new WebChromeClient());
	        webview.addJavascriptInterface(new CallJs(), "control");
	        
	        btnTest.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					webview.loadUrl("javascript:changeBc()");
				}
			});
	        
	        btnTestReturn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String call="javascript:getPlus(1,2)";
					webview.loadUrl(call);
				}
			});
	        
	        webview.loadUrl("file:///android_asset/index.html");
	    }
	    
	    public class CallJs{
	    	@JavascriptInterface
	    	public void callJs(String message){
	    		Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
	    	}
	    	
	    	@JavascriptInterface
	    	public int plus(int a,int b){
	    		return a+b;
	    	}
	    	
	    	@JavascriptInterface
	    	public void callBack(String res){
	    		Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();
	    	}
	    }
}
