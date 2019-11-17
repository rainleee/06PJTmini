package com.model2.mvc.service.purchase.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

//dao에 직접적으로 접근해서 임무(?) 수행하는 로직
@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	//field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	//setSqlSession
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//constructor
	public PurchaseDaoImpl() {
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase",purchase);
		
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
		
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", ProdNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		System.out.println("search ::: "+search);
		System.out.println("buyerId" + buyerId);
		
		
		map.put("search", search);
		map.put("buyerId", buyerId);
		

		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
		
//		System.out.println("Dao getPurchaseList ::: "+list);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBuyer((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyer().getUserId()));
			list.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.getProduct", list.get(i).getPurchaseProd().getProdNo()));
		}
		
		map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCountPurchase", buyerId));
		
		map.put("list", list);

		
		return map;
		
	}

	@Override
	public List<Purchase> getSaleList(Search search) throws Exception {
		
		System.out.println("search ::: "+search);

		return sqlSession.selectList("PurchaseMapper.getSaleList", search);
		
	}
	
	

	@Override
	public Map<String, Object> getListPurchaseProduct(Search search, String buyerId) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		System.out.println("search ::: "+search);
		System.out.println("buyerId" + buyerId);
		
		
		map.put("search", search);
		map.put("buyerId", buyerId);
		

		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getListPurchaseProduct", map);
		
//		System.out.println("Dao getPurchaseList ::: "+list);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBuyer((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyer().getUserId()));
			list.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.getProduct", list.get(i).getPurchaseProd().getProdNo()));
		}
		
		map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCountPurchase", buyerId));
		
		map.put("list", list);

		
		return map;
	}

	@Override
	public void updatePurcahse(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase",purchase);
		
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode",purchase);
		
	}
	//스트링이 맞음
	@Override
	public int getTotalCount(String buyerId) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCountPurchase",buyerId);
		
	}

	@Override
	public int getTotalCountSale() throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCountSale");
	}
	
	
	
}
