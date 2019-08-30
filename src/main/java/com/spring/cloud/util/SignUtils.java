package com.spring.cloud.util;/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

/**
 * @author suxing.zhang
 * @date 2019/03/19
 */
public class SignUtils {
    public static final Logger logger = LoggerFactory.getLogger(SignUtils.class);

    /**
     * 获取签名
     *
     * @param privateKey
     * @param params
     * @return java.lang.String
     */
    public static String genSignature(String privateKey, Map<String, String> params) {
        //签名参数排序
        String assemblingStr = SignUtils.convertToSortStr(params);
        byte[] cipherData = RsaUtils.encrypt(RsaUtils.loadPrivateKeyByStr(privateKey), assemblingStr.getBytes());
        return Base64.encodeBase64String(cipherData);
    }

    /**
     * 校验签名
     *
     * @param publicKey
     * @param params    用来构造签名的参数
     * @param signature
     * @return boolean
     */
    public static boolean verifySignature(String publicKey, Map<String, String> params, String signature) {
        try {
            //参与签名的参数排序
            String assemblingStr = SignUtils.convertToSortStr(params);
            byte[] res = RsaUtils.decrypt(RsaUtils.loadPublicKeyByStr(publicKey), Base64.encodeBase64(signature.getBytes()));
            String decryprContent = new String(res);
            return assemblingStr.equals(decryprContent);
        } catch (Exception e) {
            logger.error("签名校验失败");
            return false;
        }
    }

    /**
     * 将Map转化为排序后的组字符串(用于签名参数格式化)
     *
     * @param params
     * @return
     */
    private static String convertToSortStr(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (isNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }
        return query.toString();
    }

    private static boolean isNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !StringUtils.isEmpty(value);
            }
        }
        return result;
    }
}
