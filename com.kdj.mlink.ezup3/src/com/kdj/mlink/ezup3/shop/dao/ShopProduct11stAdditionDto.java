package com.kdj.mlink.ezup3.shop.dao;

/*
 * 11 ���� �ΰ� ���� 
 */
public class ShopProduct11stAdditionDto extends ShopProductAdditionDto {
	private String seq;//�Ϸù�ȣ
	private String compno;//ȸ���ڵ�
	private String shopcd;//���θ��ڵ�
	private String title;//����
	private String memo;//�޸�
	private String shopid;//���̵�
	private String selmthdcd;//�ǸŹ��
	private String mall_var_1;//��ǰȫ������
	private String prodtypcd;//���񽺻�ǰ
	private String mall_var_2;//��������ǰ
	private String mall_var_3;//��������뿩��
	private String mall_var_4;//��������뿩�ο���������
	private String mall_var_5;//���������ߵ�Ͽ���
	private String mall_var_6;//��ǰ��
	private String mall_var_7;//��ǰ������
	private String mall_var_8;//�Ǹ��ڻ�ǰ�ڵ�
	private String mall_var_9;//����ĳ�û�뿩��
	private String prodstatcd;//��ǰ����
	private String minorselcnyn;//�̼����ڱ��ſ���
	private String mall_var_10;//��������
	private String mall_var_11;//����Ͻø����
	private String nicknm;//�г���
	private String mall_var_12;//�귣��
	private String mall_var_13;//���Խ�ûURL
	private String mall_var_14;//�޴��������
	private String mall_var_15;//��ȿ����
	private String mall_var_16;//���ݺ񱳵�Ͽ���
	private String fpseltermyn;//�ǸűⰣ��������
	private String prodfpselcd;//�ǸűⰣ�ڵ�
	private String mall_var_17;//1������ǰ����
	private String mall_var_18;//�ɼǼ�������
	private String mall_var_19;//2�ܿɼ����뿩��
	private String mall_var_20;//�ɼǰ�������
	private String mall_var_21;//�ۼ����ɼǿ���
	private String mall_var_22;//�ɼǸ�1
	private String mall_var_23;//�ɼǸ�2
	private String mall_var_24;//�ɼǸ�3
	private String mall_var_25;//�ɼǸ�4
	private String mall_var_26;//�ɼǸ�5
	private String mall_var_27;//�ɼǸ�6
	private String mall_var_28;//�ɼǸ�7
	private String mall_var_29;//�ɼǸ�8
	private String mall_var_30;//�ɼǸ�9
	private String mall_var_31;//�ɼǸ�10
	private String mall_var_32;//���ް���ǰURL
	private String mall_var_33;//���������
	private String mall_var_34;//�����������
	private String mall_var_35;//�����Ǿ����ο���
	private String mall_var_36;//�����Ǿ����αݾ׹���
	private String mall_var_37;//�����ǾƱⰣ��������
	private String mall_var_38;//�����Ǿƽ�����
	private String mall_var_39;//�����Ǿ�������
	private String mall_var_40;//�ּұ��ż������ѿ���
	private String mall_var_41;//�ּұ������ѽü���
	private String mall_var_42;//�ִ뱸�ż������ѿ���
	private String mall_var_43;//1ȸ���ѽü���
	private String mall_var_44;//�Ⱓ���ѽñⰣ
	private String mall_var_45;//�Ⱓ���ѽü���
	private String mall_var_46;//����ǰ��
	private String mall_var_47;//����ǰ�Ⱓ������
	private String mall_var_49;//����ǰ�Ⱓ������
	private String mall_var_50;//����ǰ����
	private String mall_var_51;//����������ø�
	private String mall_var_52;//��۰�������
	private String expwycd;//��۹��
	private String sendexp;//�߼��ù��
	private String mall_var_53;//�߼ۿ�����
	private String mall_var_54;//�湮�����ּ�
	private String gbldivyn;//�������ۿ���
	private String gblwght;//�������۹���
	private String hscd;//HS�ڵ�
	private String mall_var_55;//������ּ��ؿܿ���
	private String addrout;//����ּ�
	private String mall_var_56;//��ȯ��ǰ�ּ��ؿܿ���
	private String addrin;//��ȯ/��ǰ�ּ�
	private String shiptypcd;//��ۺ�����
	private String shipprc;//�⺻��ۺ�
	private String condiprice;//��ǰ���Ǻ��� �ڿ��ݾ�
	private String mall_var_57;//�����������1
	private String mall_var_58;//�����������2
	private String diffprice1;//��������ݾ�1
	private String mall_var_59;//��������ݾ�2
	private String jejuprc;//���ֹ�ۺ�
	private String islandprc;//�����갣��ۺ�
	private String prctypcd;//����������
	private String bndyn;//������ۿ���
	private String retprc;//��ǰ��ۺ�
	private String excprc;//��ȯ��ۺ�
	private String rudcd;//�պ�������
	private String asdtl;//A/S�ȳ�
	private String rtexcdtl;//��ȯ/��ǰ�ȳ�
	private String mall_var_60;//�����������ο���
	private String mall_var_61;//�������α���
	private String mall_var_62;//�������αݾ׹װ���1
	private String mall_var_63;//�������αݾ׹װ���2
	private String mall_var_64;//�������ι������
	private String mall_var_65;//����ó����
	private String mall_var_66;//��ǥ�̹���
	private String mall_var_67;//����ϻ󼼼����̹�������
	private String mall_var_68;//������������ÿ���
	private String mall_var_69;//�ؿܱ��Ŵ����ǰ����
	private String mall_var_70;//��ǰ����߰�����
	private String mall_var_71;//��ǰ����߰�����
	private String mall_var_72;//��ǰ�������߰�����
	private String mall_var_73;//��ǰ�����ϴ��߰�����
	private String mall_var_74;//�⺻������ο���
	private String mall_var_75;//�⺻������αݾ�
	private String mall_var_76;//�⺻������α���
	private String mall_var_77;//�������ޱⰣ��������
	private String mall_var_78;//�������ޱⰣ������
	private String mall_var_79;//�������Һ���������
	private String mall_var_80;//�������Һΰ�����
	private String mall_var_81;//�������Һ�ȸ������
	private String mall_var_82;//������μ�������
	private String mall_var_83;//����Ŀ��ݾ�
	private String mall_var_84;//����Ŀ����α���
	private String mall_var_85;//SK����Ʈ���޿���
	private String mall_var_86;//SK����Ʈ�ݾ�
	private String mall_var_87;//SK����Ʈ���α���
	private String mall_var_88;//��ǰ���俩��
	private String mall_var_89;//�÷���������
	private String mall_var_90;//�÷���UP������
	private String mall_var_91;//GIF������
	private String mall_var_92;//����ü������
	private String mall_var_93;//����������
	private String mall_var_94;//�޴�����������
	private String mall_var_95;//�湮����
	private String useyn;//��뿩��
	
	
	public String getMall_var_95() {
		return mall_var_95;
	}
	public void setMall_var_95(String mall_var_95) {
		this.mall_var_95 = mall_var_95;
	}
	public String getMall_var_94() {
		return mall_var_94;
	}
	public void setMall_var_94(String mall_var_94) {
		this.mall_var_94 = mall_var_94;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCompno() {
		return compno;
	}
	public void setCompno(String compno) {
		this.compno = compno;
	}
	public String getShopcd() {
		return shopcd;
	}
	public void setShopcd(String shopcd) {
		this.shopcd = shopcd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getSelmthdcd() {
		return selmthdcd;
	}
	public void setSelmthdcd(String selmthdcd) {
		this.selmthdcd = selmthdcd;
	}
	public String getMall_var_1() {
		return mall_var_1;
	}
	public void setMall_var_1(String mall_var_1) {
		this.mall_var_1 = mall_var_1;
	}
	public String getProdtypcd() {
		return prodtypcd;
	}
	public void setProdtypcd(String prodtypcd) {
		this.prodtypcd = prodtypcd;
	}
	public String getMall_var_2() {
		return mall_var_2;
	}
	public void setMall_var_2(String mall_var_2) {
		this.mall_var_2 = mall_var_2;
	}
	public String getMall_var_3() {
		return mall_var_3;
	}
	public void setMall_var_3(String mall_var_3) {
		this.mall_var_3 = mall_var_3;
	}
	public String getMall_var_4() {
		return mall_var_4;
	}
	public void setMall_var_4(String mall_var_4) {
		this.mall_var_4 = mall_var_4;
	}
	public String getMall_var_5() {
		return mall_var_5;
	}
	public void setMall_var_5(String mall_var_5) {
		this.mall_var_5 = mall_var_5;
	}
	public String getMall_var_6() {
		return mall_var_6;
	}
	public void setMall_var_6(String mall_var_6) {
		this.mall_var_6 = mall_var_6;
	}
	public String getMall_var_7() {
		return mall_var_7;
	}
	public void setMall_var_7(String mall_var_7) {
		this.mall_var_7 = mall_var_7;
	}
	public String getMall_var_8() {
		return mall_var_8;
	}
	public void setMall_var_8(String mall_var_8) {
		this.mall_var_8 = mall_var_8;
	}
	public String getMall_var_9() {
		return mall_var_9;
	}
	public void setMall_var_9(String mall_var_9) {
		this.mall_var_9 = mall_var_9;
	}
	public String getProdstatcd() {
		return prodstatcd;
	}
	public void setProdstatcd(String prodstatcd) {
		this.prodstatcd = prodstatcd;
	}
	public String getMinorselcnyn() {
		return minorselcnyn;
	}
	public void setMinorselcnyn(String minorselcnyn) {
		this.minorselcnyn = minorselcnyn;
	}
	public String getMall_var_10() {
		return mall_var_10;
	}
	public void setMall_var_10(String mall_var_10) {
		this.mall_var_10 = mall_var_10;
	}
	public String getMall_var_11() {
		return mall_var_11;
	}
	public void setMall_var_11(String mall_var_11) {
		this.mall_var_11 = mall_var_11;
	}
	public String getNicknm() {
		return nicknm;
	}
	public void setNicknm(String nicknm) {
		this.nicknm = nicknm;
	}
	public String getMall_var_12() {
		return mall_var_12;
	}
	public void setMall_var_12(String mall_var_12) {
		this.mall_var_12 = mall_var_12;
	}
	public String getMall_var_13() {
		return mall_var_13;
	}
	public void setMall_var_13(String mall_var_13) {
		this.mall_var_13 = mall_var_13;
	}
	public String getMall_var_14() {
		return mall_var_14;
	}
	public void setMall_var_14(String mall_var_14) {
		this.mall_var_14 = mall_var_14;
	}
	public String getMall_var_15() {
		return mall_var_15;
	}
	public void setMall_var_15(String mall_var_15) {
		this.mall_var_15 = mall_var_15;
	}
	public String getMall_var_16() {
		return mall_var_16;
	}
	public void setMall_var_16(String mall_var_16) {
		this.mall_var_16 = mall_var_16;
	}
	public String getFpseltermyn() {
		return fpseltermyn;
	}
	public void setFpseltermyn(String fpseltermyn) {
		this.fpseltermyn = fpseltermyn;
	}
	public String getProdfpselcd() {
		return prodfpselcd;
	}
	public void setProdfpselcd(String prodfpselcd) {
		this.prodfpselcd = prodfpselcd;
	}
	public String getMall_var_17() {
		return mall_var_17;
	}
	public void setMall_var_17(String mall_var_17) {
		this.mall_var_17 = mall_var_17;
	}
	public String getMall_var_18() {
		return mall_var_18;
	}
	public void setMall_var_18(String mall_var_18) {
		this.mall_var_18 = mall_var_18;
	}
	public String getMall_var_19() {
		return mall_var_19;
	}
	public void setMall_var_19(String mall_var_19) {
		this.mall_var_19 = mall_var_19;
	}
	public String getMall_var_20() {
		return mall_var_20;
	}
	public void setMall_var_20(String mall_var_20) {
		this.mall_var_20 = mall_var_20;
	}
	public String getMall_var_21() {
		return mall_var_21;
	}
	public void setMall_var_21(String mall_var_21) {
		this.mall_var_21 = mall_var_21;
	}
	public String getMall_var_22() {
		return mall_var_22;
	}
	public void setMall_var_22(String mall_var_22) {
		this.mall_var_22 = mall_var_22;
	}
	public String getMall_var_23() {
		return mall_var_23;
	}
	public void setMall_var_23(String mall_var_23) {
		this.mall_var_23 = mall_var_23;
	}
	public String getMall_var_24() {
		return mall_var_24;
	}
	public void setMall_var_24(String mall_var_24) {
		this.mall_var_24 = mall_var_24;
	}
	public String getMall_var_25() {
		return mall_var_25;
	}
	public void setMall_var_25(String mall_var_25) {
		this.mall_var_25 = mall_var_25;
	}
	public String getMall_var_26() {
		return mall_var_26;
	}
	public void setMall_var_26(String mall_var_26) {
		this.mall_var_26 = mall_var_26;
	}
	public String getMall_var_27() {
		return mall_var_27;
	}
	public void setMall_var_27(String mall_var_27) {
		this.mall_var_27 = mall_var_27;
	}
	public String getMall_var_28() {
		return mall_var_28;
	}
	public void setMall_var_28(String mall_var_28) {
		this.mall_var_28 = mall_var_28;
	}
	public String getMall_var_29() {
		return mall_var_29;
	}
	public void setMall_var_29(String mall_var_29) {
		this.mall_var_29 = mall_var_29;
	}
	public String getMall_var_30() {
		return mall_var_30;
	}
	public void setMall_var_30(String mall_var_30) {
		this.mall_var_30 = mall_var_30;
	}
	public String getMall_var_31() {
		return mall_var_31;
	}
	public void setMall_var_31(String mall_var_31) {
		this.mall_var_31 = mall_var_31;
	}
	public String getMall_var_32() {
		return mall_var_32;
	}
	public void setMall_var_32(String mall_var_32) {
		this.mall_var_32 = mall_var_32;
	}
	public String getMall_var_33() {
		return mall_var_33;
	}
	public void setMall_var_33(String mall_var_33) {
		this.mall_var_33 = mall_var_33;
	}
	public String getMall_var_34() {
		return mall_var_34;
	}
	public void setMall_var_34(String mall_var_34) {
		this.mall_var_34 = mall_var_34;
	}
	public String getMall_var_35() {
		return mall_var_35;
	}
	public void setMall_var_35(String mall_var_35) {
		this.mall_var_35 = mall_var_35;
	}
	public String getMall_var_36() {
		return mall_var_36;
	}
	public void setMall_var_36(String mall_var_36) {
		this.mall_var_36 = mall_var_36;
	}
	public String getMall_var_37() {
		return mall_var_37;
	}
	public void setMall_var_37(String mall_var_37) {
		this.mall_var_37 = mall_var_37;
	}
	public String getMall_var_38() {
		return mall_var_38;
	}
	public void setMall_var_38(String mall_var_38) {
		this.mall_var_38 = mall_var_38;
	}
	public String getMall_var_39() {
		return mall_var_39;
	}
	public void setMall_var_39(String mall_var_39) {
		this.mall_var_39 = mall_var_39;
	}
	public String getMall_var_40() {
		return mall_var_40;
	}
	public void setMall_var_40(String mall_var_40) {
		this.mall_var_40 = mall_var_40;
	}
	public String getMall_var_41() {
		return mall_var_41;
	}
	public void setMall_var_41(String mall_var_41) {
		this.mall_var_41 = mall_var_41;
	}
	public String getMall_var_42() {
		return mall_var_42;
	}
	public void setMall_var_42(String mall_var_42) {
		this.mall_var_42 = mall_var_42;
	}
	public String getMall_var_43() {
		return mall_var_43;
	}
	public void setMall_var_43(String mall_var_43) {
		this.mall_var_43 = mall_var_43;
	}
	public String getMall_var_44() {
		return mall_var_44;
	}
	public void setMall_var_44(String mall_var_44) {
		this.mall_var_44 = mall_var_44;
	}
	public String getMall_var_45() {
		return mall_var_45;
	}
	public void setMall_var_45(String mall_var_45) {
		this.mall_var_45 = mall_var_45;
	}
	public String getMall_var_46() {
		return mall_var_46;
	}
	public void setMall_var_46(String mall_var_46) {
		this.mall_var_46 = mall_var_46;
	}
	public String getMall_var_47() {
		return mall_var_47;
	}
	public void setMall_var_47(String mall_var_47) {
		this.mall_var_47 = mall_var_47;
	}
	public String getMall_var_49() {
		return mall_var_49;
	}
	public void setMall_var_49(String mall_var_49) {
		this.mall_var_49 = mall_var_49;
	}
	public String getMall_var_50() {
		return mall_var_50;
	}
	public void setMall_var_50(String mall_var_50) {
		this.mall_var_50 = mall_var_50;
	}
	public String getMall_var_51() {
		return mall_var_51;
	}
	public void setMall_var_51(String mall_var_51) {
		this.mall_var_51 = mall_var_51;
	}
	public String getMall_var_52() {
		return mall_var_52;
	}
	public void setMall_var_52(String mall_var_52) {
		this.mall_var_52 = mall_var_52;
	}
	public String getExpwycd() {
		return expwycd;
	}
	public void setExpwycd(String expwycd) {
		this.expwycd = expwycd;
	}
	public String getSendexp() {
		return sendexp;
	}
	public void setSendexp(String sendexp) {
		this.sendexp = sendexp;
	}
	public String getMall_var_53() {
		return mall_var_53;
	}
	public void setMall_var_53(String mall_var_53) {
		this.mall_var_53 = mall_var_53;
	}
	public String getMall_var_54() {
		return mall_var_54;
	}
	public void setMall_var_54(String mall_var_54) {
		this.mall_var_54 = mall_var_54;
	}
	public String getGbldivyn() {
		return gbldivyn;
	}
	public void setGbldivyn(String gbldivyn) {
		this.gbldivyn = gbldivyn;
	}
	public String getGblwght() {
		return gblwght;
	}
	public void setGblwght(String gblwght) {
		this.gblwght = gblwght;
	}
	public String getHscd() {
		return hscd;
	}
	public void setHscd(String hscd) {
		this.hscd = hscd;
	}
	public String getMall_var_55() {
		return mall_var_55;
	}
	public void setMall_var_55(String mall_var_55) {
		this.mall_var_55 = mall_var_55;
	}
	public String getAddrout() {
		return addrout;
	}
	public void setAddrout(String addrout) {
		this.addrout = addrout;
	}
	public String getMall_var_56() {
		return mall_var_56;
	}
	public void setMall_var_56(String mall_var_56) {
		this.mall_var_56 = mall_var_56;
	}
	public String getAddrin() {
		return addrin;
	}
	public void setAddrin(String addrin) {
		this.addrin = addrin;
	}
	public String getShiptypcd() {
		return shiptypcd;
	}
	public void setShiptypcd(String shiptypcd) {
		this.shiptypcd = shiptypcd;
	}
	public String getShipprc() {
		return shipprc;
	}
	public void setShipprc(String shipprc) {
		this.shipprc = shipprc;
	}
	public String getCondiprice() {
		return condiprice;
	}
	public void setCondiprice(String condiprice) {
		this.condiprice = condiprice;
	}
	public String getMall_var_57() {
		return mall_var_57;
	}
	public void setMall_var_57(String mall_var_57) {
		this.mall_var_57 = mall_var_57;
	}
	public String getMall_var_58() {
		return mall_var_58;
	}
	public void setMall_var_58(String mall_var_58) {
		this.mall_var_58 = mall_var_58;
	}
	public String getDiffprice1() {
		return diffprice1;
	}
	public void setDiffprice1(String diffprice1) {
		this.diffprice1 = diffprice1;
	}
	public String getMall_var_59() {
		return mall_var_59;
	}
	public void setMall_var_59(String mall_var_59) {
		this.mall_var_59 = mall_var_59;
	}
	public String getJejuprc() {
		return jejuprc;
	}
	public void setJejuprc(String jejuprc) {
		this.jejuprc = jejuprc;
	}
	public String getIslandprc() {
		return islandprc;
	}
	public void setIslandprc(String islandprc) {
		this.islandprc = islandprc;
	}
	public String getPrctypcd() {
		return prctypcd;
	}
	public void setPrctypcd(String prctypcd) {
		this.prctypcd = prctypcd;
	}
	public String getBndyn() {
		return bndyn;
	}
	public void setBndyn(String bndyn) {
		this.bndyn = bndyn;
	}
	public String getRetprc() {
		return retprc;
	}
	public void setRetprc(String retprc) {
		this.retprc = retprc;
	}
	public String getExcprc() {
		return excprc;
	}
	public void setExcprc(String excprc) {
		this.excprc = excprc;
	}
	public String getRudcd() {
		return rudcd;
	}
	public void setRudcd(String rudcd) {
		this.rudcd = rudcd;
	}
	public String getAsdtl() {
		return asdtl;
	}
	public void setAsdtl(String asdtl) {
		this.asdtl = asdtl;
	}
	public String getRtexcdtl() {
		return rtexcdtl;
	}
	public void setRtexcdtl(String rtexcdtl) {
		this.rtexcdtl = rtexcdtl;
	}
	public String getMall_var_60() {
		return mall_var_60;
	}
	public void setMall_var_60(String mall_var_60) {
		this.mall_var_60 = mall_var_60;
	}
	public String getMall_var_61() {
		return mall_var_61;
	}
	public void setMall_var_61(String mall_var_61) {
		this.mall_var_61 = mall_var_61;
	}
	public String getMall_var_62() {
		return mall_var_62;
	}
	public void setMall_var_62(String mall_var_62) {
		this.mall_var_62 = mall_var_62;
	}
	public String getMall_var_63() {
		return mall_var_63;
	}
	public void setMall_var_63(String mall_var_63) {
		this.mall_var_63 = mall_var_63;
	}
	public String getMall_var_64() {
		return mall_var_64;
	}
	public void setMall_var_64(String mall_var_64) {
		this.mall_var_64 = mall_var_64;
	}
	public String getMall_var_65() {
		return mall_var_65;
	}
	public void setMall_var_65(String mall_var_65) {
		this.mall_var_65 = mall_var_65;
	}
	public String getMall_var_66() {
		return mall_var_66;
	}
	public void setMall_var_66(String mall_var_66) {
		this.mall_var_66 = mall_var_66;
	}
	public String getMall_var_67() {
		return mall_var_67;
	}
	public void setMall_var_67(String mall_var_67) {
		this.mall_var_67 = mall_var_67;
	}
	public String getMall_var_68() {
		return mall_var_68;
	}
	public void setMall_var_68(String mall_var_68) {
		this.mall_var_68 = mall_var_68;
	}
	public String getMall_var_69() {
		return mall_var_69;
	}
	public void setMall_var_69(String mall_var_69) {
		this.mall_var_69 = mall_var_69;
	}
	public String getMall_var_70() {
		return mall_var_70;
	}
	public void setMall_var_70(String mall_var_70) {
		this.mall_var_70 = mall_var_70;
	}
	public String getMall_var_71() {
		return mall_var_71;
	}
	public void setMall_var_71(String mall_var_71) {
		this.mall_var_71 = mall_var_71;
	}
	public String getMall_var_72() {
		return mall_var_72;
	}
	public void setMall_var_72(String mall_var_72) {
		this.mall_var_72 = mall_var_72;
	}
	public String getMall_var_73() {
		return mall_var_73;
	}
	public void setMall_var_73(String mall_var_73) {
		this.mall_var_73 = mall_var_73;
	}
	public String getMall_var_74() {
		return mall_var_74;
	}
	public void setMall_var_74(String mall_var_74) {
		this.mall_var_74 = mall_var_74;
	}
	public String getMall_var_75() {
		return mall_var_75;
	}
	public void setMall_var_75(String mall_var_75) {
		this.mall_var_75 = mall_var_75;
	}
	public String getMall_var_76() {
		return mall_var_76;
	}
	public void setMall_var_76(String mall_var_76) {
		this.mall_var_76 = mall_var_76;
	}
	public String getMall_var_77() {
		return mall_var_77;
	}
	public void setMall_var_77(String mall_var_77) {
		this.mall_var_77 = mall_var_77;
	}
	public String getMall_var_78() {
		return mall_var_78;
	}
	public void setMall_var_78(String mall_var_78) {
		this.mall_var_78 = mall_var_78;
	}
	public String getMall_var_79() {
		return mall_var_79;
	}
	public void setMall_var_79(String mall_var_79) {
		this.mall_var_79 = mall_var_79;
	}
	public String getMall_var_80() {
		return mall_var_80;
	}
	public void setMall_var_80(String mall_var_80) {
		this.mall_var_80 = mall_var_80;
	}
	public String getMall_var_81() {
		return mall_var_81;
	}
	public void setMall_var_81(String mall_var_81) {
		this.mall_var_81 = mall_var_81;
	}
	public String getMall_var_82() {
		return mall_var_82;
	}
	public void setMall_var_82(String mall_var_82) {
		this.mall_var_82 = mall_var_82;
	}
	public String getMall_var_83() {
		return mall_var_83;
	}
	public void setMall_var_83(String mall_var_83) {
		this.mall_var_83 = mall_var_83;
	}
	public String getMall_var_84() {
		return mall_var_84;
	}
	public void setMall_var_84(String mall_var_84) {
		this.mall_var_84 = mall_var_84;
	}
	public String getMall_var_85() {
		return mall_var_85;
	}
	public void setMall_var_85(String mall_var_85) {
		this.mall_var_85 = mall_var_85;
	}
	public String getMall_var_86() {
		return mall_var_86;
	}
	public void setMall_var_86(String mall_var_86) {
		this.mall_var_86 = mall_var_86;
	}
	public String getMall_var_87() {
		return mall_var_87;
	}
	public void setMall_var_87(String mall_var_87) {
		this.mall_var_87 = mall_var_87;
	}
	public String getMall_var_88() {
		return mall_var_88;
	}
	public void setMall_var_88(String mall_var_88) {
		this.mall_var_88 = mall_var_88;
	}
	public String getMall_var_89() {
		return mall_var_89;
	}
	public void setMall_var_89(String mall_var_89) {
		this.mall_var_89 = mall_var_89;
	}
	public String getMall_var_90() {
		return mall_var_90;
	}
	public void setMall_var_90(String mall_var_90) {
		this.mall_var_90 = mall_var_90;
	}
	public String getMall_var_91() {
		return mall_var_91;
	}
	public void setMall_var_91(String mall_var_91) {
		this.mall_var_91 = mall_var_91;
	}
	public String getMall_var_92() {
		return mall_var_92;
	}
	public void setMall_var_92(String mall_var_92) {
		this.mall_var_92 = mall_var_92;
	}
	public String getMall_var_93() {
		return mall_var_93;
	}
	public void setMall_var_93(String mall_var_93) {
		this.mall_var_93 = mall_var_93;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	
	
	
	
		
}
