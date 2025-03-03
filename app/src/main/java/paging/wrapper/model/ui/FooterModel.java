package paging.wrapper.model.ui;

import com.google.auto.value.AutoOneOf;
import paging.wrapper.model.ui.FooterModel.PendingStatus;

@AutoOneOf(PendingStatus.class)
public abstract class FooterModel {

  public abstract void toBeRemoved();

  public abstract FooterEntity toBeAdded();

  public abstract FooterEntity toBeRefreshed();

  public abstract PendingStatus status();

  public static FooterModel ofToBeRemoved() {
    return AutoOneOf_FooterModel.toBeRemoved();
  }

  public static FooterModel ofToBeAdded(FooterEntity toBeAdded) {
    return AutoOneOf_FooterModel.toBeAdded(toBeAdded);
  }

  public static FooterModel ofToBeRefreshed(FooterEntity toRefreshed) {
    return AutoOneOf_FooterModel.toBeRefreshed(toRefreshed);
  }

  public enum PendingStatus {
    TO_BE_REMOVED,
    TO_BE_ADDED,
    TO_BE_REFRESHED
  }
}
