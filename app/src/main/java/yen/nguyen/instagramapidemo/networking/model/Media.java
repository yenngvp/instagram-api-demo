package yen.nguyen.instagramapidemo.networking.model;

/**
 * Created by yen.nguyen on 3/31/17.
 */

public class Media {

    private MediaDetails low_resolution;
    private MediaDetails thumbnail;
    private MediaDetails standard_resolution;

    public MediaDetails getLow_resolution() {
        return low_resolution;
    }

    public MediaDetails getThumbnail() {
        return thumbnail;
    }

    public MediaDetails getStandard_resolution() {
        return standard_resolution;
    }

    public class MediaDetails {
        private String url;
        private int width;
        private int height;

        public String getUrl() {
            return url;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
