package io.husayn.paging_library_sample.listing;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava2.PagingRx;
import io.husayn.paging_library_sample.data.Pokemon;
import io.reactivex.Observable;
import javax.inject.Inject;

public class PagingPokemonRepo {

  private static final int INITIAL_LOAD_KEY = 0;

  private final PokemonRepo pokemonRepo;
  private final PagingConfig androidPagingConfig;
  private final PokemonRemoteMediatorFactory pokemonRemoteMediatorFactory;
  private final QueryStreaming queryStreaming;

  @Inject
  public PagingPokemonRepo(
      PokemonRepo pokemonRepo,
      PagingConfig androidPagingConfig,
      PokemonRemoteMediatorFactory pokemonRemoteMediatorFactory,
      QueryStreaming queryStreaming) {
    this.pokemonRepo = pokemonRepo;
    this.androidPagingConfig = androidPagingConfig;
    this.pokemonRemoteMediatorFactory = pokemonRemoteMediatorFactory;
    this.queryStreaming = queryStreaming;
  }

  public Observable<PagingData<Pokemon>> rxPagingData() {
    return queryStreaming
        .streaming()
        .map(this::pagerContext)
        .map(this::pager)
        .switchMap(PagingRx::getObservable);
  }

  private PagerContext pagerContext(PagingQuery query) {
    return PagerContext.create(
        pokemonRemoteMediatorFactory.create(query),
        () -> pokemonRepo.pokemonLocalPagingSource(query));
  }

  private Pager<Integer, Pokemon> pager(PagerContext context) {
    return new Pager<>(
        androidPagingConfig,
        INITIAL_LOAD_KEY,
        context.pokemonRemoteMediator(),
        context.localPagingSource());
  }
}
