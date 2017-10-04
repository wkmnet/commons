/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : common
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-8-26
 * Time : 下午10:22
 * -------------------------------
 */
package xin.wukm.commons.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.Restful;
import okhttp3.HttpUrl;
import org.apache.commons.lang.StringUtils;
import xin.wukm.commons.util.HttpUtil;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : common
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-8-26
 * Time : 下午10:22
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@Before(Restful.class)
public class AccessUserController extends ApiBaseController {

    public void index(){
        int page = getParaToInt("page",1);

        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("http").host("idea.dami.ml").
                addPathSegment("api").addPathSegment("key.action").addQueryParameter("page",String.valueOf(page));
        logger.info("url:" + builder.build().toString());
        String body = HttpUtil.get(builder.build());
        logger.info("response for uri:" + body);
        if(StringUtils.isBlank(body)){
            renderJson(fail("REQUEST_BODY_EMPTY","请求体为空"));
            return;
        }
        JSONObject req = JSONObject.parseObject(body);
        if(req.getBoolean("success")){
            renderJson(ok(req.get("data")));
        } else {
            renderJson(fail(req.getString("code"),req.getString("message")));
        }
    }
}
