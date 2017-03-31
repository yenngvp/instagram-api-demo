package yen.nguyen.instagramapidemo.networking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

            return model;
        }
    }
}
