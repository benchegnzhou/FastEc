package com.zbc.fastec.generators;

import com.zbc.latte_anntations.anntations.EntryGenerator;
import com.zbc.latte_core.templates.AppRegisterTemplate;

/**
 * Created by benchengzhou on 2019/11/11 0:11.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： WeChatAppRegisterEntry
 * 备    注：
 */
@EntryGenerator(
        packageName = "com.zbc.fastec.templates",
        entryTemplete  = AppRegisterTemplate.class
)
public interface WeChatAppRegisterEntry {


}
