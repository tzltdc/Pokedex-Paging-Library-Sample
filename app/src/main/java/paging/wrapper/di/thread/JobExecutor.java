package paging.wrapper.di.thread;

import io.reactivex.annotations.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

class JobExecutor implements ThreadExecutor {

  static final int INITIAL_POOL_SIZE = 3;
  static final int MAX_POOL_SIZE = 5;
  static final int KEEP_ALIVE_TIME = 10;
  static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

  private final ExecutorService executorService;

  @Inject
  public JobExecutor() {
    executorService = prodThreadPoolExecutor();
  }

  @androidx.annotation.NonNull
  private static ThreadPoolExecutor prodThreadPoolExecutor() {
    return new ThreadPoolExecutor(
        INITIAL_POOL_SIZE,
        MAX_POOL_SIZE,
        KEEP_ALIVE_TIME,
        KEEP_ALIVE_TIME_UNIT,
        new LinkedBlockingQueue<>(),
        new JobThreadFactory());
  }

  @Override
  public void execute(@NonNull Runnable runnable) {
    this.executorService.execute(runnable);
  }

  static class JobThreadFactory implements ThreadFactory {

    static final String THREAD_NAME = "android_";
    private int counter = 0;

    @Override
    public Thread newThread(@NonNull Runnable runnable) {
      return new Thread(runnable, THREAD_NAME + counter++);
    }
  }
}
