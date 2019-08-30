package com.spring.cloud.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * @author: suxing.zhang
 * @Date: 2019/3/18 15:03
 * @Description:
 */
public class RsaUtils {
    public static final Logger logger = LoggerFactory.getLogger(RsaUtils.class);

    private RsaUtils() {
    }

    /**
     * 随机生成密钥对
     *
     * @param filePath
     */
    public static void genKeyPair(String filePath) {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            logger.debug("NoSuchAlgorithmException:{}", e);
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        if (keyPairGen != null) {
            keyPairGen.initialize(1024, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            try (FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");
                 FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");
                 BufferedWriter pubbw = new BufferedWriter(pubfw);
                 BufferedWriter pribw = new BufferedWriter(prifw);) {
                // 得到公钥字符串
                String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
                // 得到私钥字符串
                String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
                // 将密钥对写入到文件
                pubbw.write(publicKeyString);
                pubbw.flush();
                pribw.write(privateKeyString);
                pribw.flush();
            } catch (Exception e) {
                logger.debug("随机生成密钥对异常：{}", e);
            }
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param path
     * @return
     */
    public static String loadPublicKeyByFile(String path) {
        try {
            return filePathReader(path);
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("公钥数据读取错误");
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException("公钥输入流为空");
        }
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) {
        try {
            byte[] buffer = Base64.decodeBase64(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.info("无此算法：{}", e);
        } catch (InvalidKeySpecException e) {
            logger.info("公钥非法：{}", e);
        } catch (NullPointerException e) {
            throw new NullPointerException("公钥数据为空");
        }
        return null;
    }

    /**
     * 从文件中加载私钥
     *
     * @param path 私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static String loadPrivateKeyByFile(String path) {
        try {
            return filePathReader(path);
        } catch (IOException e) {
            logger.debug("私钥数据读取错误:{}", e);
        } catch (NullPointerException e) {
            logger.debug("私钥输入流为空:{}", e);
        }
        return null;
    }

    /**
     * 读取文件
     *
     * @param path
     * @return java.lang.String
     */
    private static String filePathReader(String path) throws IOException {
        StringBuilder sb;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String readLine = null;
            sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
        }
        return sb.toString();
    }


    /**
     * 从字符串加载私钥
     *
     * @param privateKeyStr
     * @return
     */
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) {
        try {
            byte[] buffer = Base64.decodeBase64(privateKeyStr);
            System.out.println(buffer.length);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            System.out.println("00000");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.info("无此算法：{}", e);
        } catch (InvalidKeySpecException e) {
            logger.info("公钥非法：{}", e);
        } catch (NullPointerException e) {
            throw new NullPointerException("公钥数据为空");
        }
        return null;
    }

    /**
     * 公钥加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return byte
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) {
        if (publicKey == null) {
            throw new NullPointerException("加密公钥为空, 请设置");
        }
        return getCommonMethod(publicKey, plainTextData, Cipher.ENCRYPT_MODE, "加密失败！");
    }

    /**
     * 私钥加密过程
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) {
        if (privateKey == null) {
            throw new NullPointerException("加密私钥为空, 请设置");
        }
        return getCommonMethod(privateKey, plainTextData, Cipher.ENCRYPT_MODE, "加密失败！");
    }

    /**
     * 私钥解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) {
        if (privateKey == null) {
            throw new NullPointerException("解密私钥为空, 请设置");
        }
        return getCommonMethod(privateKey, cipherData, Cipher.DECRYPT_MODE, "解密失败！");
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @return 明文
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) {
        if (publicKey == null) {
            throw new NullPointerException("解密公钥为空, 请设置");
        }
        return getCommonMethod(publicKey, cipherData, Cipher.DECRYPT_MODE, "解密失败！");
    }

    /**
     * 抽象公共方法
     *
     * @param key           公钥私钥
     * @param plainTextData byte[]
     * @param encryptMode   加密解密类型
     * @param s             异常
     * @return byte[]
     */
    private static byte[] getCommonMethod(Key key, byte[] plainTextData, int encryptMode, String s) {
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(encryptMode, key);
            return cipher.doFinal(plainTextData);
        } catch (GeneralSecurityException e) {
            logger.debug("操作失败，请重试");
        }
        return null;
    }

    public static void main(String[] args) {

    }
}