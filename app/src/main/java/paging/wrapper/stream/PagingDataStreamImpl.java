package paging.wrapper.stream;

import androidx.paging.PagingData;
import com.jakewharton.rxrelay2.BehaviorRelay;
import io.reactivex.Observable;
import javax.inject.Inject;
import paging.wrapper.model.data.Pokemon;

public class PagingDataStreamImpl implements PagingDataStream, PagingDataStreaming {

  private final BehaviorRelay<PagingData<Pokemon>> behaviorRelay = BehaviorRelay.create();

  @Inject
  public PagingDataStreamImpl() {}

  @Override
  public void accept(PagingData<Pokemon> data) {
    behaviorRelay.accept(data);
  }

  @Override
  public Observable<PagingData<Pokemon>> streaming() {
    return behaviorRelay.hide();
  }
}
