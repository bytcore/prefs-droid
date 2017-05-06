package com.binjar.prefdroidexample;

import com.binjar.prefsdroid.Type;

import static com.binjar.prefsdroid.Type.ValueTpe.BOOLEAN;
import static com.binjar.prefsdroid.Type.ValueTpe.INTEGER;
import static com.binjar.prefsdroid.Type.ValueTpe.OBJECT;
import static com.binjar.prefsdroid.Type.ValueTpe.STRING;


/**
 * Created by Arif Islam on 06-May-17.
 */

public interface PrefsKey {
    @Type(STRING)  String EMAIL_KEY  = "email";
    @Type(BOOLEAN) String NOTIFY_KEY = "shallNotify";
    @Type(OBJECT)  String USER_KEY   = "user";
}
