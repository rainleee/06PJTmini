package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	//Insert
	public void addPurchase(Purchase purchase) throws Exception;
	
	//find => get으로 변경
	public Purchase getPurchase(int tranNo) throws Exception;
	
	//getPurchase2 ProdNo로 찾기 (계속 쓰는지는 ?)
	public Purchase getPurchase2(int ProdNo) throws Exception;
	
	//getProductList
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	//getSaleList(판매리스트 미구현기능)
	public List<Purchase> getSaleList(Search search) throws Exception;
	
	//user 구매목록조회
	public Map<String,Object> getListPurchaseProduct(Search search, String buyerId) throws Exception;
	
	//updatePurcahse
	public void updatePurcahse(Purchase purchase) throws Exception;
	
	//updateTranCode
	public void updateTranCode(Purchase purchase) throws Exception;
	
	//총 구매갯수를 세기위한 TotalCount
	public int getTotalCount(String buyerId) throws Exception;
	
	public int getTotalCountSale() throws Exception;
}
