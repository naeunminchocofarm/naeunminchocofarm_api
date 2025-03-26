package com.naeunminchocofarm.ncf_api.lib.pagination;

public class Pagination {
    private final Integer size;
    private final Integer page;
    private final Integer skip;

    public Pagination(Integer size, Integer page) {
        if (size == null || size < 1) {
            throw new IllegalArgumentException("페이지네이션 size의 값은 1이상이어야 합니다.");
        }

        if (page == null || page < 1) {
            throw new IllegalArgumentException("페이지네이션 page의 값은 1이상이어야 합니다.");
        }

        this.size = size;
        this.page = page;
        this.skip = (page - 1) * size;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSkip() {
        return skip;
    }
}
