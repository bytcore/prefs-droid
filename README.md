# prefs-droid
An easy to use tools for Shared Preference of android
## Getting Started
Add Gradle dependency: 
```
dependencies {
    compile 'com.binjar.prefsdroid:prefs-droid:1.0@aar'
}
```
Make sure to add the repository:
```
repositories {
    maven {
        url  "http://dl.bintray.com/arifnislam/maven" 
    }
}
```
Initialize the shared preference inside the `onCreate()` of the `Application` class of your app.
```
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Preference.load().using(this).with(getPackageName()).prepare();
    }
}
```
### Usage
After initialization, you can save values to the shared preferences from anywhere in your app, such as:
```
Preference.putString(TEXT, textInput.getText().toString());
```
or save an object
```
User user = new User();
Preference.putObject(OBJECT, user);
```
You can get the object by passing the `class` of the `User`
```
User savedUser = Preference.getObject(OBJECT, User.class);
```
