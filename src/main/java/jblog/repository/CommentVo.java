package jblog.repository;

import java.util.Date;

public class CommentVo {
	private Long cmtNo;
	private Long postNo;
	private Long userNo;
	private String userName;
	private String cmtContent;
	private Date regDate;
	
	public Long getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(Long cmtNo) {
		this.cmtNo = cmtNo;
	}
	public Long getPostNo() {
		return postNo;
	}
	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
