package www.hyb.untils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class bean {
    public static<T> T getParameterMap(Map p, T bean){
        try {
            BeanUtils.populate(bean,p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
