package com.binjar.prefdroidexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.binjar.prefsdroid.Preference;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_KEY    = "text";
    private static final String NUMBER_KEY  = "number";
    private static final String BOOL_KEY    = "boolean";
    private static final String USER_KEY    = "user";

    @BindView(R.id.et_text)             EditText textInput;
    @BindView(R.id.et_number)           EditText numberInput;
    @BindView(R.id.et_bool)             EditText booleanInput;

    @BindView(R.id.btn_save_text)       View saveTextBtn;
    @BindView(R.id.btn_save_number)     View saveNumberBtn;
    @BindView(R.id.btn_save_bool)       View saveBooleanBtn;

    @BindViews({R.id.et_user_name, R.id.et_user_age, R.id.et_user_address})
    List<EditText> userInputs;
    @BindView(R.id.btn_save_user)       View saveUserBtn;

    @BindString(R.string.failed_saved) String failed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save_text)
    public void onSaveTextButtonClick(View view) {
        Preference.putString(TEXT_KEY, textInput.getText().toString());
        showToast(Preference.containsKey(TEXT_KEY) ? Preference.getString(TEXT_KEY, "") : failed);
    }

    @OnClick(R.id.btn_save_number)
    public void onSaveNumberButtonClick(View view) {
        try {
            int number = Integer.valueOf(numberInput.getText().toString());
            Preference.putInt(NUMBER_KEY, number);
            showToast(Preference.containsKey(NUMBER_KEY) ? String.valueOf(Preference.getInt(NUMBER_KEY)) : failed);
        } catch (Exception ex) {
            ex.printStackTrace();
            showToast("Enter a valid number");
        }
    }

    @OnClick(R.id.btn_save_bool)
    public void onSaveBooleanBtnClick(View view) {
        try {
            boolean aBoolean = Boolean.valueOf(booleanInput.getText().toString());
            Preference.putBoolean(BOOL_KEY, aBoolean);
            showToast(Preference.containsKey(BOOL_KEY) ? String.valueOf(Preference.getBoolean(BOOL_KEY)) : failed);
        } catch (Exception ex) {
            ex.printStackTrace();
            showToast("Enter a valid boolean");
        }
    }

    @OnClick(R.id.btn_save_user)
    public void onSaveUserButtonClick(View view) {
        try {
            String name = userInputs.get(0).getText().toString();
            int age = Integer.valueOf(userInputs.get(1).getText().toString());
            String address = userInputs.get(2).getText().toString();

            User user = new User(name, age, address);
            Preference.putObject(USER_KEY, user);

            if (Preference.containsKey(USER_KEY)) {
                User savedUser = Preference.getObject(USER_KEY, User.class);
                if (savedUser != null) {
                    showToast(savedUser.getName() + " of age " + savedUser.getAge() + " lives in " + savedUser.getAddress());
                } else {
                    showToast("no user record");
                }
            } else {
                showToast(failed);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showToast("Unexpected error!");
        }
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private class User {
        private String name;
        private int age;
        private String address;

        User() {
            //
        }

        User(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        int getAge() {
            return age;
        }

        void setAge(int age) {
            this.age = age;
        }

        String getAddress() {
            return address;
        }

        void setAddress(String address) {
            this.address = address;
        }
    }
}
