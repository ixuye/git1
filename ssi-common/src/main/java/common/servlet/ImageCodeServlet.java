package common.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class ImageCodeServlet
 */
public class ImageCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	// TODO Auto-generated method stub
		int width = 60;
		int height = 20;
		// 产生随机�?
		Random r = new Random();
		// 把随机数绘制成图�?
		BufferedImage imgbuf = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);// 产生缓冲图像,40�?0�?
		Graphics2D g = imgbuf.createGraphics();// 取得缓冲的绘制环�?
		// �?��绘制
		g.setColor(getRandColor(200, 250));// 设定背景�?
		g.fillRect(0, 0, width, height);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测�?
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			int xl = r.nextInt(12);
			int yl = r.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 随机产生100个干扰点，使图像中的验证码不易被其他分析程序探测�?
		g.setColor(getRandColor(120, 240));
		for (int i = 0; i < 100; i++) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		String scode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(r.nextInt(10));
			scode += rand;
			g.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110),
					20 + r.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生�?
			g.drawString(rand, 13 * i + 6, 16);
		}
		
		//关键代码   把生成的验证码放进Session中
		request.getSession().setAttribute("imageCode", scode);

		// 输出图像
		try {
			ServletOutputStream out = response.getOutputStream();// 得到HTTP的流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);// 产生JPEG的图像加码器
			encoder.encode(imgbuf);
			out.flush();
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//建立图像缓冲区
		BufferedImage image = new BufferedImage(70, 40, BufferedImage.TYPE_INT_RGB);
		//建立绘制图片的对象 graphics
		
		Graphics graphics = image.getGraphics();
		//获取颜色
		Color color = new Color(240, 230, 140);
		//设置图片的底色、位置及大小
		graphics.setColor(getRandColor(120, 240));
		graphics.fillRect(0, 0, 70, 50);
		//设置随机产生字符串的范围
		char[] array = "asdfghjkl123456789".toCharArray();
		//创建验证码字符串对象
		StringBuffer stringBuffer = new StringBuffer();
		//创建随机数对象
		Random random = new Random();
		//获取array的长度
		int i = array.length;
		//设置验证码字符串为4位
		for (int j = 0; j < 4; j++) {
			//在所给字符串长度内生成随机数
			int index = random.nextInt(i);
			//随机设置当前字符串颜色
			graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			//在对应位置绘制字符串
			graphics.drawString(array[index]+"", j*15+9, (random.nextInt(10)-5)+25);
			//拼接验证码字符串
			stringBuffer.append(array[index]);
		}
		//将验证码字符串存到session中（此处可将其存到redis中）
		request.getSession().setAttribute("picCode", stringBuffer.toString());
		try {
			//绘制对应验证码的图像
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (fc < 0)
			fc = 0;
		if (bc > 255)
			bc = 255;
		if (bc < 0)
			bc = 0;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
