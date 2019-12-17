package com.exam.proxydemo.init;

import com.exam.proxydemo.proxy.YoutubeCacheProxy;
import com.exam.proxydemo.service.ThirdPartyYoutubeClass;
import com.exam.proxydemo.util.YoutubeDownloader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InitTest {

  @Test
  public void mainInitTest() {
    YoutubeDownloader naiveDownloader = new YoutubeDownloader(new ThirdPartyYoutubeClass());
    YoutubeDownloader smartDownloader = new YoutubeDownloader(new YoutubeCacheProxy());

    long naive = tester(naiveDownloader);
    long smart = tester(smartDownloader);
    System.out.println("Time saved by caching proxy: " + (naive - smart) + "ms");
  }

  public static long tester(YoutubeDownloader downloader) {
    long startTime = System.currentTimeMillis();

    // User behavior in our app:
    downloader.renderPopularVideos();
    downloader.renderVideoPage("catzzzzzzzzz");
    downloader.renderPopularVideos();
    downloader.renderVideoPage("dancesvideoo");
    // Users might visit the same page quite often.
    downloader.renderVideoPage("catzzzzzzzzz");
    downloader.renderVideoPage("someothervid");

    long estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Time elapsed = " + estimatedTime + "ms");
    return estimatedTime;
  }
}
