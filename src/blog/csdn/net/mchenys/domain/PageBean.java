package blog.csdn.net.mchenys.domain;

import java.util.List;

public class PageBean<T> {
	private List<T> list;
	private Integer pageNo;
	private Integer pageSize;
	private Integer pageTotal;
	private Integer total;
	
	public PageBean(List<T> list, Integer pageNo, Integer pageSize, Integer total) {
		super();
		this.list = list;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageTotal() {
		return (int) Math.ceil(this.total /(double)this.pageSize);
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
