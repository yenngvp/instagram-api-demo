package yen.nguyen.instagramapidemo.networking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yen.nguyen.instagramapidemo.fragments.media.model.MediaItemViewModel;

/**
 * Created by yen.nguyen on 3/31/17.
 */

public class SearchMediaNetworkModel {

    private List<SearchMedia> data;

    public List<SearchMedia> getData() {
        return data;
    }

    public void setData(List<SearchMedia> data) {
        this.data = data;
    }

    public List<MediaItemViewModel> toListOfMediaItemViewModels() {
        if (data == null) {
            return Collections.emptyList();
        }
        List<MediaItemViewModel> results = new ArrayList<>();
        for (SearchMedia searchMedia : data) {
            results.add(searchMedia.toMediaItemViewModel());
        }

        return results;
    }

    public class SearchMedia {
        private String type;
        private List<String> users_in_photo;
        private String filter;
        private List<String> tags;
        private String link;
        private Media images;
        private Media videos;
        private String id;
        private User user;
        private Map<String, Integer> likes;
        private Map<String, Integer> comments;
        private long created_time;
        private boolean user_has_liked;
        private Map<String, Object> location;

        public String getType() {
            return type;
        }

        public List<String> getUsers_in_photo() {
            return users_in_photo;
        }

        public String getFilter() {
            return filter;
        }

        public List<String> getTags() {
            return tags;
        }

        public String getLink() {
            return link;
        }

        public Media getImages() {
            return images;
        }

        public Media getVideos() {
            return videos;
        }

        public String getId() {
            return id;
        }

        public User getUser() {
            return user;
        }

        public Map<String, Integer> getLikes() {
            return likes;
        }

        public Map<String, Integer> getComments() {
            return comments;
        }

        public long getCreated_time() {
            return created_time;
        }

        public boolean isUser_has_liked() {
            return user_has_liked;
        }

        public Map<String, Object> getLocation() {
            return location;
        }

        public MediaItemViewModel toMediaItemViewModel() {
            MediaItemViewModel model = new MediaItemViewModel();
            model.setMediaId(getId());
            model.setType(getType());
            model.setUsersInPhoto(getUsers_in_photo());
            model.setFilter(getFilter());
            model.setTags(getTags());
            model.setLink(getLink());
            model.setImages(getImages());
            model.setVideos(getVideos());
            model.setUser(getUser());
            model.setLikeCount(getLikes().get("count"));
            model.setCommentCount(getComments().get("count"));
            model.setCreatedTime(getCreated_time());
            model.setUserHasLiked(isUser_has_liked());
            if (getLocation() != null && getLocation().size() > 0) {
                model.setLocationName(String.valueOf(getLocation().get("name")));
            }
            return model;
        }
    }
}
