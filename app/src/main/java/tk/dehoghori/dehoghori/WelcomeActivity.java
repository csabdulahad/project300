package tk.dehoghori.dehoghori;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

import tk.dehoghori.dehoghori.data.Key;
import tk.dehoghori.dehoghori.helper.PrefHelper;

/*
 * this is called welcome activity, because we would like to show brand logo, our nation's
 * achievements on specific days, taking user to sign up activity and so on
 * */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check whether the app is lunched for the very first time. if yes, then go to SignUpActivity
        if (PrefHelper.getBoolean(this, Key.FIRST_RUN, true)) {
            Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // if it is not first time lunch, then figure out whether it is any of special day for our BD
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH), month = calendar.get(Calendar.MONTH);

        Intent intent;
        if (day == 21 && month == 1) {  // VASHA-BANGLA
            intent = TwentyFirstActivity.getIntent(this, 21);
        } else if (day == 26 && month == 2) {   // SHONGRAM-ROKTO
            intent = TwentyFirstActivity.getIntent(this, 26);
        } else if (day == 16 && month == 11) {  // BIJOY-SHADHINOTA
            intent = TwentyFirstActivity.getIntent(this, 16);
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
