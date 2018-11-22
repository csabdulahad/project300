package tk.dehoghori.dehoghori.helper;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * this helper class has some methods that you might need to use frequently
 *
 * @author abdul ahad
 */
public class UtilHelper {

    /**
     * this method makes an activity go full screen
     *
     * @param activity the activity you want to go full screen
     */
    public static void goFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
