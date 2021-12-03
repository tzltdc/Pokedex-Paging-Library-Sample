package androidx.paging.wrapper;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/** Collection of pagination LoadStates - refresh, prepend, and append. */
@AutoValue
@GenerateTypeAdapter
public abstract class LoadStates {

  public static LoadStates IDLE =
      LoadStates.builder()
          .refresh(LoadState.INCOMPLETE)
          .prepend(LoadState.INCOMPLETE)
          .append(LoadState.INCOMPLETE)
          .build();

  /** LoadState corresponding to LoadType.REFRESH loads. */
  public abstract LoadState refresh();

  /** LoadState corresponding to LoadType.PREPEND loads. */
  public abstract LoadState prepend();

  /** LoadState corresponding to LoadType.APPEND loads. */
  public abstract LoadState append();

  public LoadState get(LoadType loadType) {
    switch (loadType) {
      case REFRESH:
        return refresh();
      case PREPEND:
        return prepend();
      case APPEND:
        return append();
      default:
        throw new IllegalStateException("Unknown LoadType:" + loadType);
    }
  }

  public static Builder builder() {
    return new AutoValue_LoadStates.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder refresh(LoadState refresh);

    public abstract Builder prepend(LoadState prepend);

    public abstract Builder append(LoadState append);

    public abstract LoadStates build();
  }

  /**
   * Type of load a [PagingData] can trigger a [PagingSource] to perform.
   *
   * <p>[LoadState] of any [LoadType] may be observed for UI purposes by registering a listener via
   * [androidx.paging.PagingDataAdapter.addLoadStateListener] or
   * [androidx.paging.AsyncPagingDataDiffer.addLoadStateListener].
   *
   * @see LoadState
   */
  public enum LoadType {
    /**
     * [PagingData] content being refreshed, which can be a result of [PagingSource] invalidation,
     * refresh that may contain content updates, or the initial load.
     */
    REFRESH,

    /** Load at the start of a [PagingData]. */
    PREPEND,

    /** Load at the end of a [PagingData]. */
    APPEND
  }
}
