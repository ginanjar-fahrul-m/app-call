# app-call¬

App call is a demo to show communication between apps in Android and html pages.

Features:
  1. Open Android app by web browser
  2. Open Android app by another app

## Application Caller
### Web Browser 

  - Project path: `app-call/web/`
  - Sending params with query parameters: 

```
href="http://digitalines.com/call?firstparam=first&secondparam=second"
```

### First Application 

  - In First Application `app-call/firstapp`

Call main activity:

```java
    launchIntent = getPackageManager()
                .getLaunchIntentForPackage(SECOND_APP_ID);
```

Call regular activity:

```java
    launchIntent = new Intent(SECOND_APP_ID + ".CallActivity");
```

Then:

```java
    if (launchIntent != null) {
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchIntent.putExtra(BUNDLE_NAME, params);
        startActivity(launchIntent);
    } else {
        launchIntent = new Intent(Intent.ACTION_VIEW);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchIntent.setData(Uri.parse("market://details?id=" + SECOND_APP_ID));
        startActivity(launchIntent);
    }
```

  - Sending params with Intent Extra: 

```java
Bundle params = new Bundle();
params.putString(FIRST_PARAM, firstParam);
params.putString(SECOND_PARAM, secondParam);
launchIntent.putExtra(params);
```

The demo include sending params to apps. 

## Application to be called

  1. Second Application `app-call/secondapp .. MainActivity`
  2. Second Application `app-call/secondapp .. CallActivity`

### Important: XML Manifest (Second Application)

For app call, add intent filter custom action

```xml
<intent-filter>
    <action android:name="com.digitalines.secondapp.CallActivity" />
    <category android:name="android.intent.category.DEFAULT" />
</intent-filter>
```

For browser call, add intent filter to category `BROWSABLE` and set data scheme like this:

```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />

    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />

    <data android:host="digitalines.com" android:scheme="http" android:path="/main"/>
</intent-filter>
```

### Version

1.0.0

### Demo

Open with Chrome Android. APKs are available on `app-call/apk`
```
http://digitalines.com/app-call/web
```

### FAQ

`mailto: ginanjar.fahrul.m@gmail.com`

License
----

