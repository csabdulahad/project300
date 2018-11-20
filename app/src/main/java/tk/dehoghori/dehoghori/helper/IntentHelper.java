package tk.dehoghori.dehoghori.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * this helper class holds some useful methods to manage different intent actions. as with android's
 * new permission scheme, intents can no longer access critical aspects of the device such contact,
 * storage, etc. so this helper class makes that process easier
 *
 * @author abdul ahad
 */
public class IntentHelper {

    /**
     * this methods takes a url and tries to open that url in a web browser if there any.
     *
     * @param context the context where you are
     * @param url     the url you want to open by web browser
     */
    public static boolean openUrl(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                return true;
            } else {
                Toast.makeText(context, "No browser found", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Unknown error happened", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}