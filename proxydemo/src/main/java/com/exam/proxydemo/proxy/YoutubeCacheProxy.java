package com.exam.proxydemo.proxy;

import com.exam.proxydemo.domain.Video;
import com.exam.proxydemo.service.ThirdPartyYoutubeClass;
import com.exam.proxydemo.service.ThirdPartyYoutubeLib;

import java.util.HashMap;

/**
 * 캐싱 프록시
 */
public class YoutubeCacheProxy implements ThirdPartyYoutubeLib {
  private ThirdPartyYoutubeLib youtubeService;
  private HashMap<String, Video> cachePopular = new HashMap<String, Video>();
  private HashMap<String, Video> cacheAll = new HashMap<String, Video>();

  /**
   * 프록시 객체를 생성할 때 서비스 인터페이스 타입으로 서비스 객체 생성
   */
  public YoutubeCacheProxy() {
    this.youtubeService = new ThirdPartyYoutubeClass();
  }

  @Override
  public HashMap<String, Video> popularVideos() {
    if (cachePopular.isEmpty()) {
      cachePopular = youtubeService.popularVideos();
    } else {
      System.out.println("Retrieved list from cache.");
    }
    return cachePopular;
  }

  @Override
  public Video getVideo(String videoId) {
    Video video = cacheAll.get(videoId);
    if (video == null) {
      video = youtubeService.getVideo(videoId);
      cacheAll.put(videoId, video);
    } else {
      System.out.println("Retrieved video '" + videoId + "' from cache.");
    }
    return video;
  }

  public void reset() {
    cachePopular.clear();
    cacheAll.clear();
  }
}
