package com.kdj.mlink.ezup3.data.dao;

import com.kdj.mlink.ezup3.common.YDMAStringUtil;

public class UserDto {

	private String rowno; // UI 그리드 순번 (디비컬럼x)

	private String userid;
	private String usernam;
	private String userpwd;
	private String email;
	private String progcd;
	private String useyn;
	private String compno;

	public String getRwono() {
		return rowno;
	}

	public void setRowno(String no) {
		this.rowno = YDMAStringUtil.replaceNullvalue(no);
	}

	public String getUserId() {
		return userid;
	}

	public void setUserId(String userid) {
		this.userid = YDMAStringUtil.replaceNullvalue(userid);
	}

	public String getUsernam() {
		return usernam;
	}

	public void setUsernam(String usernam) {
		this.usernam = YDMAStringUtil.replaceNullvalue(usernam);
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = YDMAStringUtil.replaceNullvalue(userpwd);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = YDMAStringUtil.replaceNullvalue(email);
	}

	public String getProgcd() {
		return progcd;
	}

	public void setProgcd(String progcd) {
		this.progcd = YDMAStringUtil.replaceNullvalue(progcd);
	}

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = YDMAStringUtil.replaceNullvalue(useyn);
	}

	public String getCompno() {
		return compno;
	}

	public void setCompno(String compno) {
		this.compno = YDMAStringUtil.replaceNullvalue(compno);
	}
}
