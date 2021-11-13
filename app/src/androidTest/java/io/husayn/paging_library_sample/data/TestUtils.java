package io.husayn.paging_library_sample.data;

import android.view.View;
import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/** Created by dannyroa on 5/9/15. */
public class TestUtils {

  public static ViewAction actionOnItemViewAtPosition(
      int position, @IdRes int viewId, ViewAction viewAction) {
    return new ActionOnItemViewAtPositionViewAction(position, viewId, viewAction);
  }

  public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

    return new RecyclerViewMatcher(recyclerViewId);
  }

  private static final class ActionOnItemViewAtPositionViewAction implements ViewAction {

    private final int position;
    private final ViewAction viewAction;
    private final int viewId;

    private ActionOnItemViewAtPositionViewAction(
        int position, @IdRes int viewId, ViewAction viewAction) {
      this.position = position;
      this.viewAction = viewAction;
      this.viewId = viewId;
    }

    public Matcher<View> getConstraints() {
      Matcher<View> displayed = ViewMatchers.isDisplayed();
      Matcher<View> assignableFrom = ViewMatchers.isAssignableFrom(RecyclerView.class);
      return Matchers.allOf(assignableFrom, displayed);
    }

    public String getDescription() {
      return "actionOnItemAtPosition performing ViewAction: "
          + this.viewAction.getDescription()
          + " on item at position: "
          + this.position;
    }

    public void perform(UiController uiController, View view) {
      RecyclerView recyclerView = (RecyclerView) view;
      (new ScrollToPositionViewAction(this.position)).perform(uiController, view);
      uiController.loopMainThreadUntilIdle();

      View targetView = recyclerView.getChildAt(this.position).findViewById(this.viewId);

      if (targetView == null) {
        throw (new PerformException.Builder())
            .withActionDescription(this.toString())
            .withViewDescription(HumanReadables.describe(view))
            .withCause(
                new IllegalStateException(
                    "No view with id " + this.viewId + " found at position: " + this.position))
            .build();
      } else {
        this.viewAction.perform(uiController, targetView);
      }
    }
  }

  public static final class ScrollToPositionViewAction implements ViewAction {

    private final int position;

    public ScrollToPositionViewAction(int position) {
      this.position = position;
    }

    public Matcher<View> getConstraints() {
      Matcher<View> assignableFrom = ViewMatchers.isAssignableFrom(RecyclerView.class);
      Matcher<View> displayed = ViewMatchers.isDisplayed();
      return Matchers.allOf(assignableFrom, displayed);
    }

    public String getDescription() {
      return "scroll RecyclerView to position: " + this.position;
    }

    public void perform(UiController uiController, View view) {
      RecyclerView recyclerView = (RecyclerView) view;
      recyclerView.scrollToPosition(this.position);
    }
  }
}
