package com.errorpoint.expendedlistview.entity;

public class PromoMaterials {
	private String productId;  // product family id
	private String promoAidId; // promo material id [1=pad;2=gimmick;3=sample]
	private String categoryId; // product id
	private int year;
	private int month;
	private int teritoryBudget;
	private int confirmQty;
	private int expenseQty;
	private String promoProductName; // product name
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPromoAidId() {
		return promoAidId;
	}
	public void setPromoAidId(String promoAidId) {
		this.promoAidId = promoAidId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getTeritoryBudget() {
		return teritoryBudget;
	}
	public void setTeritoryBudget(int teritoryBudget) {
		this.teritoryBudget = teritoryBudget;
	}
	public int getConfirmQty() {
		return confirmQty;
	}
	public void setConfirmQty(int confirmQty) {
		this.confirmQty = confirmQty;
	}
	public int getExpenseQty() {
		return expenseQty;
	}
	public void setExpenseQty(int expenseQty) {
		this.expenseQty = expenseQty;
	}
	public String getPromoProductName() {
		return promoProductName;
	}
	public void setPromoProductName(String promoProductName) {
		this.promoProductName = promoProductName;
	}
	
	
}
