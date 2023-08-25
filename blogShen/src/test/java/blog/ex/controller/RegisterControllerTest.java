package blog.ex.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.ex.service.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;
	
	@BeforeEach
	//データの作成
	public void prepareData() {
		when(accountService.createAccount(eq("ss"), eq("ss@ss.com"), eq("123456"))).thenReturn(true);
		when(accountService.createAccount(eq("ss"), eq("s@ss.com"), eq("123456"))).thenReturn(false);
		when(accountService.createAccount(eq("ss"), eq(""), eq("123456"))).thenReturn(false);
		when(accountService.createAccount(eq("ss"), eq("ss@ss.com"), eq("1111"))).thenReturn(false);
		when(accountService.createAccount(eq("ss"), eq("ss@ss.com"), eq(""))).thenReturn(false);
		when(accountService.createAccount(eq("sss"), eq("ss@ss.com"), eq("123456"))).thenReturn(false);
		when(accountService.createAccount(eq(""), eq("ss@ss.com"), eq("123456"))).thenReturn(false);
	}
	
	// 登録ページを正しく取得できるかのテスト
			@Test
			public void testGetLoginPage_Succeed() throws Exception {
				RequestBuilder request = MockMvcRequestBuilders
						.get("/user/register");

				mockMvc.perform(request).andExpect(view().name("register.html"));
			}
		
	// 登録が成功した場合のテスト
			@Test
			public void testRegister_Successful() throws Exception {
				RequestBuilder request = MockMvcRequestBuilders
						.post("/user/register/process")
						.param("name", "ss")
						.param("email", "ss@ss.com")
						.param("password", "123456");
				
				mockMvc.perform(request)
				.andExpect(redirectedUrl("/user/login")).andReturn();
		}
			
	//メールが間違った場合登録失敗するテスト
			@Test
			public void testRegister_Wrongemail_Unsuccessful() throws Exception {
				RequestBuilder request = MockMvcRequestBuilders
						.post("/user/register/process")
						.param("name", "ss")
						.param("email", "")
						.param("password", "123456");
				
				mockMvc.perform(request)
				.andExpect(redirectedUrl("/user/login")).andReturn();
		}
			
			//パスワードが間違った場合登録失敗するテスト
			@Test
			public void testRegister_Wrongpassword_Unsuccessful() throws Exception {
				RequestBuilder request = MockMvcRequestBuilders
						.post("/user/register/process")
						.param("name", "ss")
						.param("email", "ss@ss.com")
						.param("password", "");
				
				mockMvc.perform(request)
				.andExpect(redirectedUrl("/user/login")).andReturn();
		}
			//ユーザー名が間違った場合登録失敗するテスト
			@Test
			public void testRegister_Wrongname_Unsuccessful() throws Exception {
				RequestBuilder request = MockMvcRequestBuilders
						.post("/user/register/process")
						.param("name", "")
						.param("email", "ss@ss.com")
						.param("password", "123456");
				
				mockMvc.perform(request)
				.andExpect(redirectedUrl("/user/login")).andReturn();
		}
			//全てが間違った場合登録失敗するテスト
			@Test
			public void testRegister_Wrongall_Unsuccessful() throws Exception {
				RequestBuilder request = MockMvcRequestBuilders
						.post("/user/register/process")
						.param("name", "")
						.param("email", "")
						.param("password", "");
				
				mockMvc.perform(request)
				.andExpect(redirectedUrl("/user/login")).andReturn();
		}
}
