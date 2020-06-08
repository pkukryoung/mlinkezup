package com.kdj.mlink.ezup3.data.dao;
  
  
  public class BaseStockDto
  {
    private String rowno=""; // No.
    
    private String prodcd=""; // 상품코드
    
    private String img;
    
    private String prodnm=""; // 상품명
    
    private String specdes=""; // 규격
    
    public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	private String qty=""; // 장부수량
    private String realqty=""; // 실제수량
    private String diffqty=""; // 차이수량
    
    private String reason=""; // 사유

    public BaseStockDto() {}

    public BaseStockDto(String prodcd, String prodnm, String specdes, 
			String qty, String realQty, String diffQty, String reason) 
    {
    	this.prodcd = prodcd;
    	this.prodnm = prodnm;
    	this.specdes = specdes;
    	this.qty = qty;
    	this.realqty = realQty;
    	this.diffqty = diffQty;
    	this.reason = reason;
    }

    
    public String getRowno()
    {
      return this.rowno;
    }
    
    public void setRowno(String rowno)
    {
    	this.rowno = rowno;
    }
    
    public String getProdcd()
    {
      return this.prodcd;
    }
    
    public void setProdcd(String prodcd)
    {
    	this.prodcd = prodcd;
    }
    
    public String getProdnm()
    {
      return this.prodnm;
    }
    
    public void setProdnm(String prodnm)
    {
    	this.prodnm = prodnm;
    }
    
    public String getSpecdes()
    {
      return this.specdes;
    }
    
    public void setSpecdes(String specdes)
    {
    	this.specdes= specdes;
    }

   
    public String getQty() 
    {
      return this.qty;
    }
    
    public void setQty(String qty)
    {
    	this.qty = qty;
    }
    
    public String getRealQty()
    {
      return this.realqty;
    }
    
    public void setRealQty(String realqty)
    {
    	this.realqty = realqty;
    }
    
    public String getDiffQty()
    {
      return this.diffqty;
    }
    
    public void setDiffQty(String diffqty)
    {
    	this.diffqty = diffqty;
    }
    
    public String getReason() {
      return this.reason;
    }
    
    public void setReason(String reason)
    {
    	this.reason= reason;
    }

    public String toString() {
    	return rowno + " " + prodcd + " " + prodnm+ " " + qty+ " " + realqty+ " " + diffqty+ " " + reason;
    }
  }
