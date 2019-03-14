package com.kone.utils.pager;

public class PagerUtils {
    public static Pager getPager(Pager pager) {
        if(pager.getNum() < 0) {
            pager.setNum(0L);
        }
//        计算页数，页数不取整加一
        Long page = pager.getTotal() / pager.getSize();
        if(pager.getTotal() % pager.getSize() != 0) {
            page += 1;
        }
        pager.setPage(page);

        if(pager.getNum() >= pager.getPage()) {
            pager.setNum(pager.getPage());
        }
        return pager;
    }
}
