package com.binjar.prefdroidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.binjar.prefsdroid.Preference;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static com.binjar.prefdroidexample.PrefsKey.EMAIL_KEY;
import static com.binjar.prefdroidexample.PrefsKey.NOTIFY_KEY;
import static com.binjar.prefdroidexample.PrefsKey.USER_KEY;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_email) EditText emailEditText;

    @BindViews({R.id.et_user_name, R.id.et_user_age, R.id.et_user_address})
    List<EditText> userInputs;

    @BindString(R.string.saved)        String saved;
    @BindString(R.string.failed_saved) String failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        emailEditText.setText(Preference.containsKey(EMAIL_KEY) ? Preference.getString(EMAIL_KEY, "") : "");
        ((AppCompatCheckBox) ButterKnife.findById(this, R.id.cb_notify_me)).setChecked(Preference.getBoolean(NOTIFY_KEY));

        User user = Preference.getObject(USER_KEY, User.class);
        if (user != null) {
            userInputs.get(0).setText(user.getName());
            userInputs.get(1).setText(String.valueOf(user.getAge()));
            userInputs.get(2).setText(user.getAddress());
        }
    }

    @OnClick(R.id.btn_save_email) void saveText() {
        Preference.putString(EMAIL_KEY, emailEditText.getText().toString());
        showToast(Preference.containsKey(EMAIL_KEY) ? saved : failed);
    }

    @OnCheckedChanged(R.id.cb_notify_me) void saveBoolean(boolean checked) {
        Preference.putBoolean(NOTIFY_KEY, checked);
        showToast(Preference.containsKey(NOTIFY_KEY) ? saved : failed);
    }

    @OnClick(R.id.btn_save_user) void saveUser() {
        try {
            String name    = userInputs.get(0).getText().toString();
            int    age     = Integer.valueOf(userInputs.get(1).getText().toString());
            String address = userInputs.get(2).getText().toString();

            User user = new User(name, age, address);
            Preference.putObject(USER_KEY, user);

            showToast(Preference.containsKey(USER_KEY) ? saved : failed);
        } catch (Exception ex) {
            ex.printStackTrace();
            showToast("Unexpected error!");
        }
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
