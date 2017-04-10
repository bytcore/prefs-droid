# prefs-droid
An easy to use tools for Shared Preference of android
## Getting Started
Add dependency in your module **build.gradle** file
```Java
dependencies {
    compile 'com.binjar.prefsdroid:prefs-droid:1.0@aar'
}
```
Make sure to add the Maven Central Repository to **build.gradle** before **dependencies**
```java
repositories {
    maven {
        url  "http://dl.bintray.com/arifnislam/maven" 
    }
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
## License
This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/binjar/prefs-droid/blob/master/LICENSE) file for details
