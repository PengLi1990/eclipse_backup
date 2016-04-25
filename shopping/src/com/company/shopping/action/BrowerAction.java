package com.company.shopping.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class BrowerAction {
	protected final Logger logger = Logger	.getLogger(BrowerAction.class);
	/** ~~~ 上传文件的保存路径 */
	private static final String FILE_UPLOAD_DIR = "upload";
	/** ~~~ 上传文件的保存的下一级路径，标示存储类型 */
	private static final String FILE_UPLOAD_SUB_IMG_DIR = "image";

	public void execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String typeStr = request .getParameter("type");
		String floderName = request.getParameter("fo");
		
		if (logger.isDebugEnabled()) {
			logger.debug("浏览文件，文件格式:" + typeStr);
		}

		// 定位到目标文件夹 ： 上传目录
		String realPath = "";
//		if(StringUtils.isNotBlank(floderName)){
//			floderName = URLDecoder.decode(floderName);
//			// 如果请求中存在文件夹名称，则定位到文件夹中
//			realPath = request.getSession().getServletContext().getRealPath(floderName);
//			if(logger.isInfoEnabled()){
//				logger.info("sub floder:"+realPath);
//			}
//		}else if(StringUtils.equalsIgnoreCase(typeStr, "Image")){
//			// 如果请求中不存在文件夹名称，则使用默认的文件夹
//			realPath = request.getSession().getServletContext().getRealPath(File.separator+FILE_UPLOAD_DIR+ File.separator+FILE_UPLOAD_SUB_IMG_DIR);
//			if(logger.isInfoEnabled()){
//				logger.info("default floder:"+realPath);
//			}
//		}
		realPath = request.getSession().getServletContext().getRealPath(File.separator+FILE_UPLOAD_DIR+ File.separator+FILE_UPLOAD_SUB_IMG_DIR);
		System.out.println("realPath"+realPath);
		File folder = new File(realPath);
		System.out.println(folder.getParent());
		if(!folder.exists()){
			System.out.println("return exit");
			return;
		}
		
		// 存储子目录 ,路径需要从/freemarker开始
		List<String> subFolderSet = new ArrayList<String>();
		// 存储文件夹
		List<String> subFileerSet = new ArrayList<String>();
		
		File[] subFiles = folder.listFiles();
		if(null != subFiles && 0 < subFiles.length){
			for(int i=0;i < subFiles.length; i++){
				File _file = subFiles[i];
				if(_file.isDirectory()){
					subFolderSet.add("upload/image");
				} else {
					subFileerSet.add(_file.getName());
				}
			}
		}
		
		String callback = request.getParameter("CKEditorFuncNum");
		System.out.println(callback);
		PrintWriter out;
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
			out.println("<script type='text/javascript'><!--");
			
			// 定义点击选择js
			out.println("function choose(obj){");
			out.println("window.opener.CKEDITOR.tools.callFunction(" + callback
					+ ",obj)");
			out.println("window.close();");
			out.println("}");
			
			// 定义文件夹点击响应js
			out.println("function view(obj){window.location.href='browerServer.do?type=image&CKEditorFuncNum="+callback+"&fo='+obj;}");
			out.println("// --></script>");
			
			// 这里显示一个返回顶级目录，也就是返回freemarker目录
			out.print("<div style='width:100%;float:left;word-break:break-all;' onclick =view('" + URLEncoder.encode("/upload/image") + "')>");
			out.print("<span>[根目录]<span/>");
			out.print("</div>");
			System.out.println("here");
			
			// 如果是子文件夹，显示上级目录链接
			if(StringUtils.isNotBlank(floderName) && !checkIsRoot(folder)){
				String parent = "upload/image";
				out.print("<div style='width:100%;float:left;word-break:break-all;' onclick =view('" + URLEncoder.encode(parent) + "')>");
				out.print("<span>[上级目录]"+parent+"<span/>");
				out.print("</div>");
				if(logger.isDebugEnabled()){
					logger.debug("发现上级目录:"+ parent);
				}
			}
			
			// 如果是文件夹，则显示文件夹并且可以再次触发下级和上级目录
			if(0 < subFolderSet.size()){
				Iterator<String> subFolderSetIndex = subFolderSet.iterator();
				while(subFolderSetIndex.hasNext()){
					String ftemp = subFolderSetIndex.next();
					// 这里url传递的时候，文件分隔符会有问题，需要转义
					out.print("<div style='width:100%;float:left;word-break:break-all;' onclick =view('" + URLEncoder.encode(ftemp) + "')>");
					out.print("<span>[下级目录]"+ftemp+"</span>");
					out.print("</div>");
					
					if(logger.isDebugEnabled()){
						logger.debug("添加子目录："+ftemp);
					}
				}
			}
			
			// 如果是文件，则点击就选择文件到控件中
			if(0 < subFileerSet.size()){
				Iterator<String> subFileerSetIndex = subFileerSet.iterator();
				while(subFileerSetIndex.hasNext()){
					String ftemp = subFileerSetIndex.next();
					String f = "/upload/image";
					String fileUrl = f + File.separator + ftemp;
					fileUrl = StringUtils.replace(fileUrl, "//", "/");
					
					out.print("<div style='width:150px;height:150px;float:left;word-break:break-all;padding:5px;background:#666699;margin:5px;'>");
					out.print("<a href=\"javascript:void(0)\" mce_href=\"javascript:void(0)\" onclick=choose('"+fileUrl+"')><img style='border:none;width:145px;height:145px;' src=\""+fileUrl+"\" mce_src=\""+fileUrl+"\" title='"+fileUrl+"'/></a>");
					out.print("</div>");
					
					if(logger.isDebugEnabled()){
						logger.debug("添加文件："+fileUrl);
					}
				}
			}
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * 判断是否是根目录
	 * @param floderName
	 * @return
	 */
	private static boolean checkIsRoot(File folder){
		String name = folder.getName();
		return StringUtils.equalsIgnoreCase(name, FILE_UPLOAD_DIR);
	}
}
