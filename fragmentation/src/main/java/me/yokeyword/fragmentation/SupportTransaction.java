package me.yokeyword.fragmentation;

import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Add some action when calling {@link SupportFragment#start(SupportFragment)
 * or SupportActivity/SupportFragment.startXXX()}
 * <p>
 * Created by YoKey on 16/11/24.
 */
public abstract class SupportTransaction {

    /**
     * default: FragmentTransaction.commit()
     */
    public static final int COMMIT = 0;
    /**
     * FragmentTransaction.commitAllowingStateLoss()
     * <p>
     * Allows the commit to be executed after an
     * activity's state is saved.  This is dangerous because the commit can
     * be lost if the activity needs to later be restored from its state, so
     * this should only be used for cases where it is okay for the UI state
     * to change unexpectedly on the user.
     */
    public static final int COMMIT_ALLOWING_STATE_LOSS = 1;
    /**
     * commit() + executePendingTransactions()
     * <p>
     * it is scheduled to be executed asynchronously on the process's main thread.
     */
    public static final int COMMIT_IMMEDIATE = 2;

    @IntDef({COMMIT, COMMIT_ALLOWING_STATE_LOSS, COMMIT_IMMEDIATE})
    @Retention(RetentionPolicy.SOURCE)
    @interface CommitMode {
    }

    /**
     * @param tag Optional tag name for the fragment, to later retrieve the
     *            fragment with {@link SupportFragment#findFragment(String)}
     *            , SupportFragment.pop(String)
     *            or FragmentManager.findFragmentByTag(String).
     * @return the same SupportTransaction instance.
     */
    public abstract SupportTransaction setTag(String tag);

    /**
     * @param commitMode Select a commit mode for this transaction.
     *                   <p>
     *                   May be one of {@link #COMMIT}, {@link #COMMIT_ALLOWING_STATE_LOSS}
     *                   or {@link #COMMIT_IMMEDIATE}.
     * @return the same SupportTransaction instance.
     */
    public abstract SupportTransaction setCommitMode(@CommitMode int commitMode);

    /**
     * @param launchMode Can replace {@link SupportFragment#start(SupportFragment, int)}
     *                   <p>
     *                   May be one of {@link SupportFragment#STANDARD}, {@link SupportFragment#SINGLETASK}
     *                   or {@link SupportFragment#SINGLETOP}.
     * @return the same SupportTransaction instance.
     */
    public abstract SupportTransaction setLaunchMode(@SupportFragment.LaunchMode int launchMode);

    /**
     * Can replace {@link SupportFragment#startWithPop(SupportFragment)}
     *
     * @param with return true if you need pop currentFragment
     * @return the same SupportTransaction instance.
     */
    public abstract SupportTransaction withPop(boolean with);

    /**
     * Can replace {@link SupportFragment#startWithSharedElement(SupportFragment, View, String)}
     * <p>
     * Used with custom Transitions to map a View from a removed or hidden
     * Fragment to a View from a shown or added Fragment.
     * <var>sharedElement</var> must have a unique transitionName in the View hierarchy.
     *
     * @param sharedElement A View in a disappearing Fragment to match with a View in an
     *                      appearing Fragment.
     * @param sharedName    The transitionName for a View in an appearing Fragment to match to the shared
     *                      element.
     * @return the same SupportTransaction instance.
     * @see Fragment#setSharedElementReturnTransition(Object)
     * @see Fragment#setSharedElementEnterTransition(Object)
     */
    public abstract SupportTransaction addSharedElement(View sharedElement, String sharedName);
}
