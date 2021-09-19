/*
package com.xinge;

import com.tencent.xinge.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

*/
/**
 * XingePush
 *
 * @author PJ
 * @since 2019-06-17 15:39
 *//*

//@Component
public class XingePush {

	@Value("${ENV:prod}")
	private String ENV = "env";
	// Android
	private XingeApp androidXinge;
	// iOS
	private XingeApp iOSXinge;
	// iOS访问ID
	private static final Long IOS_ACCESS_ID = 2200335395L;
	// iOS访问KEY
	private static final String IOS_ACCESS_KEY = "2475626e021c7ed664b7bdfaafe740e4";
	// Android访问ID
	private static final Long ANDROID_ACCESS_ID = 2100335429L;
	// Android访问KEY
	private static final String ANDROID_ACCESS_KEY = "6af3b623fb98504b46ea649da91864ce";

	public XingePush() {
		this.iOSApp();
		this.androidApp();
	}

	//	@Bean
	protected XingeApp iOSApp() {
		this.iOSXinge = new XingeApp(IOS_ACCESS_ID, IOS_ACCESS_KEY);
		return this.iOSXinge;
	}

	//	@Bean
	protected XingeApp androidApp() {
		this.androidXinge = new XingeApp(ANDROID_ACCESS_ID, ANDROID_ACCESS_KEY);
		return this.androidXinge;
	}

	// ======================= 安卓 =======================

	*/
/**
	 * 推送给多个账号，限Android设备使用<br> 超过10000，建议改用 {@link #pushAccountListMultipleForAndroid(String, String,
	 * List)} 接口
	 *
	 * @param title    title
	 * @param content  内容
	 * @param accounts 账号
	 *//*

	public JSONObject pushAccountForAndroid(String title, String content, List<String> accounts) {
		if (accounts.size() <= 10000) {
			Message message = new Message();
			message.setTitle(title);
			message.setContent(content);
			message.setType(Message.TYPE_NOTIFICATION);// 通知类型
			return androidXinge.pushAccountList(0, accounts, message);
		} else {
			return pushAccountListMultipleForAndroid(title, content, accounts);
		}
	}

	*/
/**
	 * 大批量下发给账号Account，限Android系统使用
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param accounts 账号
	 *//*

	public JSONObject pushAccountListMultipleForAndroid(String title, String content,
														List<String> accounts) {
		Message message = new Message();
		message.setTitle(title);
		message.setContent(content);
		message.setType(Message.TYPE_NOTIFICATION);// 通知类型
		return pushAccountListMultipleForAndroid(accounts, message);
	}

	*/
/**
	 * 推送消息给大批量账号，可对同一个pushId多次调用此接口，限Android系统使用 <br/> 建议用户采用此接口自行控制发送时间
	 *
	 * @param accounts 账号列表
	 * @param message  消息
	 * @return 服务器执行结果，JSON形式
	 *//*

	public JSONObject pushAccountListMultipleForAndroid(List<String> accounts, Message message) {
		JSONObject ret = androidXinge.createMultipush(message);
		if (ret.getInt("ret_code") != 0) {
			return ret;
		} else {
			JSONObject result = new JSONObject();

			List<List<String>> list = splitList(accounts, 1000);
			list.forEach(l -> result.append("all",
					androidXinge.pushAccountListMultiple(ret.getJSONObject("result").getLong("push_id"), l)));
			return result;
		}
	}

	*/
/**
	 * 推送给多个tags对应的设备，限Android系统使用，标签关系是AND
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param tags     标签列表
	 * @param linkType 1:打开activity或app,2:打开url,3:打开Intent
	 * @param linkAdd  打开的地址或URL
	 *//*

	public JSONObject pushTagsAndForAndroid(String title, String content,
											List<String> tags, int linkType, String linkAdd) {
		return pushTagsForAndroid(title, content, tags, linkType, linkAdd, "AND");
	}

	*/
/**
	 * 推送给多个tags对应的设备，限Android系统使用，标签关系是OR
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param tags     标签列表
	 * @param linkType 1:打开activity或app,2:打开url,3:打开Intent
	 * @param linkAdd  打开的地址或URL
	 *//*

	public JSONObject pushTagsOrForAndroid(String title, String content,
										   List<String> tags, int linkType, String linkAdd) {
		return pushTagsForAndroid(title, content, tags, linkType, linkAdd, "OR");
	}

	*/
/**
	 * 推送给多个tags对应的设备，限Android系统使用
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param tags     指定推送的tag列表
	 * @param linkType 1:打开activity或app,2:打开url,3:打开Intent
	 * @param linkAdd  打开的地址或URL
	 * @param tagOp    多个tag的运算关系，取值必须是下面之一： AND OR
	 *//*

	private JSONObject pushTagsForAndroid(String title, String content, List<String> tags,
										  int linkType, String linkAdd, String tagOp) {
		ClickAction action = new ClickAction();

		if (linkType == ClickAction.TYPE_URL) {
			action.setUrl(linkAdd);
			action.setActionType(ClickAction.TYPE_URL);// 打开URL
		} else if (linkType == ClickAction.TYPE_ACTIVITY) {
			action.setActivity(linkAdd);
			action.setActionType(ClickAction.TYPE_ACTIVITY);// 打开activity或APP本身
		}

		Style style = new Style(1, 1, 0, 1, 0);

		Message msg = new Message();
		msg.setType(Message.TYPE_NOTIFICATION);
		msg.setTitle(title);
		msg.setContent(content);
		msg.setStyle(style);
		msg.setAction(action);

		return androidXinge.pushTags(0, tags, tagOp, msg);
	}

	// ======================= iOS =======================

	*/
/**
	 * 推送给多个账号，限iOS设备使用 <br/> 如果目标账号数超过10000，建议改用 {@link #pushAccountListMultipleForIOS(String,
	 * String, List)} 接口
	 *
	 * @param title    title
	 * @param content  内容
	 * @param accounts 账号
	 *//*

	public JSONObject pushAccountForIOS(String title, String content, List<String> accounts) {
		if (accounts.size() <= 10000) {
			MessageIOS msg = new MessageIOS();
			JSONObject apns = new JSONObject();
			apns.put("title", title);
			apns.put("body", content);
			apns.put("badge", 1);
			msg.setAlert(apns);
			msg.setType(MessageIOS.TYPE_APNS_NOTIFICATION);
			// 使用setRaw之后,只有expireTIme,sendTime设置项生效
			return iOSXinge.pushAccountList(0, accounts, msg,
					Objects.equals(ENV.toLowerCase(), "prod") ? XingeApp.IOSENV_PROD : XingeApp.IOSENV_DEV);
		} else {
			return pushAccountListMultipleForIOS(title, content, accounts);
		}
	}

	*/
/**
	 * 大批量下发给账号Account，限iOS系统使用
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param accounts 账号
	 *//*

	public JSONObject pushAccountListMultipleForIOS(String title, String content,
													List<String> accounts) {
		int env =
				Objects.equals(ENV.toLowerCase(), "prod") ? XingeApp.IOSENV_PROD : XingeApp.IOSENV_DEV;
		MessageIOS msg = new MessageIOS();
		msg.setType(MessageIOS.TYPE_APNS_NOTIFICATION);
		JSONObject apns = new JSONObject();
		apns.put("title", title);
		apns.put("body", content);
		apns.put("badge", 1);
		msg.setAlert(apns);

		return pushAccountListMultipleForIOS(accounts, msg, env);
	}

	*/
/**
	 * 推送消息给大批量账号，可对同一个pushId多次调用此接口，限Android系统使用 <br/> 建议用户采用此接口自行控制发送时间
	 *
	 * @param accounts 账号列表
	 * @param message  消息
	 * @return 服务器执行结果，JSON形式
	 *//*

	public JSONObject pushAccountListMultipleForIOS(List<String> accounts,
													MessageIOS message, int env) {
		JSONObject ret = iOSXinge.createMultipush(message, env);
		if (ret.getInt("ret_code") != 0) {
			return ret;
		} else {
			List<List<String>> list = splitList(accounts, 1000);

			JSONObject result = new JSONObject();
			list.forEach(l -> result.append("all",
					iOSXinge.pushAccountListMultiple(ret.getJSONObject("result").getLong("push_id"), l)));

			return result;
		}
	}

	*/
/**
	 * 推送给多个tags对应的设备，限iOS系统使用，标签关系是AND
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param tags     标签列表
	 * @param linkType 1:打开activity或app,2:打开url,3:打开Intent
	 * @param linkAdd  打开的地址或URL
	 *//*

	public JSONObject pushTagsAndForIOS(String title, String content,
										List<String> tags, int linkType, String linkAdd) {
		return pushTagsForIOS(title, content, tags, linkType, linkAdd, "AND");
	}

	*/
/**
	 * 推送给多个tags对应的设备，限IOS系统使用，标签关系是OR
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param tags     标签列表
	 * @param linkType 1:打开activity或app,2:打开url,3:打开Intent
	 * @param linkAdd  打开的地址或URL
	 *//*

	public JSONObject pushTagsOrForIOS(String title, String content,
									   List<String> tags, int linkType, String linkAdd) {
		return pushTagsForIOS(title, content, tags, linkType, linkAdd, "OR");
	}

	*/
/**
	 * 推送给多个tags对应的设备，限IOS系统使用
	 *
	 * @param title    标题
	 * @param content  内容
	 * @param tags     指定推送的tag列表
	 * @param linkType 1:打开activity或app,2:打开url,3:打开Intent
	 * @param linkAdd  打开的地址或URL
	 * @param tagOp    多个tag的运算关系，取值必须是下面之一： AND OR
	 *//*

	private JSONObject pushTagsForIOS(String title, String content, List<String> tags,
									  int linkType, String linkAdd, String tagOp) {
		ClickAction action = new ClickAction();

		if (linkType == ClickAction.TYPE_URL) {
			action.setUrl(linkAdd);
			action.setActionType(ClickAction.TYPE_URL);// 打开URL
		} else if (linkType == ClickAction.TYPE_ACTIVITY) {
			action.setActionType(ClickAction.TYPE_ACTIVITY);// 打开activity或APP本身
			action.setActivity(linkAdd);
		}

		Style style = new Style(1, 1, 0, 1, 0);

		Message msg = new Message();
		msg.setType(Message.TYPE_NOTIFICATION);
		msg.setTitle(title);
		msg.setContent(content);
		msg.setAction(action);
		msg.setStyle(style);

		return iOSXinge.pushTags(0, tags, tagOp, msg);
	}

	// ======================= 通用查询 =======================

	*/
/**
	 * 查询消息推送状态
	 *
	 * @param pushIds 推送成功返回的ID
	 *//*

	public JSONArray queryPushStatus(List<String> pushIds) {
		JSONArray array = new JSONArray();
		array.put(androidXinge.queryPushStatus(pushIds));
		array.put(iOSXinge.queryPushStatus(pushIds));
		return array;
	}

	*/
/**
	 * 查询设备数量
	 *//*

	public Long queryDeviceCount() {
		JSONObject o = androidXinge.queryDeviceCount();
		long ret = 0L;
		if (this.validatePushRetStatus(o)) {
			ret += o.getJSONObject("result").getLong("device_num");
		}
		JSONObject o1 = iOSXinge.queryDeviceCount();
		if (this.validatePushRetStatus(o)) {
			ret += o.getJSONObject("result").getLong("device_num");
		}
		return ret;
	}

	*/
/**
	 * 查询某个tag下的token数量
	 *
	 * @param tag 指定的标签
	 *//*

	public Long queryTagTokenNum(String tag) {
		JSONObject o = androidXinge.queryTagTokenNum(tag);
		long ret = 0L;
		if (this.validatePushRetStatus(o)) {
			ret += o.getJSONObject("result").getLong("device_num");
		}
		JSONObject o1 = iOSXinge.queryTagTokenNum(tag);
		if (this.validatePushRetStatus(o)) {
			ret += o.getJSONObject("result").getLong("device_num");
		}
		return ret;
	}

	*/
/**
	 * 查询token相关的信息，包括最近一次活跃时间，离线消息数等
	 *
	 * @param deviceToken 目标设备token
	 * @return 服务器执行结果，JSON形式
	 *//*

	public JSONArray queryInfoOfToken(String deviceToken) {
		JSONArray array = new JSONArray();
		array.put(androidXinge.queryInfoOfToken(deviceToken));
		array.put(iOSXinge.queryInfoOfToken(deviceToken));
		return array;
	}

	*/
/**
	 * 查询账号绑定的token
	 *
	 * @param account 目标账号
	 * @return 服务器执行结果，JSON形式
	 *//*

	public JSONArray queryTokenOfAccount(String account) {
		JSONArray array = new JSONArray();
		array.put(androidXinge.queryTokensOfAccount(account));
		array.put(iOSXinge.queryTokensOfAccount(account));
		return array;
	}

	*/
/**
	 * 查询设备下所有的tag
	 *
	 * @param deviceToken 目标设备token
	 * @return 服务器执行结果，JSON形式
	 *//*

	public JSONArray queryTokenTags(String deviceToken) {
		JSONArray array = new JSONArray();
		array.put(androidXinge.queryTokenTags(deviceToken));
		array.put(iOSXinge.queryTokenTags(deviceToken));
		return array;
	}

	// ======================= 变更 =======================


	*/
/**
	 * 批量设置标签
	 *
	 * @param tag   标签
	 * @param token 指定删除的token
	 *//*

	public void batchSetTag(String tag, List<String> token) {
		Map<String, String> map = new LinkedHashMap<>();
		token.forEach(t -> map.put(tag, t));
		batchSetTag(map);
	}

	*/
/**
	 * 批量设置标签
	 *
	 * @param map key为tag，value为token
	 *//*

	public void batchSetTag(Map<String, String> map) {
		List<TagTokenPair> pairs = new ArrayList<>();
		map.forEach((k, v) -> pairs.add(new TagTokenPair(k, v)));
		androidXinge.BatchSetTag(pairs);
		iOSXinge.BatchSetTag(pairs);
	}

	*/
/**
	 * 批量删除标签
	 *
	 * @param tag   标签
	 * @param token 指定删除的token
	 *//*

	public void batchDelTag(String tag, List<String> token) {
		Map<String, String> map = new LinkedHashMap<>();
		token.forEach(t -> map.put(tag, t));
		batchDelTag(map);
	}

	*/
/**
	 * 批量删除标签
	 *
	 * @param map key为tag，value为token
	 *//*

	public void batchDelTag(Map<String, String> map) {
		List<TagTokenPair> pairs = new ArrayList<>();
		map.forEach((k, v) -> pairs.add(new TagTokenPair(k, v)));
		androidXinge.BatchDelTag(pairs);
		iOSXinge.BatchDelTag(pairs);
	}

	*/
/**
	 * 删除指定账号和token的绑定关系（token仍然有效）
	 *
	 * @param account     目标账号
	 * @param deviceToken 目标设备token
	 * @return 服务器执行结果，JSON形式
	 *//*

	public void deleteTokenOfAccount(String account, String deviceToken) {
		androidXinge.deleteTokenOfAccount(account, deviceToken);
		iOSXinge.deleteTokenOfAccount(account, deviceToken);
	}

	*/
/**
	 * 删除指定账号绑定的所有token（token仍然有效）
	 *
	 * @param account 目标账号
	 *//*

	public void deleteAllTokenOfAccount(String account) {
		androidXinge.deleteAllTokensOfAccount(account);
		iOSXinge.deleteAllTokensOfAccount(account);
	}

	*/
/**
	 * 拆分List
	 *//*

	public static <T> List<List<T>> splitList(List<T> list, int size) {
		List<List<T>> result = new ArrayList<>();
		for (int begin = 0; begin < list.size(); begin = begin + size) {
			int end = (begin + size > list.size() ? list.size() : begin + size);
			result.add(list.subList(begin, end));
		}
		return result;
	}

	*/
/**
	 * 检查请求是否成功
	 *//*

	private boolean validatePushRetStatus(JSONObject jo) {
		try {
			return jo != null && jo.getInt("ret_code") == 0;
		} catch (JSONException e) {
			return false;
		}
	}

}
*/
