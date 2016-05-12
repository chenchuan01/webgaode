package com.sys.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author chenchuan
 * @date 2016年1月22日 
 * 图片输出控制器
 */
@Controller
@RequestMapping("/img")
public class ImgController {
	/**
	 * 验证码字符集
	 */
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	@RequestMapping("/code")
	public void verifyCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 在内存中创建图象
		int width = 80, height = 30;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位字符)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(codeSequence[random.nextInt(59)]);
			sRand += rand;
			// 设定字体
			g.setFont(getRandomFont());
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 18 * i + 6, 20);
		}

		// 将认证码存入session
		request.getSession().setAttribute("code", sRand);

		// 图象生效
		g.dispose();
		OutputStream out = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", out);
		out.close();
	}

	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	 /**
	  * <b>function:</b> 随机生成字体、文字大小
	  * @createDate 2010-8-23 上午10:44:22
	  * @author hoojo
	  * @return
	  */ 
	 public static Font getRandomFont() { 
	     String[] fonts = {"Georgia", "Segoe Script","Verdana", "Arial", "Tahoma", "Time News Roman", "Courier New", "Arial Black", "Quantzite"}; 
	     int fontIndex = (int)Math.round(Math.random() * (fonts.length - 1)); 
	     int fontSize = (int) Math.round(Math.random() * 4 + 16); 
	     return new Font(fonts[fontIndex], Font.PLAIN, fontSize); 
	 } 
}
