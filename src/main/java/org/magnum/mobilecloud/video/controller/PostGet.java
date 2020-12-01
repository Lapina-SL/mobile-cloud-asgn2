package org.magnum.mobilecloud.video.controller;

import com.google.common.collect.Lists;
import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostGet {

    @Autowired
    VideoRepository repository;

    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.GET)
    public @ResponseBody
    List<Video> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.POST)
    public @ResponseBody
    Video saveVideo(@RequestBody Video v) {
        return repository.save(v);
    }

    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Video getById(@PathVariable("id") long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = VideoSvcApi.VIDEO_TITLE_SEARCH_PATH, method = RequestMethod.GET)
    public @ResponseBody
    List<Video> getByTitle(@RequestParam("title") String title) {
        return repository.findByName(title);
    }

    @RequestMapping(value = VideoSvcApi.VIDEO_DURATION_SEARCH_PATH, method = RequestMethod.GET)
    public @ResponseBody
    List<Video> getByDurationLessThan(@RequestParam("duration") Long duration) {
        return repository.findByDurationLessThan(duration);
    }
}
