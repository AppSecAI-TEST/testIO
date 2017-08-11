package com.greenline.bio;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @Type Calculator
 * @Desc 计算的工具类
 * @author yangyi
 * @date 2017年8月9日
 * @Version V1.0
 */
public final class Calculator {
    
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static Object cal(String expression) throws ScriptException {
        // 可计算某个字符串
        return jse.eval(expression);
    }
}
