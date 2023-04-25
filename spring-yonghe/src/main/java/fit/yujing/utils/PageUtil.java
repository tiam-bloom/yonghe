package fit.yujing.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author Tiam
 * @date 2023/4/22 13:30
 * @description 分页模型
 */
public class PageUtil<T> {
    // 当前页
    private Integer currentPage = 1;
    // 每页条数
    private Integer pageSize = 10;
    // 总条数
    private Integer total;
    // 总页数
    private Integer totalPage;
    // 数据列表
    private List<T> list;

    public PageUtil() {
    }

    public PageUtil(Page<T> page) {
        this.currentPage = (int) page.getCurrent();
        this.pageSize = (int) page.getSize();
        this.total = (int) page.getTotal();
        this.totalPage = (int) page.getPages();
        this.list = page.getRecords();
    }

    public static <T> PageUtil<T> of(Page<T> page) {
        return new PageUtil<>(page);
    }

    public static <T>Page<T> of(PageUtil<T> mypage) {
        return new Page<>(mypage.getCurrentPage(), mypage.getPageSize());
    }
    public static <T>Page<T> of(Integer currentPage, Integer pageSize) {
        return new Page<>(currentPage, pageSize);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MyPage{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalList=" + total +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
