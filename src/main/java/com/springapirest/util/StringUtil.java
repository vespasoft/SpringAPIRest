package com.springapirest.util;

import java.util.Random;

/**
 *
 * @author luisvespa
 */
public class StringUtil {
    
    public static String generateTokenString (int length)  {
            String token="";
            char[] chars = "1234567890".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                    char c = chars[random.nextInt(chars.length)];
                    sb.append(c);
            }
            token = sb.toString();
            System.out.println(token);
            return token;
    }
    
    
}

