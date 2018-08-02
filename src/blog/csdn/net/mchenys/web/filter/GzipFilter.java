/*package blog.csdn.net.mchenys.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

*//**
 * 处理响应数据gzip压缩的filter
 * 
 * @author mChenys
 *
 *//*
@WebFilter(urlPatterns = "/*")
public class GzipFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("GzipFilter start 压缩页面");
		
				// 强转
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				HttpServletResponse httpServletResponse = (HttpServletResponse) response;
				
		 
				// 增强response
				MyHttpServletResponseWrapper responseWrapper = new MyHttpServletResponseWrapper(httpServletResponse);
		 
				// 放行,传递增强后的response对象
				chain.doFilter(httpServletRequest, responseWrapper);
		 
				// 处理响应的数据压缩
				// 创建gizp压缩流,底层是依赖字节流的
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				GZIPOutputStream gout = new GZIPOutputStream(baos);
		 
				// 获取responseWrapper中存储的响应数据
				byte[] oldByte = responseWrapper.getBytes();
				System.out.println(" filter 压缩之前: " + oldByte.length);
		 
				// 将响应数据写入到压缩流中
				gout.write(oldByte);
				// 注意, 这里要 关闭 一下, 这样可以 保证 数据 进到 底层流 中去
				gout.close();

				// 获取压缩后的响应数据
				byte[] newByte = baos.toByteArray();
				System.out.println(" filter 压缩 之后: " + newByte.length);
		 
				// 通知浏览器输出的数据是 经过gzip 压缩的,
				httpServletResponse.setHeader("content-encoding", "gzip");
		 
				// 通知 浏览器 压缩的 数据 长度
				httpServletResponse.setContentLength(newByte.length);
		 
				// 输出压缩后的数据
				httpServletResponse.getOutputStream().write(newByte);


		System.out.println("GzipFilter end 压缩页面");
	}

	@Override
	public void destroy() {

	}

}

*//**
 * response增强类,处理底层输出流
 * 
 * @author mChenys
 *
 *//*
class MyHttpServletResponseWrapper extends HttpServletResponseWrapper {

	private HttpServletResponse response;

	// 定义用于存储调用getOutputStream或者getWriter方法时输出的数据的底层流（字节流）
	private ByteArrayOutputStream boas = new ByteArrayOutputStream();

	private PrintWriter mPrintWrite;

	public MyHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyServletOutputStream(boas);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// 注意PrintWriter流是带缓冲功能和编码功能的
		this.mPrintWrite = new PrintWriter(new OutputStreamWriter(boas, "utf-8"));
		return mPrintWrite;
	}

	// 提供一个方法, 统一返回 baos中的 数据
	public byte[] getBytes() {
		if (null != this.mPrintWrite) {
			// 强制刷新,确保 数据 可以 进到 底层流 baos中
			this.mPrintWrite.close();
		}
		// 将保存在baos流对象中的数据返回给调用者
		return this.boas.toByteArray();

	}
}

// 处理ServletOutputStream,将其数据保存到baos流对象的类
class MyServletOutputStream extends ServletOutputStream {

	private ByteArrayOutputStream boas;

	public MyServletOutputStream(ByteArrayOutputStream boas) {
		this.boas = boas;
	}

	@Override
	public void write(int b) throws IOException {
		this.boas.write(b);
	}

}*/