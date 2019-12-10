package jblog.repository;

import java.util.Date;

public class CategoryVo {
	private Long cateNo;
	private Long userNo;
	private String cateName;
	private String description;
	private Date regDate;
	private int postCount;
	public Long getCateNo() {
		return cateNo;
	}
	public void setCateNo(Long cateNo) {
		this.cateNo = cateNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", userNo=" + userNo + ", cateName=" + cateName + ", description="
				+ description + ", regDate=" + regDate + "]";
	}
}
