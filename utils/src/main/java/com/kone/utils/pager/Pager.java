package com.kone.utils.pager;

public class Pager {

    private Long total;//总共条数

    private Long num = 0L; //第几页

    private int size = 10; // 每页大小

    private Long page; // 总共多少页

    private Long start;//开始页数

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "total=" + total +
                ", num=" + num +
                ", size=" + size +
                ", page=" + page +
                ", start=" + start +
                '}';
    }
}
