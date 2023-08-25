package blog.ex.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.ex.model.entity.AccountEntity;
import blog.ex.service.AccountService;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;
	
	@BeforeEach
	// 事前のデータ準備
	public void prepareData() {
		AccountEntity accountEntity = new AccountEntity(1L,"ss","ss@ss.com","123456",LocalDateTime.now());
		when(accountService.loginAccount(eq("ss@ss.com"), eq("123456"))).thenReturn(accountEntity);
		when(accountService.loginAccount(eq("11@s"), eq("123456"))).thenReturn(null);
		when(accountService.loginAccount(eq(""), eq("123456"))).thenReturn(null);
		when(accountService.loginAccount(eq(""), eq(""))).thenReturn(null);
		when(accountService.loginAccount(eq("ss@s.com"), eq("1234"))).thenReturn(null);
		
	}
	// ログインページを正しく取得できるかのテスト
		@Test
		public void testGetLoginPage_Succeed() throws Exception {
			RequestBuilder request = MockMvcRequestBuilders
					.get("/user/login");

			mockMvc.perform(request).andExpect(view().name("login.html"));
		}
		// ログインが成功した場合のテスト
		@Test
		public void testLogin_Successful() throws Exception {
			RequestBuilder request = MockMvcRequestBuilders
					.post("/user/login/process")
					.param("email", "ss@ss.com")
					.param("password", "123456");
			
			MvcResult result = mockMvc.perform(request)
					// リクエストの実行結果を返すためのメソッドがandReturn()
					.andExpect(redirectedUrl("/user/blog/list")).andReturn();
			
			// セッションの取得
			HttpSession session = result.getRequest().getSession();
			
			// セッションがきちんと設定出来ているかの確認テスト
			AccountEntity accountList = (AccountEntity)session.getAttribute("account");
			assertNotNull(accountList);
			assertEquals("ss",accountList.getAccountName());
			assertEquals("ss@ss.com",accountList.getAccountEmail());
			assertEquals("123456",accountList.getPassword());
		}
		//メールが間違っているので失敗してしまった例
		@Test
		public void testLogin_Wrongemail_Unsuccessful() throws Exception{
			RequestBuilder request = MockMvcRequestBuilders
					.post("/user/login/process")
					.param("email", "")
					.param("password", "123456");
			
			MvcResult result = mockMvc.perform(request)
					// リクエストの実行結果を返すためのメソッドがandReturn()
					.andExpect(redirectedUrl("/user/login")).andReturn();
			// セッションの取得
			HttpSession session = result.getRequest().getSession();
			// セッションがきちんと設定出来ているかの確認テスト
			AccountEntity accountList = (AccountEntity)session.getAttribute("account");
			assertNull(accountList);
		}
		
		//パスワードが間違っているので失敗してしまった例
			@Test
			public void testLogin_Wrongpassword_Unsuccessful() throws Exception{
				RequestBuilder request = MockMvcRequestBuilders
						.post("/user/login/process")
						.param("email", "ss@ss.com")
						.param("password", "");
					
				MvcResult result = mockMvc.perform(request)
						// リクエストの実行結果を返すためのメソッドがandReturn()
						.andExpect(redirectedUrl("/user/login")).andReturn();
				// セッションの取得
				HttpSession session = result.getRequest().getSession();
				// セッションがきちんと設定出来ているかの確認テスト
				AccountEntity accountList = (AccountEntity)session.getAttribute("account");
				assertNull(accountList);
				}
			//パスワードが間違っているので失敗してしまった例
			@Test
			public void testLogin_WrongemailAndpassword_Unsuccessful() throws Exception{
				RequestBuilder request = MockMvcRequestBuilders
						.post("/user/login/process")
						.param("email", "ss@s.com")
						.param("password", "1234");
					
				MvcResult result = mockMvc.perform(request)
						// リクエストの実行結果を返すためのメソッドがandReturn()
						.andExpect(redirectedUrl("/user/login")).andReturn();
				// セッションの取得
				HttpSession session = result.getRequest().getSession();
				// セッションがきちんと設定出来ているかの確認テスト
				AccountEntity accountList = (AccountEntity)session.getAttribute("account");
				assertNull(accountList);
				}
}
