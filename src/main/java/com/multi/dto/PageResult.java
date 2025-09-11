package com.multi.dto;

import java.util.List;

public class PageResult<T> {
    private final List<T> items;
    private final int page;
    private final int size;
    private final long totalCount;

    public PageResult(List<T> items, int page, int size, long totalCount) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
    }

    public List<T> getItems() { return items;}

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalCount() {
        return totalCount;
    }
    public int getTotalPages() {
        return (int)Math.ceil((double)totalCount / size);
    }
}
