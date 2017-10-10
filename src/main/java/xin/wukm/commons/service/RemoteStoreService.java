/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.service
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午6:00
 * ---------------------------------
 */
package xin.wukm.commons.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.service
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午6:00
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class RemoteStoreService extends BaseService {

    Configuration configuration = new Configuration(Zone.zone0());

    public Object file(){
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
        String key = "your file key";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, key);
            System.out.println(fileInfo.hash);
            System.out.println(fileInfo.fsize);
            System.out.println(fileInfo.mimeType);
            System.out.println(fileInfo.putTime);
        } catch (QiniuException ex) {
            logger.error("list file QiniuException:" + ex.response.toString());
        }
        return null;
    }


    public Object list(){
        //...其他参数参考类注释
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                System.out.println(item.key);
                System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);
            }
        }
        return null;
    }

}
