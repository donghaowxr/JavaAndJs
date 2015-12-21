package com.example.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
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
	        webview.setWebChromeClient(new WebChromeClient(){
	        	@Override
	        	public boolean onJsAlert(WebView view, String url,
	        			String message, final JsResult result) {
	        		// TODO Auto-generated method stub
	        		AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
	        		builder.setTitle("对话框").setMessage(message).setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							result.confirm();
						}
					});
	        		builder.setOnKeyListener(new OnKeyListener() {
						
						@Override
						public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
							return true;
						}
					});
	        		builder.setCancelable(false);
	        		AlertDialog dialog=builder.create();
	        		dialog.show();
	        		return true;
	        	}
	        });
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
