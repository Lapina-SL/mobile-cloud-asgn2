package org.magnum.mobilecloud.video.controller;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Controller
public class Likes {

    @Autowired
    VideoRepository repository;


    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}/like", method = RequestMethod.POST)
    public void like(@PathVariable("id") long id,
                     Principal user,
                     HttpServletResponse response) throws IOException {
        Video v = repository.findOne(id);
        String userName = user.getName();

        if (v == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (v.getLikedBy().contains(userName)) {
            response.sendError(400, "You have already liked this video before.");
        } else {
            likeAction(v, userName);
        }

    }


    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}/unlike", method = RequestMethod.POST)
    public void unlike(@PathVariable("id") long id,
                       Principal user,
                       HttpServletResponse response) throws IOException {
        Video v = repository.findOne(id);
        String userName = user.getName();

        if (v == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (v.getLikedBy().contains(userName)) {
            likeAction(v, userName);
        } else {
            response.sendError(400, "You haven't already liked this video before.");
        }
    }

    private Video likeAction(Video v, String userName) {
        Set<String> likedBy = v.getLikedBy();

        if (likedBy.contains(userName))
            likedBy.remove(userName);
        else
            likedBy.add(userName);

        v.setLikedBy(likedBy);
        v.setLikes(likedBy.size());
        return repository.save(v);
    }


}
