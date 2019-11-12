package com.zbc.fastec.generators;

import com.zbc.latte_anntations.anntations.EntryGenerator;
import com.zbc.latte_core.templates.WXEntryTemplate;

/**
 * Created by benchengzhou on 2019/11/11 0:11.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： WeChatEntry
 * 备    注：
 */
@EntryGenerator(
        packageName = "com.zbc.fastec",
        entryTemplete  = WXEntryTemplate.class
)
public interface WeChatEntry {


}
