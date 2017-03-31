package yen.nguyen.instagramapidemo.fragments.media.model;

import java.util.List;

import yen.nguyen.instagramapidemo.networking.model.Media;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public class MediaItemViewModel {
    public static final int TYPE_GROUP_HEADER = 1;
    public static final int TYPE_ITEM = 2;

    private Long id;

    private String type;
    private List<String> usersInPhoto;
    private String filter;
    private List<String> tags;
    private String link;
    private Media images;
    private Media videos;
    private String mediaId;

    public MediaItemViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getUsersInPhoto() {
        return usersInPhoto;
    }

    public void setUsersInPhoto(List<String> usersInPhoto) {
        this.usersInPhoto = usersInPhoto;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Media getImages() {
        return images;
    }

    public void setImages(Media images) {
        this.images = images;
    }

    public Media getVideos() {
        return videos;
    }

    public void setVideos(Media videos) {
        this.videos = videos;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
