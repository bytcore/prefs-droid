# prefs-droid
An easy to use wrapper tools for Shared Preference of android
## Getting Started
[ ![Download](https://api.bintray.com/packages/arifnislam/maven/prefs-droid/images/download.svg) ](https://bintray.com/arifnislam/maven/prefs-droid/_latestVersion) from JCenter
<br /> or <br />
Add dependency in your module **build.gradle** file
```Java
dependencies {
    compile 'com.binjar.prefsdroid:prefs-droid:1.1.0'
}
```
Initialize preference in the `Application` class
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Preference.load().using(this).prepare();
    }
}
```
If you wish to add custom name of the shared preference use
```Java
Preference.load().using(this).with(PREFERENCE_NAME).prepare();
```
### Usage
After initialization, you can save values to the shared preferences from anywhere in your app, such as:
```Java
Preference.putString(TEXT, textInput.getText().toString());
```
or save an object
```java
User user = new User(name, age);
Preference.putObject(OBJECT, user);
```
You can get the object by passing the `class` of the `User`
```java
User savedUser = Preference.getObject(OBJECT, User.class);
```
You can remove a single key
```Java
if (Preference.containsKey(KEY)) {
    Preference.remove(KEY);
}
```
or remove all keys by calling
```Java
Preference.clear();
```

### Bonus
You can add `@Type()` source annotation to your preference keys which will help you to find easily what type of value is stored against that key. See smaple [PrefsKey.java](https://github.com/binjar/prefs-droid/blob/master/prefsdroid-sample/src/main/java/com/binjar/prefdroidexample/PrefsKey.java) for usage.

## License
This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/binjar/prefs-droid/blob/master/LICENSE) file for details
