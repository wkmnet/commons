/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.writer
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 上午11:50
 * -------------------------------
 */
package xin.wukm.commons.writer;

import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Writer;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.writer
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 上午11:50
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LoggerWriter extends Writer {

    private Logger logger = Logger.getLogger(this.getClass());

    public void write(String str) throws IOException {
        StringBuilder builder = new StringBuilder(SystemUtils.LINE_SEPARATOR);
        builder.append(str);
        logger.info(builder.toString());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
