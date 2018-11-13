package tk.dehoghori.dehoghori.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

/**
 * this is a helper class, which helps us working with fragments optimally. it has various methods
 * that help us manage fragments so that we don't need to recommit or recreate fragment more than once.
 * <p>
 * Fragment Manager manages fragments with associated tags(if given) in a stack called BackStackEntry.
 * when you commit any fragment, give that fragment a tag, so that you can later refer to
 * that fragment and can make really great use of this little helper class.
 *
 * @author abdul ahad
 * @version 1.2
 */

public class FragmentHelper {


    /**
     * this method checks to see whether a fragment exists in the BackStackEntry with the given tag
     *
     * @param fragmentManager the fragment manager to work with
     * @param tag             of the fragment, you want to look for
     * @return true if there is any fragment exists with the given tag
     */
    public static boolean fragmentExists(FragmentManager fragmentManager, String tag) {
        return fragmentManager.findFragmentByTag(tag) != null;
    }


    /**
     * this methods helps you avoid write the same code bloat evey time when you need to commit a
     * fragment with a tag and putting the fragment into the BackStackEntry
     */
    public static void commitFragment(FragmentManager fragmentManager, Fragment fragment, String tag, int contentFrame) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(tag)
                .replace(contentFrame, fragment, tag)
                .commit();
    }

    /**
     * this method can tell whether the top fragment of the stack matches the given fragmentTag
     *
     * @param fragmentManager the fragment manager to work with
     * @param tag             tag of the fragment, you want to look for
     * @return true if the top fragment of the backStackEntry has the given fragmentTag, false otherwise
     */
    public static boolean isCurrentFragment(FragmentManager fragmentManager, String tag) {
        // since there is no fragment in BackStackEntry, then return false
        if (fragmentManager.getBackStackEntryCount() == 0) return false;

        // it gets the very first/current fragment from FragmentManager.BackStackEntry
        FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);

        return backEntry.getName().equals(tag);
    }

    /**
     * this method tries to find whether there is any fragment exists with given tag. if
     * it finds fragment associated with the given tag, then it replaces the top fragment of the
     * stack with the found fragment and then commits.
     *
     * @param fragmentManager the fragment manager to work with
     * @param tag             the tag of the fragment you want to commit if it exists
     * @return true if it can find the fragment with the given tag and can commit, false otherwise
     */
    public static boolean commitIfExists(FragmentManager fragmentManager, String tag, int contentFrame) {
        if (!fragmentExists(fragmentManager, tag)) return false;

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        commitFragment(fragmentManager, fragment, tag, contentFrame);
        return true;
    }

    /**
     * this methods works exactly {@code commitIfExists} method but first it tries to figure out whether
     * the current/top the fragment of the BackStackEntry has the given fragment tag. If yes, then it
     * doesn't commit any transaction, simply returns true. Otherwise it goes ahead and invoke the
     * {@code commitIfExists} method
     */
    public static boolean commitFragmentInSafeMode(FragmentManager fragmentManager, String fragmentTag, int contentFrame) {

        // if it is current fragment, then we don't need to commit
        if (isCurrentFragment(fragmentManager, fragmentTag)) return true;

        /*
        * check if the fragment by tag exists. if exists, then recycle and commit it and
        * return fragment commit status
        */
        return commitIfExists(fragmentManager, fragmentTag, contentFrame);
    }

    /**
     * this method prints out the different information of the BackStackEntry. it uses {@code Log.d()}
     * method to print out information, using "ahad" tag. So you should filter your logs to "ahad" in
     * android studio
     */
    public static void logBackStack(FragmentManager fragmentManager) {
        List<Fragment> list = fragmentManager.getFragments();
        if (list == null) return;

        Log.d("ahad", "FirstAidFragment > toatal fragment : " + list.size());
        for (Fragment fragment : list)
            Log.d("ahad", "FragmentHelper > " + fragment.getTag());
    }
}
