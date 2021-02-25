package com.haso.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化工具类
 */
@Component
public class MessageUtil {
    private static MessageSource messageSource;

    public MessageUtil(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     *
     * @param msgCode 文字编码
     * @return 文字内容
     */
    public static String get(String msgCode) {
        try {
            return messageSource.getMessage(msgCode, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgCode;
        }
    }

    /**
     * 获取单个国际化翻译值
     *
     * @param msgCode       文字编码
     * @param replaceValues 文字中要替换的内容（可以是多个）
     * @return 替换后的文字内容
     */
    public static String get(String msgCode, Object... replaceValues) {
        try {
            return messageSource.getMessage(msgCode, replaceValues, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgCode;
        }
    }

    /**
     * 获取单个提示信息国际化翻译值
     *
     * @param msgId         提示信息ID
     * @param replaceValues 提示信息中要替换的内容（可以是多个）
     * @return 替换后的文字内容
     */
    public static String getMsg(String msgId, Object... replaceValues) {
        msgId = "message." + msgId;
        try {
            return messageSource.getMessage(msgId, replaceValues, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgId;
        }
    }

    /**
     * 获取单个标签文字 国际化翻译值
     *
     * @param labelId 标签文字ID
     * @return 标签文字内容
     */
    public static String getLab(String labelId) {
        labelId = "labels." + labelId;
        try {
            return messageSource.getMessage(labelId, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return labelId;
        }
    }
}
