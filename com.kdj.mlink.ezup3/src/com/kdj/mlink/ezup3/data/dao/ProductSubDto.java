package com.kdj.mlink.ezup3.data.dao;

import com.kdj.mlink.ezup3.common.YDMAStringUtil;

public class ProductSubDto {

	private String rowno; // UI �׸��� ���� (����÷�x)

	private String prodcd; // ��ǰ����Ÿ �ڵ�
	private String proddtcd; // ��ǰ�� �ڵ�
	private String proddtnm; // ��ǰ�� ��Ī
	private String specdtdes; // �԰�

	private String falgset; // ��Ʈ����
	private String qtyset; // ��Ʈ����
	private String levset; // ��Ʈ����

	private String expdtcd; // �ù��

	private String useyn;
	private String delyn;
	private String insertdt;
	private String insertid;
	private String modifydt;
	private String modifyid;

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = YDMAStringUtil.replaceNullvalue(rowno);
		;
	}

	public String getProdcd() {
		return prodcd;
	}

	public void setProdcd(String prodcd) {
		this.prodcd = YDMAStringUtil.replaceNullvalue(prodcd);
	}

	public String getProddtcd() {
		return proddtcd;
	}

	public void setProddtcd(String proddtcd) {
		this.proddtcd = YDMAStringUtil.replaceNullvalue(proddtcd);
	}

	public String getProddtnm() {
		return proddtnm;
	}

	public void setProddtnm(String proddtnm) {
		this.proddtnm = YDMAStringUtil.replaceNullvalue(proddtnm);
	}

	public String getSpecdtdes() {
		return specdtdes;
	}

	public void setSpecdtdes(String specdtdes) {
		this.specdtdes = YDMAStringUtil.replaceNullvalue(specdtdes);
	}

	public String getFalgset() {
		return falgset;
	}

	public void setFalgset(String falgset) {
		this.falgset = YDMAStringUtil.replaceNullvalue(falgset);
	}

	public String getLevset() {
		return levset;
	}

	public void setLevset(String levset) {
		this.levset = YDMAStringUtil.replaceNullvalue(levset);
	}

	public String getQtyset() {
		return qtyset;
	}

	public void setQtyset(String qtyset) {
		this.qtyset = YDMAStringUtil.replaceNullvalue(qtyset);
	}

	public String getExpdtcd() {
		return expdtcd;
	}

	public void setExpdtcd(String expdtcd) {
		this.expdtcd = YDMAStringUtil.replaceNullvalue(expdtcd);
		;
	}

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = YDMAStringUtil.replaceNullvalue(useyn);
		;
	}

	public String getDelyn() {
		return delyn;
	}

	public void setDelyn(String delyn) {
		this.delyn = YDMAStringUtil.replaceNullvalue(delyn);
	}

	public String getInsertdt() {
		return insertdt;
	}

	public void setInsertdt(String insertdt) {
		this.insertdt = YDMAStringUtil.replaceNullvalue(insertdt);
	}

	public String getInsertid() {
		return insertid;
	}

	public void setInsertid(String insertid) {
		this.insertid = YDMAStringUtil.replaceNullvalue(insertid);
	}

	public String getModifydt() {
		return modifydt;
	}

	public void setModifydt(String modifydt) {
		this.modifydt = YDMAStringUtil.replaceNullvalue(modifydt);
	}

	public String getModifyid() {
		return modifyid;
	}

	public void setModifyid(String modifyid) {
		this.modifyid = YDMAStringUtil.replaceNullvalue(modifyid);
	}
}
