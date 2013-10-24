# SESClient Library for Android

Smartface Enterprise Server (SES) is not just an life cycle management tool for your mobile application. It is an assistant for your mobile app strategies.

SES is designed for your enterprise for multiple purposes of use. SES can be used by IT to manage applications; apps can be monitored by decision makers; contact center has its own support interface, all managed by Access Control Levels (ACL).

Everything in the SES architecture is expandable with Service Oriented Architecture. (SOA) Your enterprise can integrate with SES with the given API’s.

SESClient Library is a logging and analytics library works with SES.

## Usage


- In your Android Project Properties navigate to Java Build Path > Libraries. Add sesclient.jar using Add External JARs.
- In Order and Export tab check sesclient.jar
- Import necessary classes

```java
import biz.mobinex.sesclient.SESClient;
import biz.mobinex.sesclient.SESClient.UpdateResponseCallback;
import biz.mobinex.sesclient.UpdateResponse;
```

##### Permissions
If permissions are not given, related features will not work. Permissions and features are below:
- android.permission.INTERNET is mandatory for network operations.
- android.permission.GET_TASKS is mandatory
- android.permission.READ_PHONE_STATE is for MSISDN
- android.permission.ACCESS_NETWORK_STATE is for detecting network type
- android.permission.ACCESS_FINE_LOCATION is for location services

Add lines below to your manifest file.
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.GET_TASKS"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```

##### Starting Session
User activities are recorded in a session. A session shuld be started for tracking.
At the first start, user will be prompted to enable location services for your application.
```java
SESClient.startSession(YOUR_ACTIVITY, "YOUR_API_KEY", "SES_URL");
```

##### Ending Session 
Session can be ended to stop tracking.
```java
SESClient.endSession();
```

##### Pausing Session
startSession method will be called to resume a paused session.
```java
SESClient.pauseSession();
```

##### Recording Customer
Customer can be recorded with customer information. With customParams parameter, information about customer can be sent in JSON format.
```java
SESClient.recordCustomer("name", "surname", "phone_number", "email", "customer_id","{\"key\":\"value\"}");
```

##### Recording Event
Events can be recorded by defining an event name for creating detailed path analyze. Optionally event details can be sent in JSON format.
```java
SESClient.recordEvent("event_name", "{\"key\":\"value\"}");
```

##### Checking Update
In your application, an update check mechanism can be used with SES.
```java
SESClient.checkUpdate(getApplicationContext(), new UpdateResponseCallback() {
	@Override
	public void OnResponse(UpdateResponse updateResponse) {
		Log.v("SESClient","status: " + updateResponse.getStatus() + " state code:" + SESClient.getLastUpdateState());
	}
});
```
##### Logging
To see SESClientSESClient logs on Logcat, logs should be enabled. Enabling logs before starting session is suggested.
```java
if (!SESClient.isLoggingEnabled()){
	SESClient.setLoggingEnabled(true);
}
```
