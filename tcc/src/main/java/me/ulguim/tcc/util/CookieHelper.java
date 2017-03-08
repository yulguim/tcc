package me.ulguim.tcc.util;

import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.util.hash.HashSHA;
import me.ulguim.tcc.entity.Account;

/**
 * Created by yulle on 08/03/17.
 */
public class CookieHelper {

	public static String generateCookie(String... params) {
		StringBuilder cookie = new StringBuilder();
		for (String param: params) {
			cookie.append(param);
		}
		cookie.append(DataUtil.format(DataUtil.getTimestamp(), "yyyy-MMM-dd mm:HH:ss:sss"));
		return HashSHA.SHA512(cookie.toString());
	}

}
