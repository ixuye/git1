package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swetake.util.Qrcode;

public class MyUtil {

	
	/**
	 * <pre>getCodeImg(生成验证码图片并输出到页面，保存验证信息至session中)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月20日 上午7:51:35    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月20日 上午7:51:35    
	 * 修改备注： </pre>
	 */
	public static void getCodeImg(HttpServletRequest request,HttpServletResponse response) {
		//建立图像缓冲区
		BufferedImage image = new BufferedImage(70, 40, BufferedImage.TYPE_INT_RGB);
		//建立绘制图片的对象 graphics
		Graphics graphics = image.getGraphics();
		//获取颜色
		Color color = new Color(240, 230, 140);
		//设置图片的底色、位置及大小
		graphics.setColor(color);
		graphics.fillRect(0, 0, 70, 40);
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
	 * <pre>genQrcode(生成二维码)   
	 * 创建人：徐叶    
	 * 创建时间：2017年7月20日 上午7:54:12    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月20日 上午7:54:12    
	 * 修改备注： 若text=null，则随机生成 
	 * @param text 二维码的text文本内容
	 * @param path 要存放二维码的路径</pre>
	 * @param request
	 * @param response</pre>
	 */
	public static void genQrcode(String text,String path,HttpServletRequest request,HttpServletResponse response){
		Qrcode x=new Qrcode();  
        x.setQrcodeErrorCorrect('M');//纠错等级（四种等级）  
        x.setQrcodeEncodeMode('B');//N代表数字，A代表a-Z,B代表其他字符  
        x.setQrcodeVersion(7);//版本  
          
        int width = 67 + 12 * (7 - 1);//设置二维码的大小公式：67 + 12 * （version - 1）  
        int height = 67 + 12 * (7 - 1);  
          
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Graphics2D gs = bufferedImage.createGraphics();  
          
        gs.setBackground(Color.WHITE);  
        gs.setColor(Color.BLACK);  
        gs.clearRect(0, 0, width, height);//清除画板的内容  
        int pixoff = 2;//添加一个偏移量  
        //二维码文本内容
        String c="";
        if (text==null) {
        	char[] array = "asdfghjkl123456789".toCharArray();
        	int index = array.length;
        	Random random = new Random();
        	for (int j = 0; j < 4; j++) {
        		c += array[random.nextInt(index)];
        	}
		}else{
			c=text;
		}
        byte[] d=null;
		try {
			d = c.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        if (d.length>0 && d.length <120){  
            boolean[][] s = x.calQrcode(d);  
  
            for (int i=0;i<s.length;i++){  
            for (int j=0;j<s.length;j++){  
                if (s[j][i]) {  
                gs.fillRect(j*3 + pixoff,i*3 + pixoff,3,3);  
                }  
            }  
            }  
        }  
        gs.dispose();  
        bufferedImage.flush();
        File muLu=new File(path);
        if (!muLu.exists()) {
			muLu.mkdir();
		}
        String uuid = UUID.randomUUID().toString();
        String newPath="";
        if (c.contains("http://")) {
        	newPath=muLu+"/"+uuid+"_.jpg";
		}else{
			newPath=muLu+"/"+uuid+"_"+c+".jpg";
		}
      //将二维码文本内容存到session中（此处可将其存到redis中）
      		request.getSession().setAttribute("picQrCode", c);
      		System.out.println("二维码========"+c);
        try {
        	ImageIO.write(bufferedImage, "jpg", new File(newPath));
			ImageIO.write(bufferedImage, "jpg",response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
