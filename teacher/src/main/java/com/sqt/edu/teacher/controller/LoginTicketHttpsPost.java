package com.sqt.edu.teacher.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

public class LoginTicketHttpsPost {

    static{
        init();
    }

    /**
     * 初始化
     */
    private static void init() {
    }

    // change or configure the follow properties appropriately
    private String apiId = "abcdf8c177032b6cd3e94837ae06b1a0#TEST";
    private final static String partnerId = "partnerId";
    private String key = "abc18cd070a41a17fab8dacb29586a18";
    private final static long appId = 9220000L;
    private String baseUrl = "https://openapi.test.pajk.cn/api/v1/";
    private final static String apiGroup = "user";
    private final static String apiName = "acquireLoginTicketWithExt";

    private static final Pattern pattern = Pattern.compile("[0-9a-fA-F]+");
    private SecureRandom random = new SecureRandom();

    public String acquireLoginTicketWithExt(Long appId, String openId) {
        String salt = String.valueOf(random.nextFloat());

        /**
         * __o_s: apiId
         * __o_v: verison
         * __o_r: 随机数
         * 所有的业务参数，形式如下arg1=a&arg2=b，这里arg1=appId&arg2=openId&arg3={}，appId=固定值，openId合作方传入，arg3写死
         */
        String body = String.format("__o_s=%s&__o_v=0.1.0&arg1=%s&arg2=%s&arg3={}&__o_r=%s", apiId, appId, openId, salt);

        body = TripleDESUtil.encrypt(body, key);
        String sig = TripleDESUtil.hmac(body, key, salt);
        String postURL = String.format("%s%s/%s?p=%s&v=%s&s=%s&h=%s", baseUrl, apiGroup, apiName, partnerId, "0.1.0", salt, sig);
        return parseResponse(makeRequest(postURL, "q=" + body));
    }

    private String parseResponse(String text) {
        System.out.println("response:"+text);
        Map obj = JSONObject.parseObject(text, Map.class);
        String objectStr = obj.get("object").toString();
        Integer code = (Integer) obj.get("code");
        if (code == 0) {
            String token = TripleDESUtil.decrypt(objectStr, key);
            if (token == null || token.length() < 3) {
                throw new RuntimeException("Get invalid ticket: " + (token == null ? "null" : token));
            }
            return token.substring(1, token.length() - 1);
        }
        throw new RuntimeException("Fail to call request ticket due to: " + obj.get("tips"));
    }

    private String makeRequest(String requestUrl, String requestBody) {
        try {
            System.out.println("requestUrl:"+requestUrl);
            System.out.println("requestBody:"+requestBody);
            URL url = new URL(requestUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(requestBody);
            out.flush();
            out.close();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = urlConnection.getInputStream().read(buffer, 0, buffer.length)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return new String(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to call save due to IOException ");
        }
    }

    private static Map<String, String> toMap(String data) {
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            if (data != null && data.trim().length() != 0) {
                data = data.trim();
                String[] cipher = data.split("&");
                for (String aCipher : cipher) {
                    String[] pairs = aCipher.split("=");
                    paramMap.put(pairs[0], URLDecoder.decode(pairs[1], "UTF-8"));
                }
            }
            return paramMap;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Fail to url decode");
        }
    }

    private static class TripleDESUtil {
        static String hmac(String dataStr, String keyStr, String saltStr) {
            try {
                byte[] data = dataStr.getBytes("UTF-8");
                byte[] key = keyStr.getBytes("UTF-8");
                byte[] salt = saltStr.getBytes("UTF-8");
                SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA1");
                Mac e = Mac.getInstance("HmacSHA1");
                e.init(keySpec);
                e.update(data);
                return toHexString(e.doFinal(salt));
            } catch (InvalidKeyException e) {
                throw new RuntimeException("Signature calculation error");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Signature calculation error");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Signature calculation error");
            }
        }

        static String encrypt(String message, String key) {
            try {
                SecretKeySpec var10 = new SecretKeySpec(toKeyBytes(key), "DESede");
                IvParameterSpec var11 = new IvParameterSpec(new byte[8]);
                Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
                cipher.init(1, var10, var11);
                byte[] plainTextBytes = message.getBytes("utf-8");
                return toHexString(cipher.doFinal(plainTextBytes));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Fail to encrypt");
            } catch (InvalidKeyException e) {
                throw new RuntimeException("Fail to encrypt");
            } catch (InvalidAlgorithmParameterException e) {
                throw new RuntimeException("Fail to encrypt");
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException("Fail to encrypt");
            } catch (BadPaddingException e) {
                throw new RuntimeException("Fail to encrypt");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Fail to encrypt");
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException("Fail to encrypt");
            }
        }

        static String decrypt(String cypher, String key) {
            try {
                Map<String, String> map = TripleDESUtil.toMap(cypher, false);
                String salt = map.get("s");
                String expSig = map.get("h");
                String dataStr = map.get("d");
                String actSig = TripleDESUtil.hmac(dataStr, key, salt);
                if (!actSig.equals(expSig)) {
                    throw new RuntimeException("Fail to validate signatures, potential data corruption");
                }
                Matcher match = pattern.matcher(dataStr);
                if (!match.matches()) {
                    throw new IllegalArgumentException("Invalid hex string: " + dataStr);
                } else if (dataStr.length() % 2 != 0) {
                    throw new IllegalArgumentException("Invalid hex string length: " + dataStr);
                }
                int len = dataStr.length();
                byte[] data = new byte[len / 2];
                for (int i = 0; i < len; i += 2) {
                    data[i / 2] = (byte) ((Character.digit(dataStr.charAt(i), 16) << 4)
                            + Character.digit(dataStr.charAt(i + 1), 16));
                }

                SecretKeySpec keySpec = new SecretKeySpec(toKeyBytes(key), "DESede");
                IvParameterSpec iv = new IvParameterSpec(new byte[8]);
                Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
                decipher.init(2, keySpec, iv);
                byte[] plainText = decipher.doFinal(data);

                String result = toMap(new String(plainText, "UTF-8"), true).get("__o_o");
                return result;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Fail to decrypt");
            } catch (InvalidKeyException e) {
                throw new RuntimeException("Fail to decrypt");
            } catch (InvalidAlgorithmParameterException e) {
                throw new RuntimeException("Fail to decrypt");
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException("Fail to decrypt");
            } catch (BadPaddingException e) {
                throw new RuntimeException("Fail to decrypt");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Fail to decrypt");
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException("Fail to decrypt");
            }
        }

        static Map<String, String> toMap(String data, boolean doUrlDecode) {
            try {
                Map<String, String> paramMap = new HashMap<String, String>();
                if (data != null && data.trim().length() != 0) {
                    data = data.trim();
                    String[] cipher = data.split("&");
                    for (String aCipher : cipher) {
                        String[] pairs = aCipher.split("=");
                        if (doUrlDecode) {
                            paramMap.put(pairs[0], URLDecoder.decode(pairs[1], "UTF-8"));
                        } else {
                            paramMap.put(pairs[0], pairs[1]);
                        }
                    }
                }
                return paramMap;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Fail to url decode");
            }
        }

        private static String toHexString(byte[] data) {
            StringBuilder buffer = new StringBuilder();
            for (byte aData : data) {
                buffer.append(String.format("%02x", 255 & aData));
            }
            return buffer.toString();
        }

        private static byte[] toKeyBytes(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            System.arraycopy(keyBytes, 0, keyBytes, 16, 8);
            return keyBytes;
        }
    }

    public static void main(String[] args) {
        LoginTicketHttpsPost client = new LoginTicketHttpsPost();
        System.out.printf("result=%s\n", client.acquireLoginTicketWithExt(appId, "openId"));
    }
}
