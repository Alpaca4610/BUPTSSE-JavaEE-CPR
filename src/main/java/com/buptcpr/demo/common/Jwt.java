//package com.buptcpr.demo.common;
//
//import org.springframework.util.StringUtils;
//
//public class Jwt {
//    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
//
//    /**
//     * 获取原始令牌
//     * remove 'Bearer ' string
//     *
//     * @param authorizationHeader
//     * @return
//     */
//    public static String getRawToken(String authorizationHeader) {
//        return authorizationHeader.substring(AUTHORIZATION_HEADER_PREFIX.length());
//    }
//
//    /**
//     * 获取令牌头
//     * @param rawToken
//     * @return
//     */
//    public static String getTokenHeader(String rawToken) {
//        return AUTHORIZATION_HEADER_PREFIX + rawToken;
//    }
//
//    /**
//     * 验证授权请求头
//     * @param authorizationHeader
//     * @return
//     */
//    public static boolean validate(String authorizationHeader) {
//        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(AUTHORIZATION_HEADER_PREFIX);
//    }
//
//    /**
//     * 获取授权头前缀
//     * @return
//     */
//    public static String getAuthorizationHeaderPrefix() {
//        return AUTHORIZATION_HEADER_PREFIX;
//    }
//}
