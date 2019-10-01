package com.example.demo.form;

/**
 * ページングフォーム.
 * 
 * @author otsuka
 *
 */
public class pagingFrom {

	/**リンクページ*/
	private Integer page;
	
	
	/**名前*/
	private String name;


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "pagingFrom [page=" + page + ", name=" + name + "]";
	}
	
	
	
}
