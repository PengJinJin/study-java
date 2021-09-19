package root.com.push.account;

import java.util.List;

public class AccountOperateVO {
	private String token;
	private List<Account> account_list;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Account> getAccount_list() {
		return account_list;
	}

	public void setAccount_list(List<Account> account_list) {
		this.account_list = account_list;
	}
}
