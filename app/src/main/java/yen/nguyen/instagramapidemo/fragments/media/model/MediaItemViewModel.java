package yen.nguyen.instagramapidemo.fragments.media.model;

/**
 * Created by yen.nguyen on 3/30/17.
 */

public class MediaItemViewModel {
    public static final int TYPE_GROUP_HEADER = 1;
    public static final int TYPE_ITEM = 2;

    private Long id;
    private String name;
    private int viewType;
    private String entityId;
    private String mediaType;
    private boolean watched;
    private boolean watching;
    private int downloadPercentage;
    private String tags;

    public MediaItemViewModel() {
    }


}
