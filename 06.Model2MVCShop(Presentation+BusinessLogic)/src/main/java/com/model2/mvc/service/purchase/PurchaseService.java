package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

//Dao에 직접 연관하지 않고 Dao에 구성된 로직을 불러오는 역할만 한다.
public interface PurchaseService {
	
	//Insert
	public void addPurchase(Purchase purchase) throws Exception;
	
	//find => get으로 변경
	public Purchase getPurchase(int tranNo) throws Exception;
	
	//getPurchase2 ProdNo로 찾기 (계속 쓰는지는 ?)
	public Purchase getPurchase2(int ProdNo) throws Exception;
	
	//getProductList
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	//getSaleList(판매리스트 미구현기능)
	public Map<String,Object> getSaleList(Search search) throws Exception;
	
	//구매자가 구매목록보기
	public Map<String,Object> getListPurchaseProduct(Search search, String buyerId) throws Exception;
	
	//updatePurcahse
	public void updatePurcahse(Purchase purchase) throws Exception;
	
	//updateTranCode
	public void updateTranCode(Purchase purchase) throws Exception;

}
