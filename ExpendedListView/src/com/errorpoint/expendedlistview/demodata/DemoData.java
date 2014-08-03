package com.errorpoint.expendedlistview.demodata;

import java.util.ArrayList;

import com.errorpoint.expendedlistview.entity.PromoMaterials;

public class DemoData {
	
	
	public static ArrayList<PromoMaterials> getPromoProductList(int promoAidId){
		ArrayList<PromoMaterials> promoMaterialsList = null;
		String pName="Fimoxil Xerox";
		
		int pId=1, tBudget=1;
		
		promoMaterialsList = new ArrayList<PromoMaterials>();
		do {
			
			int promoProductId = pId;
			String promoProductName = pName+"~"+pId;
			
			PromoMaterials promoMaterials = new PromoMaterials();
			promoMaterials.setPromoAidId(promoAidId+"");
			
			promoMaterials.setCategoryId(promoProductId + "");
			promoMaterials.setPromoProductName(promoProductName);
			
			try{
				promoMaterials.setProductId(tBudget+"");
			} catch (Exception e){}
			
			tBudget = Integer.parseInt( tBudget+"" );
			
			if(tBudget>-99999){
				promoMaterials.setTeritoryBudget(tBudget+promoAidId+promoProductId);
				promoMaterialsList.add(promoMaterials);
			}
			pId++;
			
		} while (pId<=promoAidId);

		
		return promoMaterialsList;
	}
	
}
