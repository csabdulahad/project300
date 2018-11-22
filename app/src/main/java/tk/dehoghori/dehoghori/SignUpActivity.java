package tk.dehoghori.dehoghori;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import tk.dehoghori.dehoghori.data.Key;
import tk.dehoghori.dehoghori.helper.InputChecker;
import tk.dehoghori.dehoghori.helper.PrefHelper;
import tk.dehoghori.dehoghori.helper.UtilHelper;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UtilHelper.goFullScreen(this);
        setContentView(R.layout.activity_sign_up);
    }

    public void register(View view) {

        int sex, day, month, year;
        String name, bloodGroup;

        EditText nameET, dayET, monthET, yearET, bloodGroupET;
        RadioGroup sexRG;

        nameET = findViewById(R.id.name);
        dayET = findViewById(R.id.day);
        monthET = findViewById(R.id.month);
        yearET = findViewById(R.id.year);
        sexRG = findViewById(R.id.radioGroupSex);
        bloodGroupET = findViewById(R.id.bloodGroup);

        /*
         * here we can see the true beauty and capability of the InputChecker helper class. see how
         * easy it is to filter and validate inputs. and on any error, showing error message  and
         * animating that input element is encapsulated to the InputChecker class. we don't need
         * to do any of those
         * */
        if (!InputChecker.passTextSpaceRangeCheck(nameET, 3, 0, true)) return;
        if (!InputChecker.passNumberRangeCheck(dayET, 1, 31, false)) return;
        if (!InputChecker.passNumberRangeCheck(monthET, 1, 12, false)) return;
        if (!InputChecker.passNumberRangeCheck(yearET, 1800, 3000, false)) return;
        if (!InputChecker.passSelectionCheck(sexRG)) return;
        if (!InputChecker.passTextRangeCheck(bloodGroupET, 1, 5, false)) return;

        // as to this point we don't have any problem with inputs, so get them
        name = nameET.getText().toString();
        bloodGroup = bloodGroupET.getText().toString();
        day = Integer.parseInt(dayET.getText().toString());
        month = Integer.parseInt(monthET.getText().toString());
        year = Integer.parseInt(yearET.getText().toString());
        sex = sexRG.getCheckedRadioButtonId() == R.id.sexFemale ? Key.SEX_FEMALE : Key.SEX_MALE;

        // for now, let's store user info into shared preference
        PrefHelper.putString(this, Key.USER_NAME, name);
        PrefHelper.putString(this, Key.USER_BLOOD_GROUP, bloodGroup);
        PrefHelper.putInt(this, Key.USER_DOB_DAY, day);
        PrefHelper.putInt(this, Key.USER_DOB_MONTH, month);
        PrefHelper.putInt(this, Key.USER_DOB_YEAR, year);
        PrefHelper.putInt(this, Key.USER_SEX, sex);

        // now log that user has been through the first time lunch and take user to the main activity
        PrefHelper.putBoolean(this, Key.FIRST_RUN, false);
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

}