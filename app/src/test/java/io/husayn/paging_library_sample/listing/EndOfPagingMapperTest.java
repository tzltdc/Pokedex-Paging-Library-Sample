package io.husayn.paging_library_sample.listing;

import static org.junit.Assert.*;

import com.google.common.truth.Truth;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class EndOfPagingMapperTest {

  @Test
  public void endOfPaging() {
    Truth.assertThat(
            EndOfPagingMapper.endOfPaging(PagingAction.create(initialRequest(), emptyResponse())))
        .isTrue();
  }

  private SearchPokemonResponse emptyResponse() {
    return SearchPokemonResponse.create(Collections.emptyList());
  }

  private PagingRequest initialRequest() {
    return PagingRequest.create(0, PagingQuery.create(null), PagingQueryConfig.create(10));
  }
}
