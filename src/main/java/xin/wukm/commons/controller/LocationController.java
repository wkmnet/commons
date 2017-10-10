/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午2:55
 * ---------------------------------
 */
package xin.wukm.commons.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.ext.interceptor.Restful;
import okhttp3.HttpUrl;
import org.apache.commons.lang.StringUtils;
import xin.wukm.commons.interceptor.LoginInterceptor;
import xin.wukm.commons.service.LocationService;
import xin.wukm.commons.util.HttpUtil;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午2:55
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@Before(Restful.class)
@Clear(LoginInterceptor.class)
public class LocationController extends ApiBaseController {

    public void index(){
        String a = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?" +
                "query=119.89.237.99&co=&=6006&t=1504782670365&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&" +
                "cb=jQuery1102024227860070688867_1504782569232&_=1504782569239";
        String ip = getPara("ip");
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https").host("sp0.baidu.com").
                addPathSegment("8aQDcjqpAAV3otqbppnN2DJv").addPathSegment("api.php").
                addQueryParameter("query",ip).addQueryParameter("co","").
                addQueryParameter("resource_id","6006").addQueryParameter("t","1504782670365").
                addQueryParameter("ie","utf8").addQueryParameter("oe","gbk").
                addQueryParameter("cb","op_aladdin_callback").addQueryParameter("format","json").
                addQueryParameter("tn","baidu").addQueryParameter("cb","jQuery1102024227860070688867_1504782569232").
                addQueryParameter("_","1504782569239");
        logger.info("request for get location:" + builder.build().toString());
        String body = HttpUtil.get(builder.build());
        logger.info("response for location:" + body);
        if(StringUtils.isBlank(body)){
            renderJson(fail("REQUEST_BODY_EMPTY","请求体为空"));
            return;
        }
        String resp = body.substring(body.indexOf("{"),body.lastIndexOf("}") + 1);
        logger.info("resp:" + resp);
        renderJson(ok(JSONObject.parseObject(resp, Map.class)));
    }

    public void show(){
        String index = getPara();
        logger.info("index:" + index);
        String address = getPara("ip");
        if(StringUtils.isBlank(address)){
            address = getRequest().getRemoteAddr();
        }
        logger.info("address:" + address);
        String p = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$";
        if(!Pattern.matches(p, address)) {
            logger.info("无效IP地址:" + address);
            renderJson(fail("INVALIDATE_IP","无效IP地址"));
        } else if(StringUtils.equalsIgnoreCase("1", index)){
            logger.info("识别IP:" + address);
            Map<String,Object> data = Duang.duang(LocationService.class).location(address);
            data.put("ip",address);
            logger.info("data:" + data);
            renderJson(ok(data));
        } else {
            logger.info("无效的索引:" + index);
            renderJson(fail("INVALIDATE_INDEX","无效的索引"));
        }
    }

}
