package io.stream.paging;

import java.util.List;
import paging.wrapper.model.data.Pokemon;

public interface PagingDataListSnapshotProvider {

  List<Pokemon> snapshot();
}
