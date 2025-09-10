package com.multi.dto;

public class PageRequest {
    private int page;
    private int size;

    public PageRequest(int page, int size) {
        this.page = Math.max(page, 1);
        this.size = Math.max(size, 1);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }
    public int getSize() {
        return size;
    }
    public int getOffset() {
        return (page - 1) * size;
    }
}
