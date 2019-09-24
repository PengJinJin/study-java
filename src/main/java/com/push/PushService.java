package com.push;

import com.push.account.Account;
import com.push.account.AccountOperateRequest;
import com.push.account.AccountOperateVO;
import com.push.account.AccountQueryRequest;
import com.tencent.xinge.bean.*;
import com.tencent.xinge.device.tag.DeviceTagRequest;
import com.tencent.xinge.push.app.PushAppRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//@Service
public class PushService {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		strings.add("type_1");
//		strings.add("18701478695");
//		strings.add("18695235800");
//		strings.add("962b8d1d1b19018f4156904dc692430ab0b19273");
//		JSONObject o = queryAccountAndroid(strings);
//		JSONObject o = pushAccountAndroid("hhhhh", "pushAccountAndroid", strings, null);
		String t = "962b8d1d1b19018f4156904dc692430ab0b19273";
		JSONObject o = pushTagsAndroid("123", "12312412412", strings, null);
//				addMultiTagsForSingleTokenAndroid(strings, t);

		System.out.println(o);

	}

//	@Autowired
	private static CustomPushApp androidPush;
//	@Autowired
	private static CustomPushApp iOSPush;

	static {
		androidPush = new CustomPushApp("e8ba2eddbcce1", "6af3b623fb98504b46ea649da91864ce");
		iOSPush = new CustomPushApp("d1e0f9629a187", "2475626e021c7ed664b7bdfaafe740e4");
	}

	//-----------------------------------通用

	/**
	 * 账号推送Android和iOS
	 */
	public JSONArray pushAccountAll(String title, String content, List<String> accounts, Map<String, Object> customParams) {
		JSONArray array = new JSONArray();
		array.put(pushAccountIOS(title, content, accounts, customParams));
		array.put(pushAccountAndroid(title, content, accounts, customParams));
		return array;
	}

	/**
	 * 标签推送Android和iOS
	 */
	public JSONArray pushTagAll(String title, String content, List<String> tags, Map<String, Object> customParams) {
		JSONArray array = new JSONArray();
		array.put(pushTagsIOS(title, content, tags, customParams));
		array.put(pushTagsAndroid(title, content, tags, customParams));
		return array;
	}

	//-----------------------------------IOS

	/**
	 * 多账号推送IOS
	 *
	 * @param title        一级标题
	 * @param content      内容
	 * @param accounts     账号列表
	 * @param customParams 自定义参数
	 */
	public JSONObject pushAccountIOS(String title, String content, List<String> accounts, Map<String, Object> customParams) {
		// 请求内容
		PushAppRequest request = new PushAppRequest();
		request.setPlatform(Platform.ios);
		request.setAudience_type(AudienceType.account_list);
		request.setMessage_type(MessageType.notify);
		request.setMessage(this.buildMessageIOS(title, content, customParams));
		/*ArrayList<String> accounts = new ArrayList<>();
		accounts.add("15131087520");*/
		request.setAccount_list((ArrayList<String>) accounts);
		request.setEnvironment(iOSPush.getEnv());

		return iOSPush.pushApp(request.toString());
	}

	/**
	 * 多标签推送IOS
	 *
	 * @param title        一级标题
	 * @param content      内容
	 * @param tags         标签列表
	 * @param customParams 自定义参数
	 * @return
	 */
	public JSONObject pushTagsIOS(String title, String content, List<String> tags, Map<String, Object> customParams) {
		// 请求内容
		PushAppRequest request = new PushAppRequest();
		request.setPlatform(Platform.ios);
		request.setAudience_type(AudienceType.tag);
		request.setMessage_type(MessageType.notify);
		request.setMessage(this.buildMessageIOS(title, content, customParams));

		TagListObject tagListObject = new TagListObject();
		tagListObject.setOp(OpType.AND);
		tagListObject.setTags((ArrayList<String>) tags);
		request.setTag_list(tagListObject);
		request.setEnvironment(iOSPush.getEnv());

		return iOSPush.pushApp(request.toString());
	}

	/**
	 * IOS全推
	 */
	public JSONObject pushAllIOS(String title, String content, Map<String, Object> customParams) {
		// 请求内容
		PushAppRequest request = new PushAppRequest();
		request.setAudience_type(AudienceType.all);
		request.setPlatform(Platform.ios);
		request.setMessage_type(MessageType.notify);
		request.setMessage(this.buildMessageIOS(title, content, customParams));
		request.setEnvironment(iOSPush.getEnv());

		return iOSPush.pushApp(request.toString());
	}

	/**
	 * IOS单个token增加多个tags
	 *
	 * @param tags  目标标签
	 * @param token token
	 * @return
	 */
	public JSONObject addMultiTagsForSingleTokenIOS(List<String> tags, String token) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.ADD_MULT_TAGS_SINGLE.getType());
		request.setPlatform(Platform.ios);
		request.setTag_list((ArrayList<String>) tags);
		request.setToken_list(new ArrayList<>(Collections.singleton(token)));

		return iOSPush.deviceTag(request.toString());
	}

	/**
	 * IOS单个token删除多个tags
	 *
	 * @param tags  目标标签
	 * @param token token
	 * @return
	 */
	public JSONObject delMultiTagsForSingleTokenIOS(List<String> tags, String token) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.DELE_MULT_TAGS_SINGLE.getType());
		request.setPlatform(Platform.ios);
		request.setTag_list((ArrayList<String>) tags);
		request.setToken_list(new ArrayList<>(Collections.singleton(token)));

		return iOSPush.deviceTag(request.toString());
	}

	/**
	 * IOS为单个tag添加tokens
	 *
	 * @param tag    目标标签
	 * @param tokens tokens
	 * @return
	 */
	public JSONObject addSingleTagForMultiTokenIOS(String tag, List<String> tokens) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.ADD_SINGLE_TAG_MULT.getType());
		request.setPlatform(Platform.ios);
		request.setTag_list(new ArrayList<>(Collections.singleton(tag)));
		request.setToken_list((ArrayList<String>) tokens);

		return iOSPush.deviceTag(request.toString());
	}

	/**
	 * IOS根据tag删除tokens
	 */
	public JSONObject delSingleTagForMultiTokenIOS(String tag, List<String> tokens) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.DELE_SINGLE_TAG_MULT.getType());
		request.setPlatform(Platform.ios);
		request.setTag_list(new ArrayList<>(Collections.singleton(tag)));
		request.setToken_list((ArrayList<String>) tokens);

		return iOSPush.deviceTag(request.toString());
	}

	/**
	 * IOS删除token的所有tags
	 */
	public JSONObject addAllTagsForSingleTokenIOS(String token) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.DELE_ALL_TAGS_SINGLE.getType());
		request.setPlatform(Platform.ios);
		request.setToken_list(new ArrayList<>(Collections.singleton(token)));

		return iOSPush.deviceTag(request.toString());
	}

	/**
	 * token覆盖绑定的account
	 *
	 * @param token   token
	 * @param account account
	 * @return
	 */
	public JSONObject coverAccountIOS(String token, String account) {
		AccountOperateRequest request = new AccountOperateRequest();
		request.setOperator_type(2);
		request.setPlatform(Platform.ios);
		List<AccountOperateVO> tokenAccounts = new ArrayList<>();
		AccountOperateVO queryRequest = new AccountOperateVO();
		Account a = new Account(account, 0);
		queryRequest.setAccount_list(Collections.singletonList(a));
		queryRequest.setToken(token);
		tokenAccounts.add(queryRequest);
		request.setToken_accounts(tokenAccounts);
		return iOSPush.operateAccount(request.toString());
	}

	/**
	 * 根据token删除所有的account
	 */
	public JSONObject delAllAccountByTokenIOS(List<String> tokens) {
		AccountOperateRequest request = new AccountOperateRequest();
		request.setOperator_type(4);
		request.setPlatform(Platform.ios);
		request.setToken_list(tokens);
		return iOSPush.operateAccount(request.toString());
	}

	/**
	 * 根据account删除所有的token
	 */
	public JSONObject delAllTokenByAccountIOS(List<String> accounts) {
		AccountOperateRequest request = new AccountOperateRequest();
		request.setOperator_type(4);
		request.setPlatform(Platform.ios);
		List<Account> accountList = new ArrayList<>();
		accounts.forEach(a -> accountList.add(new Account(a, 0)));
		request.setAccount_list(accountList);
		return iOSPush.operateAccount(request.toString());
	}

	/**
	 * iOS根据account 批量查询对应token
	 *
	 * @param accounts 账号列表
	 * @return
	 */
	public JSONObject queryAccountIOS(List<String> accounts) {
		AccountQueryRequest query = new AccountQueryRequest();
		query.setOperator_type(1);
		query.setPlatform(Platform.ios);
		List<Account> list = new ArrayList<>();
		accounts.forEach(a -> list.add(new Account(a, AccountType.unknown.getType())));
		query.setAccount_list(list);

		return iOSPush.queryAccount(query.toString());
	}

	//-----------------------------------安卓

	/**
	 * 多账号推送Android
	 *
	 * @param title        标题
	 * @param content      内容
	 * @param accounts     账号列表
	 * @param customParams 自定义参数
	 */
	public static JSONObject pushAccountAndroid(String title, String content, List<String> accounts, Map<String, Object> customParams) {
		// 请求内容
		PushAppRequest request = new PushAppRequest();
		request.setPlatform(Platform.android);
		request.setMessage_type(MessageType.notify);
		request.setAudience_type(AudienceType.account_list);
		request.setMessage(buildMessageAndroid(title, content, customParams));
		/*ArrayList<String> accounts = new ArrayList<>();
		accounts.add("15131087520");*/
		request.setAccount_list((ArrayList<String>) accounts);
		request.setPush_id("0");

		return androidPush.pushApp(request.toString());
	}

	/**
	 * 多标签推送安卓
	 *
	 * @param title        标题
	 * @param content      内容
	 * @param tags         标签列表
	 * @param customParams 自定义参数
	 * @return
	 */
	public static JSONObject pushTagsAndroid(String title, String content, List<String> tags, Map<String, Object> customParams) {
		// 请求内容
		PushAppRequest request = new PushAppRequest();
		request.setPlatform(Platform.android);
		request.setAudience_type(AudienceType.tag);
		request.setMessage_type(MessageType.notify);
		request.setMessage(buildMessageAndroid(title, content, customParams));

		TagListObject tagListObject = new TagListObject();
		tagListObject.setOp(OpType.OR);
		tagListObject.setTags((ArrayList<String>) tags);
		request.setTag_list(tagListObject);

		return androidPush.pushApp(request.toString());
	}

	/**
	 * 安卓全推
	 */
	public JSONObject pushAllAndroid(String title, String content, Map<String, Object> customParams) {
		// 请求内容
		PushAppRequest request = new PushAppRequest();
		request.setAudience_type(AudienceType.all);
		request.setPlatform(Platform.android);
		request.setMessage_type(MessageType.notify);
		request.setMessage(this.buildMessageAndroid(title, content, customParams));

		return androidPush.pushApp(request.toString());
	}

	/**
	 * Android单个token增加多个tags
	 *
	 * @param tags  目标标签
	 * @param token token
	 * @return
	 */
	public static JSONObject addMultiTagsForSingleTokenAndroid(List<String> tags, String token) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.ADD_MULT_TAGS_SINGLE.getType());
		request.setPlatform(Platform.android);
		request.setTag_list((ArrayList<String>) tags);
		request.setToken_list(new ArrayList<>(Collections.singleton(token)));

		return androidPush.deviceTag(request.toString());
	}

	/**
	 * Android单个token删除多个tags
	 *
	 * @param tags  目标标签
	 * @param token token
	 * @return
	 */
	public JSONObject delMultiTagsForSingleTokenAndroid(List<String> tags, String token) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.DELE_MULT_TAGS_SINGLE.getType());
		request.setPlatform(Platform.android);
		request.setTag_list((ArrayList<String>) tags);
		request.setToken_list(new ArrayList<>(Collections.singleton(token)));

		return androidPush.deviceTag(request.toString());
	}

	/**
	 * Android为单个tag添加tokens
	 *
	 * @param tag    目标标签
	 * @param tokens tokens
	 * @return
	 */
	public JSONObject addSingleTagForMultiTokenAndroid(String tag, List<String> tokens) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.ADD_SINGLE_TAG_MULT.getType());
		request.setPlatform(Platform.android);
		request.setTag_list(new ArrayList<>(Collections.singleton(tag)));
		request.setToken_list((ArrayList<String>) tokens);

		return androidPush.deviceTag(request.toString());
	}

	/**
	 * Android根据tag删除tokens
	 */
	public JSONObject delSingleTagForMultiTokenAndroid(String tag, List<String> tokens) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.DELE_SINGLE_TAG_MULT.getType());
		request.setPlatform(Platform.android);
		request.setTag_list(new ArrayList<>(Collections.singleton(tag)));
		request.setToken_list((ArrayList<String>) tokens);

		return androidPush.deviceTag(request.toString());
	}

	/**
	 * Android删除token的所有tags
	 */
	public JSONObject addAllTagsForSingleTokenAndroid(String token) {
		DeviceTagRequest request = new DeviceTagRequest();
		request.setOperator_type(OperatorType.DELE_ALL_TAGS_SINGLE.getType());
		request.setPlatform(Platform.android);
		request.setToken_list(new ArrayList<>(Collections.singleton(token)));

		return androidPush.deviceTag(request.toString());
	}

	/**
	 * token覆盖绑定的account
	 *
	 * @param token   token
	 * @param account account
	 * @return
	 */
	public JSONObject coverAccountAndroid(String token, String account) {
		AccountOperateRequest request = new AccountOperateRequest();
		request.setOperator_type(2);
		request.setPlatform(Platform.android);
		List<AccountOperateVO> tokenAccounts = new ArrayList<>();
		AccountOperateVO queryRequest = new AccountOperateVO();
		Account a = new Account(account, 0);
		queryRequest.setToken(token);
		queryRequest.setAccount_list(Collections.singletonList(a));
		tokenAccounts.add(queryRequest);
		request.setToken_accounts(tokenAccounts);
		return androidPush.operateAccount(request.toString());
	}

	/**
	 * 根据token删除所有的account
	 */
	public JSONObject delAllAccountByTokenAndroid(List<String> tokens) {
		AccountOperateRequest request = new AccountOperateRequest();
		request.setOperator_type(4);
		request.setPlatform(Platform.android);
		request.setToken_list(tokens);
		return androidPush.operateAccount(request.toString());
	}

	/**
	 * 根据account删除所有的token
	 */
	public JSONObject delAllTokenByAccountAndroid(List<String> accounts) {
		AccountOperateRequest request = new AccountOperateRequest();
		request.setOperator_type(4);
		request.setPlatform(Platform.android);
		List<Account> accountList = new ArrayList<>();
		accounts.forEach(a -> accountList.add(new Account(a, 0)));
		request.setAccount_list(accountList);
		return androidPush.operateAccount(request.toString());
	}

	/**
	 * Android根据account 批量查询对应token
	 *
	 * @param accounts 账号列表
	 * @return
	 */
	public static JSONObject queryAccountAndroid(List<String> accounts) {
		AccountQueryRequest query = new AccountQueryRequest();
		query.setOperator_type(1);
		query.setPlatform(Platform.android);
		List<Account> list = new ArrayList<>();
		accounts.forEach(a -> list.add(new Account(a, AccountType.unknown.getType())));
		query.setAccount_list(list);

		return androidPush.queryAccount(query.toString());
	}

	public static JSONObject queryTokensAndroid(List<String> tokens) {
		AccountQueryRequest query = new AccountQueryRequest();
		query.setOperator_type(2);
		query.setPlatform(Platform.android);
		List<String> list = new ArrayList<>(tokens);
		query.setToken_list(list);

		return androidPush.queryAccount(query.toString());
	}

	/////////////-----------------------------------

	/**
	 * 创建安卓消息
	 */
	private static Message buildMessageAndroid(String title, String content, Map<String, Object> customParams) {
		Message message = new Message();
		MessageAndroid msg = new MessageAndroid();
		msg.setN_id(0);
		// 自定义参数
		if (customParams != null && !customParams.isEmpty()) {
			JSONObject custom = new JSONObject(customParams);
//			custom.put("url", "http://www.cnknys.com");
//			custom.put("orderCode", "113912731923162370");
			msg.setCustom_content(custom.toString());
		}
		msg.setClearable(1);// 可以清除
		msg.setRing(1);// 铃声
		msg.setLights(0);
		com.push.vo.ClickAction action = new com.push.vo.ClickAction();
		action.setAction_type(1);
		action.setActivity("com.cnknys.knysclas.revision.message.OrderMessageActivity");
		msg.setAction(action);
		// 消息内容
		message.setTitle(title);
		message.setContent(content);
		message.setAndroid(msg);
		// 通知时间
		AcceptTimePoint start = new AcceptTimePoint();
		start.setHour("08");
		start.setMin("00");
		AcceptTimePoint end = new AcceptTimePoint();
		end.setHour("22");
		end.setMin("00");
		AcceptTimePair pair = new AcceptTimePair();
		pair.setStart(start);
		pair.setEnd(end);
		message.setAccept_time(pair);

		return message;
	}

	/**
	 * 创建IOS消息
	 */
	private static Message buildMessageIOS(String title, String content, Map<String, Object> customParams) {
		Message message = new Message();
		MessageIOS msg = new MessageIOS();
		Aps aps = new Aps();
		Alert alert = new Alert();
		alert.setTitle(title);
		alert.setBody(content);
		aps.setAlert(alert);
		aps.setBadge(1);
		msg.setAps(aps);
		// 自定义参数
		if (customParams != null && !customParams.isEmpty()) {
			JSONObject custom = new JSONObject(customParams);
//			custom.put("url", "http://www.cnknys.com");
//			custom.put("orderCode", "113912731923162370");
			msg.setCustom(custom.toString());
		}
		message.setIos(msg);
		// 通知时间
		AcceptTimePoint end = new AcceptTimePoint();
		end.setHour("22");
		end.setMin("00");
		AcceptTimePoint start = new AcceptTimePoint();
		start.setHour("08");
		start.setMin("00");
		AcceptTimePair pair = new AcceptTimePair();
		pair.setStart(start);
		pair.setEnd(end);
		message.setAccept_time(pair);

		return message;
	}

}
