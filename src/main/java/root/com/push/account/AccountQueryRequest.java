package root.com.push.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.xinge.bean.Platform;

import java.util.List;

/**
 * 账号-设备绑定查询（批量操作）
 *
 * @author KNYS
 * @since 2019年6月27日17:12:41
 */
public class AccountQueryRequest {
	/**
	 * <pre>
	 * 操作类型
	 * 1：根据account 批量查询对应token
	 * 2：根据 token查询account
	 * </pre>
	 */
	private int operator_type;
	/**
	 * 客户端平台类型
	 * 1）android：安卓
	 * 2）ios：苹果
	 */
	private Platform platform;
	/**
	 * 当operator_type = 1 时有效且必填
	 */
	private List<Account> account_list;
	/**
	 * 当operator_type = 2 时有效且必填待查询token 的列表
	 */
	private List<String> token_list;
	/**
	 * 接口操作人员类型：qq、rtx、email、other
	 */
	private String op_type;
	/**
	 * 接口操作人员类型：接口操作人员id（ qq\rtx\email）
	 */
	private String op_id;

	/**
	 * token
	 */
	private String token;


	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String repoStr = null;
		try {
			repoStr = mapper.writeValueAsString(this);
		} catch (Exception e) {
			//TODO do someting
		}
		return repoStr;
	}

	public int getOperator_type() {
		return operator_type;
	}

	public void setOperator_type(int operator_type) {
		this.operator_type = operator_type;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public List<Account> getAccount_list() {
		return account_list;
	}

	public void setAccount_list(List<Account> account_list) {
		this.account_list = account_list;
	}

	public List<String> getToken_list() {
		return token_list;
	}

	public void setToken_list(List<String> token_list) {
		this.token_list = token_list;
	}

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
