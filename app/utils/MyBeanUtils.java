package utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;

public class MyBeanUtils extends BeanUtils {
	static {
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		ConvertUtils.register(new DateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new IntegerConverter(null),
				java.lang.Integer.class);
		ConvertUtils.register(new LongConverter(null), java.lang.Long.class);
	}
}
