package com.example.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * i18n 国际化
 */
public class MyLocaleResolver implements org.springframework.web.servlet.LocaleResolver {
    /**
     * 解析请求中的语言参数
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        Locale locale = Locale.getDefault();
        if (lang != null) {
            String[] s = lang.split("_");
            // 国家 地区
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
