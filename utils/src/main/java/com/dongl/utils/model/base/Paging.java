package com.dongl.utils.model.base;

import java.io.Serializable;

/**
 * @Author: Dong.L
 * @Date: 2019/11/23 9:40
 * @Description: 分页
 */
public class Paging implements Serializable {

    private static final long serialVersionUID = 1L;

    private long total = 0;

    private int page = 1;

    private int pageSize = 20;

    private int pages = 0;

    public Paging() {
        this(1, 20);
    }

    public Paging(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    private void calculatePages() {
        total = total > 0 ? total : 0;
        pageSize = pageSize > 0 ? pageSize : 20;
        pages = Long.valueOf(total / pageSize + (total % pageSize == 0 ? 0 : 1)).intValue();
        page = pages >= 1 ? Math.min(Math.max(page, 1), pages) : 1;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        calculatePages();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getStart() {
        return Math.max((page - 1) * pageSize, 0);
    }

    @Override
    public String toString() {
        return "[page=" + page + ", pageSize=" + pageSize + "]";
    }

}
