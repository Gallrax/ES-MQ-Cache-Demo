package com.cx.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Date: Created on 2018/3/7
 * @Version: 1.0
 */
public class HttpUtil {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getUrl(HttpServletRequest request) {
        StringBuffer sb = request.getRequestURL();
        String params = request.getQueryString();
        if (null != params && !params.isEmpty()) {
            sb.append("?" + params);
        }
        return sb.toString();
    }
}
