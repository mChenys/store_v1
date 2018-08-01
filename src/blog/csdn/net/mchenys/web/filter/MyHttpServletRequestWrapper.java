package blog.csdn.net.mchenys.web.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * HttpServletRequest增强类
 * 
 * @author mChenys
 *
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		String[] values = this.getParameterValues(name);
		return values == null ? null : values[0];
	}

	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = this.getParameterMap();
		String[] values = map.get(name);
		return values;
	}

	private boolean hasEncode;// 避免重复编码

	/**
	 * 上面2个方法最终是调用这个方法,所有统一在这个方法处理请求参数乱码问题
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = super.getParameterMap();
		String method = this.request.getMethod();
		try {
			if (method.equalsIgnoreCase("post")) {
				// post请求,直接设置这句话即可
				this.request.setCharacterEncoding("utf-8");
			} else if (method.equalsIgnoreCase("get")) {
				// 通过遍历参数名获取说有的参数值
				if (!hasEncode) {
					hasEncode = true;
					for (String name : map.keySet()) {
						String[] values = map.get(name);
						for (int i = 0; i < values.length; i++) {
							// 在这里处理编码
							values[i] = new String(values[i].getBytes("iso8859-1"), "utf-8").trim();
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return map;
	}
}
