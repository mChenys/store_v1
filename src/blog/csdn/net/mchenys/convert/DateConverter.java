package blog.csdn.net.mchenys.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

import blog.csdn.net.mchenys.utils.StringUtils;

public class DateConverter implements Converter {


	@Override
	public Object convert(Class clazz, Object value) {
		//将object 转成 date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(StringUtils.isEmpty((String)value)) {
				return new Date(System.currentTimeMillis());
			}
			Date date = sdf.parse((String)value);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
