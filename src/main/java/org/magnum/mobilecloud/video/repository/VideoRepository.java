package org.magnum.mobilecloud.video.repository;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "videos",path = VideoSvcApi.VIDEO_SVC_PATH)
public interface VideoRepository extends CrudRepository<Video, Long> {

    public List<Video> findByName(@Param(VideoSvcApi.TITLE_PARAMETER) String title);

    public List<Video> findByDurationLessThan(@Param(VideoSvcApi.DURATION_PARAMETER)Long maxDuration);

}

