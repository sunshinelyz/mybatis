/**
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package io.binghe.mybatis.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;


/**
 * 打印结果拦截器 〈功能详细描述〉
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
    Object.class, RowBounds.class, ResultHandler.class})})
public class InterceptorForQry implements Interceptor
{
 
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object intercept(Invocation invocation)
        throws Throwable
    {
        Object result = invocation.proceed(); // 执行请求方法，并将所得结果保存到result中
        String str = JSONObject.toJSONString(result);
        System.out.println(str);
        return result;
    }
 
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }
 
    public void setProperties(Properties arg0)
    {}
}
