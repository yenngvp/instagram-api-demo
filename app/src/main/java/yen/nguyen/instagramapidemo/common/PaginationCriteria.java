package yen.nguyen.instagramapidemo.common;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import yen.nguyen.instagramapidemo.utils.AppConstants;
import yen.nguyen.instagramapidemo.utils.DateTimeUtils;

/**
 * Created by yen.nguyen on 12/1/16.
 */

public final class PaginationCriteria {

    final private int page;
    final private int offset;
    final private int size;
    final private String name;
    final private String sortBy;
    final private boolean sortAscending;
    final private DateTime fromDate;
    final private DateTime toDate;
    final private boolean isScrolledDown;
    final private String searchKey;

    // Use builder to create criteria, please
    private PaginationCriteria(int page,
                               int offset,
                               int size,
                               String name,
                               String sortBy,
                               boolean sortAscending,
                               DateTime fromDate,
                               DateTime toDate,
                               boolean isScrolledDown,
                               String searchKey) {
        this.page = page;
        this.offset = offset;
        this.size = size;
        this.name = name;
        this.sortBy = sortBy;
        this.sortAscending = sortAscending;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isScrolledDown = isScrolledDown;
        this.searchKey = searchKey;
    }

    public int getPage() {
        if (page < 0) { // Page not set, calculate page by offset and scrolling direction
            if (getSize() > 0) {
                if (isScrolledDown()) {
                    return (getOffset() + getSize()) / getSize();
                } else {
                    int index = (getOffset() + getSize()) / getSize() - 1;
                    return Math.max(index, 0);
                }
            } else {
                return 1;
            }
        } else {
            return page;
        }
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getSortBy() {
        return sortBy;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public DateTime getFromDate() {
        return fromDate;
    }

    public DateTime getToDate() {
        return toDate;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isScrolledDown() {
        return isScrolledDown;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public static class Builder {
        private int mPage;
        private int mOffset;
        private int mSize;
        private String mName;
        private String mSortBy;
        private boolean mSortAscending;
        private DateTime mFromDate;
        private DateTime mToDate;
        private boolean mIsScrolledDown;
        private String mSearchKey;

        public Builder() {
            this.mPage = -1; // not using page index by default, will calculate it by offset
            this.mOffset = 0;
            this.mSize = AppConstants.PAGINATION_SIZE;
            this.mSortAscending = true;
            this.mIsScrolledDown = true;
        }

        public Builder page(int page) {
            this.mPage = page;
            return this;
        }

        public Builder offset(int offset) {
            this.mOffset = offset;
            return this;
        }

        public Builder size(int size) {
            this.mSize = size;
            return this;
        }

        public Builder name(String name) {
            this.mName = name;
            return this;
        }

        public Builder sortBy(String sortBy) {
            this.mSortBy = sortBy;
            return this;
        }

        public Builder sortAscending(boolean sortAscending) {
            this.mSortAscending = sortAscending;
            return this;
        }

        public Builder fromDate(DateTime fromDate) {
            this.mFromDate = fromDate;
            return this;
        }

        public Builder toDate(DateTime toDate) {
            this.mToDate = toDate;
            return this;
        }

        public Builder isScrolledDown(boolean isScrolledDown) {
            this.mIsScrolledDown = isScrolledDown;
            return this;
        }

        public Builder searchKey(String mSearchKey) {
            this.mSearchKey = mSearchKey;
            return this;
        }

        public PaginationCriteria build() {
            return new PaginationCriteria(mPage,
                    mOffset,
                    mSize,
                    mName,
                    mSortBy,
                    mSortAscending,
                    mFromDate,
                    mToDate,
                    mIsScrolledDown,
                    mSearchKey);
        }
    }

    public Map toQueryMap() {

        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(getPage()));
        map.put("size", String.valueOf(getSize()));
        map.put("sortBy", getSortBy());
        map.put("direction", isSortAscending() ? "ASC" : "DESC");
        map.put("from", DateTimeUtils.printWithTz(getFromDate()));
        map.put("to", DateTimeUtils.printWithTz(getToDate()));
        map.put("name", getName());

        return map;
    }

    @Override
    public String toString() {
        return toQueryMap().toString();
    }
}
