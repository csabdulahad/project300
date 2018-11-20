package tk.dehoghori.dehoghori;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import tk.dehoghori.dehoghori.helper.IntentHelper;
import tk.dehoghori.dehoghori.helper.UtilHelper;

public class TwentyFirstActivity extends AppCompatActivity {

    long delay = 2500; // 2.5 sec
    int day;
    boolean shouldClose;

    private static final String KEY_DAY = "kii";

    /*
     * we would like to show images that will depict the scenario of our nation's achievement for a
     * few seconds. that is why we use this time to perform tasks after few seconds have been elapsed
     * */
    private Timer mTimer;

    public static Intent getIntent(Context context, int imageId) {
        Intent intent = new Intent(context, TwentyFirstActivity.class);
        intent.putExtra(KEY_DAY, imageId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        day = getIntent().getIntExtra(KEY_DAY, -1);

        int imageId;
        int messageId;
        if (day == 21) {
            imageId = R.drawable.twenty_first_february;
            messageId = R.string.message_21;
        } else if (day == 26) {
            imageId = R.drawable.twenty_six_march;
            messageId = R.string.message_26;
        } else {
            imageId = R.drawable.sixteen_december;
            messageId = R.string.message_16;
        }

        UtilHelper.goFullScreen(this);

        setContentView(R.layout.activity_twenty_first);

        ImageView imageView = findViewById(R.id.twenty_project_image);
        imageView.setImageResource(imageId);

        TextView textView = findViewById(R.id.twenty_project_massage);
        textView.setText(getString(messageId));

        // this is the task that we want the timer to perform after a specified second has been elapsed
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(TwentyFirstActivity.this, MainActivity.class));
                finish();
            }
        };

        mTimer = new Timer();
        mTimer.schedule(task, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.cancel();
        shouldClose = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shouldClose) {
            startActivity(new Intent(TwentyFirstActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        /*
         * we override this method, so that user can't back press while in the activity watching
         * our nation's pride
         * */
    }

    // it opens specific url to help user learn more about our nation's pride
    public void learnMore(View view) {
        String link;
        if (day == 21) {
            link = getString(R.string.link_international_mother_language_day);
        } else if (day == 26) {
            link = getString(R.string.link_independence_day);
        } else {
            link = getString(R.string.link_victory_day);
        }
        IntentHelper.openUrl(this, link);
    }

}
