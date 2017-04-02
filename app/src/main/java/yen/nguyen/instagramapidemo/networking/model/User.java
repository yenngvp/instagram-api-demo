package yen.nguyen.instagramapidemo.networking.model;

/**
 * Created by yen.nguyen on 4/2/17.
 */

public class User {
    private String id;
    private String username;
    private String full_name;
    private String profile_picture;
    private String bio;
    private String website;
    private Counts counts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    class Counts {
        private int media;
        private int follows;
        private int followed_by;

        public int getMedia() {
            return media;
        }

        public void setMedia(int media) {
            this.media = media;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getFollowed_by() {
            return followed_by;
        }

        public void setFollowed_by(int followed_by) {
            this.followed_by = followed_by;
        }
    }
}