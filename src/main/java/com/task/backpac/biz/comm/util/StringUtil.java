/*
 * Copyright (c) 2012 CORE BUILDER
 * All right reserved.
 */
package com.task.backpac.biz.comm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Random;

@Slf4j
@Component
public class StringUtil extends StringUtils {

    /**
     * @Purpose : random key size length 만큼 생성해서 return
     * @Method Name  : randomKey
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @Return : String
     * @Description
     */
    public static String randomKey(int size) {

		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			char ch = chars[rd.nextInt(chars.length)];
			sb.append(ch);
		}

		return sb.toString();
    }
}
