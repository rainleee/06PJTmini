package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	//field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao purchaseDao;
	
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	//constructor
	public PurchaseServiceImpl() {
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		return purchaseDao.getPurchase(ProdNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		System.out.println("search ::: "+search);
		
		Map<String,Object> map = purchaseDao.getPurchaseList(search, buyerId);
		
		int totalCount = purchaseDao.getTotalCount(buyerId);
		
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	@Override
	public Map<String,Object> getSaleList(Search search) throws Exception {
		
		System.out.println("getSaleList search ::: "+search);
		List<Purchase> list = purchaseDao.getSaleList(search);
		System.out.println("lllllist : " + list);
		
		int totalCount = purchaseDao.getTotalCountSale();
		
		System.out.println("totalCount : " + totalCount);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println("getSaleList map : " + map.get("list"));
		System.out.println("getSaleList map : " + map.get("totalCount"));
		
		return map;
	}
	

	@Override
	public Map<String, Object> getListPurchaseProduct(Search search, String buyerId) throws Exception {
		
		System.out.println("search ::: "+search);
		
		Map<String,Object> map = purchaseDao.getListPurchaseProduct(search, buyerId);
		
		int totalCount = purchaseDao.getTotalCount(buyerId);
		
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updatePurcahse(Purchase purchase) throws Exception {
		purchaseDao.updatePurcahse(purchase);
		
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
		
	}
	
}
