package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml"})
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		User user = new User();
		user.setUserId("user02");
		user.setUserName("산도");
//		user.setPassword("180730");

		Product product = new Product();
		product.setProdNo(10000);
		product.setProdName("산도");
		
		Purchase purchase = new Purchase();
//		purchase.setTranNo(999);
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		
		System.out.println("addPurchase : " + purchase);
		purchaseService.addPurchase(purchase);
//		userService.addUser(user);
		
//		user = userService.getUser("testUserId");

		//==> console 확인
		
		//==> API 확인
//		Assert.assertEquals(3, purchase.getTranNo());
		Assert.assertEquals("user02",purchase.getBuyer().getUserId());
		Assert.assertEquals(10000, purchase.getPurchaseProd().getProdNo());
//		Assert.assertEquals(999, purchase.getTranNo());
	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		//service에 담을 test purchase 객체 선언
		Purchase purchase = new Purchase();
		
		//tranNo가 5번인 실제 DB에 존재하는 번호를 입력
		purchase = purchaseService.getPurchase(5);
		

		//==> console 확인
		System.out.println("여기에 있다 : " + purchase);
		
		//==> API 확인
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());

		//해당 ()안에 내용이 null이 아니란걸 증명
		Assert.assertNotNull(purchaseService.getPurchase(5));
	}
	
	 //@Test
	 public void testUpdatePurchase() throws Exception{
		
		Purchase purchase = purchaseService.getPurchase(5);
//		Product product = productService.getProduct(10103);
		
		Assert.assertNotNull(purchase);
		System.out.println("변경전 : " + purchase);
		
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		
		purchase.setDivyAddr("변경주소");
		purchase.setDivyRequest("변경요청사항");
		purchaseService.updatePurcahse(purchase);
		System.out.println("변경 후 : " + purchase);
		
		Assert.assertNotNull(purchase);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("변경주소", purchase.getDivyAddr());
		Assert.assertEquals("변경요청사항", purchase.getDivyRequest());
		
	 }
	 
	 //@Test
	 public void testUpdateTranCode() throws Exception{
		
		Purchase purchase = new Purchase();
		purchase.setTranCode("2");
		purchase.setTranNo(21);
		System.out.println("코드 넘버는 : " + purchase);
		purchaseService.updateTranCode(purchase);
//		Product product = productService.getProduct(10103);
		
		Assert.assertNotNull(purchase);
		Assert.assertEquals("2", purchase.getTranCode());
		
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
//		Assert.assertEquals("변경주소", purchase.getDivyAddr());
//		Assert.assertEquals("변경요청사항", purchase.getDivyRequest());
		
	 }
	 
	
	 //==>  주석을 풀고 실행하면....
	 @Test
	 public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setStartRowNum(1);
	 	search.setEndRowNum(19);

	 	
//	 	User buyId = new User();
//	 	buyId.setUserId("user01");
	 	
	 	String buyerId = "user01";
	 	
//	 	Purchase purchase = new Purchase();
//	 	purchase.setBuyer(buyId);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, buyerId);
//	 	Map<String,Object> map = userService.getUserList(search);
	 	
	 	System.out.println("map : " + map);
	 	
	 	List<Purchase> list = (List<Purchase>)map.get("list");
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	for (int i = 0; i < list.size(); i++) {
	 		System.out.println("list.get(i) : " + list.get(i));
			
		}
//	 	Assert.assertEquals(3, list.size());
//	 	Assert.assertEquals("user01");
	 	
	 	System.out.println("testList : " + list);
//	 	Assert.assertEquals(19, list.size());
	 	
		//==> console 확인
	 	
	 	System.out.println("totalcount : " + totalCount);
	 	
	 	System.out.println("=======================================");
//	 	
	 	search.setCurrentPage(2);
	 	search.setPageSize(3);
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword("");
	 	map = purchaseService.getPurchaseList(search, buyerId);
	 	
	 	list = (List<Purchase>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
//	 public void testGetUserListByUserId() throws Exception{
//		 
//	 	Search search = new Search();
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(3);
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword("admin");
//	 	Map<String,Object> map = userService.getUserList(search);
//	 	
//	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(1, list.size());
//	 	
//		//==> console 확인
//	 	//System.out.println(list);
//	 	
//	 	Integer totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 	
//	 	System.out.println("=======================================");
//	 	
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword(""+System.currentTimeMillis());
//	 	map = userService.getUserList(search);
//	 	
//	 	list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(0, list.size());
//	 	
//		//==> console 확인
//	 	//System.out.println(list);
//	 	
//	 	totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 }
	 
//	 @Test
//	 public void testGetUserListByUserName() throws Exception{
//		 
//	 	Search search = new Search();
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(3);
//	 	search.setSearchCondition("1");
//	 	search.setSearchKeyword("SCOTT");
//	 	Map<String,Object> map = userService.getUserList(search);
//	 	
//	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
//	 	
//		//==> console 확인
//	 	System.out.println(list);
//	 	
//	 	Integer totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 	
//	 	System.out.println("=======================================");
//	 	
//	 	search.setSearchCondition("1");
//	 	search.setSearchKeyword(""+System.currentTimeMillis());
//	 	map = userService.getUserList(search);
//	 	
//	 	list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(0, list.size());
//	 	
//		//==> console 확인
//	 	System.out.println(list);
//	 	
//	 	totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 }	 
}