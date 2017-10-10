/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.service
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午3:00
 * ---------------------------------
 */
package xin.wukm.commons.service;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import xin.wukm.commons.util.Constants;
import xin.wukm.commons.util.IPUtil;

import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.service
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午3:00
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LocationService extends BaseService {

    public Map<String,Object> location(String address){
        Map<String,Object> result = Maps.newHashMap();
        String[] ls;
        if(Constants.SCAN_FLAG){
            ls = IPUtil.find(address);
        } else {
            ls = IPUtil.scan(address);
            Constants.SCAN_FLAG = true;
        }
        if(ls == null || ls.length == 0){
            result.put("location","");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for(String s:ls){
                if(!StringUtils.isBlank(s.trim())) {
                    stringBuilder.append(s);
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            result.put("location",stringBuilder.toString());
        }
        return result;
    }

}
