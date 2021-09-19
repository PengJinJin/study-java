package root.com.push.account;

/**
 * 操作账号用
 *
 * @author KNYS
 * @since 2019年6月27日18:05:08
 */
public class Account {
	// 账号
	private String account;
	// 账号类型 com.tencent.xinge.bean.AccountType
	private int account_type;

	public Account() {
	}

	public Account(String account, int account_type) {
		this.account = account;
		this.account_type = account_type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getAccount_type() {
		return account_type;
	}

	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
}
