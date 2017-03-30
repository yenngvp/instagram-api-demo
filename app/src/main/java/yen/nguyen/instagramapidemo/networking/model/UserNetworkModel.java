package yen.nguyen.instagramapidemo.networking.model;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public class UserNetworkModel {

    private Data data;

    public class Data {
        private String id;
        private String username;
        private String full_name;
        private String profile_picture;
        private String bio;
        private String website;

        private Counts counts;

        class Counts {
            private int media;
            private int follows;
            private int followed_by;
        }
    }
}
