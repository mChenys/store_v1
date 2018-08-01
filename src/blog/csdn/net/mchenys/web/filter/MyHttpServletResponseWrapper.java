package blog.csdn.net.mchenys.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * response增强类,处理底层输出流
 * 
 * @author mChenys
 *
 */
public class MyHttpServletResponseWrapper extends HttpServletResponseWrapper {

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
		this.mPrintWrite =new PrintWriter(new OutputStreamWriter(boas, "utf-8"));
		return mPrintWrite;
	}

	//提供一个方法, 统一返回 baos中的 数据
	public byte[] getBytes() {
		if(null !=this.mPrintWrite) {
			//强制刷新,确保 数据 可以 进到 底层流 baos中
			this.mPrintWrite.close();
		}
		//将保存在baos流对象中的数据返回给调用者
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

}
