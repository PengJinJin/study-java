package com.push.account;

import java.util.List;

/**
 * 账号绑定与解绑（批量操作）
 *
 * @author KNYS
 * @since 2019年6月27日18:01:32
 */
public class AccountOperateRequest extends AccountQueryRequest {
	/**
	 * <pre>
	 * 	 操作类型
	 * 1：token 追加设置account
	 * 2：token覆盖设置account
	 * 3: token删除绑定的多个account
	 * 4：token 删除绑定的所有account
	 * 5：account 删除绑定的所有token
	 * </pre>
	 */
	private int operator_type;
	/**
	 * 当operator_type=1、2 、3 时有效且必须每次调用最多允许设置20个token每个token_account 由1个token 和1个account_list 组成。
	 */
	private List<AccountOperateVO> token_accounts;

	public List<AccountOperateVO> getToken_accounts() {
		return token_accounts;
	}

	public void setToken_accounts(List<AccountOperateVO> token_accounts) {
		this.token_accounts = token_accounts;
	}

}
