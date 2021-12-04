package paging.wrapper.data;

import androidx.paging.Pager;
import paging.wrapper.model.data.PagingQueryContext;
import paging.wrapper.model.data.Pokemon;

public interface PagerFactory {

  Pager<Integer, Pokemon> pager(PagingQueryContext query);
}
