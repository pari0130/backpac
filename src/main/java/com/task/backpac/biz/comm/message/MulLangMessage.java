/*
 * Copyright (c) 2015 jinotech Co., Ltd
 * All right reserved.
 */
package com.task.backpac.biz.comm.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MulLangMessage {

    private final MessageSource messageSource;

    /**
     * @Purpose 메시지 다국어 변환
     * @Method Name : getMessage
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @param message : 다국어 변환 message
     * @Return : String
     * @Description
     */
    public String getMessage(String message) {
        return messageSource.getMessage(message, null, Locale.forLanguageTag(LocaleContextHolder.getLocale().getLanguage()));
    }

    /**
     * @Purpose 메시지 다국어 변환
     * @Method Name : getMessage
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @param code : 다국어 변환 code
     * @Return : String
     * @Description
     */
    public String getCode(String code) {
        return messageSource.getMessage(code, null, Locale.forLanguageTag(LocaleContextHolder.getLocale().getLanguage()));
    }
}
