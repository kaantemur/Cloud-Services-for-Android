package io.smartface.CloudIntegration;

import io.smartface.CloudIntegration.R;

import io.smartface.sesclient.SESClient;
import io.smartface.sesclient.SESClient.UpdateResponseCallback;
import io.smartface.sesclient.UpdateResponse;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Ses extends Activity {
	private String API_KEY = "YOUR_API_KEY";
	private String SES_URL = "http://analytics.smartface.io/api/AnalyticsData/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ses);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ses, menu);
		return true;
	}

	
	public void registerDevice(View v){
		SESClient.registerDeviceToken("MY_TOKEN");
	}
	
	public void startSession(View v){
		SESClient.setLoggingEnabled(true);
		SESClient.startSession(Ses.this, API_KEY, SES_URL);
	}
	
	public void endSession(View v){
		SESClient.endSession(getApplicationContext());
	}
	
	public void pauseSession(View v){
		SESClient.pauseSession();
	}
	
	public void recordCustomer(View v){
		Log.v("SESClient","record customer");
		SESClient.recordCustomer("name", "surname", "905051234567", "email@example.com", "customerId","{\"key\":\"customparam\"}");
	}
	
	public void recordEvent(View c){
		SESClient.recordEvent("myEvent", "{\"key\":\"value\"}");
	}	
	
	public void checkUpdate(View v){
		Log.v("SESClient","state: " + SESClient.getLastUpdateState());
		SESClient.checkUpdate(getApplicationContext(), new UpdateResponseCallback() {
			
			@Override
			public void OnResponse(UpdateResponse updateResponse) {
				Log.v("SESClient","status: " + updateResponse.getStatus() + " state code:" + SESClient.getLastUpdateState());
			}
		});
	}
	

}

