package app.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifyCode
 */
@WebServlet("/verify.do")
public class VerifyCode extends HttpServlet {
	
	private static String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("image/jpeg");
		
		BufferedImage bi = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		
		
		String srand = "";
		Random random = new Random();
		for(int i=0;i<4;i++){
			srand+=s.charAt(random.nextInt(s.length()));
		}
		
		g.setColor(new Color(220,220,220));
		g.fillRect(0, 0, 100, 100);
		
		g.setColor(Color.black);
		
		
		g.drawString(srand, 10, 40);
		
		for(int i=0;i<10;i++){
			g.setColor(new Color(random.nextInt(220),random.nextInt(220),random.nextInt(220)));
			g.drawLine(4,4,random.nextInt(90),random.nextInt(90));
		}
		request.getSession().setAttribute("verifycode", srand);
		
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}

}
