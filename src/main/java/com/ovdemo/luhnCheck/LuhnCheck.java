package com.ovdemo.luhnCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuhnCheck {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(LuhnCheck.class);

    private static String stripNonNumeric(String str){
        return str.replaceAll("[^0-9]", "");
    }

    private static boolean checkLength(String number){
        if(number.length() <= 10 || number.length() > 19){
            LOGGER.error("Invalid PAN length: {}", number.length());
            return false;
        }
        return true;
    }
    
    public static boolean validate(Long number){
        return validate(number.toString());
    }

    public static boolean validate(String number){
        
        number = stripNonNumeric(number);
        
        if(!checkLength(number)){
            return false;
        }

        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(number).reverse().toString();
        
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0) { //this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            } else { //add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        
        return (s1 + s2) % 10 == 0;
    }
}
