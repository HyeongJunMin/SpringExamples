package com.exam.proxydemo.service;

import com.exam.proxydemo.domain.Video;

import java.util.HashMap;

/**
 * 서비스 인터페이스
 */
public interface ThirdPartyYoutubeLib {
  HashMap<String, Video> popularVideos();

  Video getVideo(String videoId);
}