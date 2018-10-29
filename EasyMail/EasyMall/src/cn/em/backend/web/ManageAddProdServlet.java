package cn.em.backend.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.em.domain.Prod;
import cn.em.factory.BaseFactory;
import cn.em.service.ProdService;

public class ManageAddProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取ServletContext对象
		ServletContext sc=this.getServletContext();
		// 获取项目配置的字符集编码
		String encode=sc.getInitParameter("encode");
		// 1. 获取请求参数
		String uploadPath="/WEB-INF/upload";
		String tempPath="/WEB-INF/temp";
		// 用来临时保存所有请求参数的map集合
		// key-参数的名称  value-参数的值
		Map<String, String> paramMap=new HashMap<String, String>();

		// 获取FileItem的工厂
		DiskFileItemFactory factory=new DiskFileItemFactory(1024*1024,new File(sc.getRealPath(tempPath)));
		// 获取ServletFileUpload对象
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		
		if(!fileUpload.isMultipartContent(req)){
			throw new RuntimeException("请使用正确的文件上传表单");
		}
		
		// 设置单个文件的大小上限
		fileUpload.setFileSizeMax(1024*1024);//1mb
		// 设置单次上传的所有文件的总大小上限
		fileUpload.setSizeMax(1024*1024*5);//5mb
		
		// 解决文件名的乱码问题
		fileUpload.setHeaderEncoding(encode);
		
		// 实际解析请求实体内容，将每个表单项封装成一个FileItem
		try {
			List<FileItem> list=fileUpload.parseRequest(req);
			if(list!=null){
				for(FileItem fileItem:list){
					if(fileItem.isFormField()){
						//普通表单项
						String name=fileItem.getFieldName();
						String value=fileItem.getString(encode);
						//将参数存入map集合
						paramMap.put(name, value);
						//System.out.println("name="+name+",value="+value);
					}else{
						//文件上传项
						String fileName=fileItem.getName();
						// 解决ie的文件名bug
						if(fileName.contains("\\")){
							fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
						}
						// 解决文件名重复的问题
						fileName=UUID.randomUUID()+"_"+fileName;
						
						// 解决单个文件夹下保存过多文件的问题
						String hcStr=Integer.toHexString(fileName.hashCode());
						// 不满8位补0
						while(hcStr.length()<8){
							hcStr="0"+hcStr;
						}
						String midPath="/";
						for(int i=0;i<hcStr.length();i++){
							midPath+=hcStr.charAt(i)+"/";
						}
						// 获取图片的url，供用户后期访问该图片使用
						// /WEB-INF/upload/a/b/c/d/e/1/2/3/UUID_name.jpg
						String imgurl=uploadPath+midPath+fileName;
						paramMap.put("imgurl", imgurl);
						// 获取文件保存的文件夹的绝对路径
						String savePath=sc.getRealPath(uploadPath+midPath);
						// 在服务器上创建对应的文件夹
						new File(savePath).mkdirs();
						
						InputStream is=fileItem.getInputStream();
						FileOutputStream fos=null;
						try {
							fos=new FileOutputStream(savePath+"/"+fileName);
							byte[] array=new byte[100];
							int len=is.read(array);
							while(len!=-1){
								fos.write(array, 0, len);
								len=is.read(array);
							}
						} catch (Exception e) {
							throw new RuntimeException(e.getMessage());
						}finally{
							if(is!=null){
								is.close();
							}
							if(fos!=null){
								fos.close();
							}
							//删除产生的临时文件
							fileItem.delete();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			throw new RuntimeException(e.getMessage());
		}

		// 2. 表单验证(略)
		
		// 3. 调用service执行逻辑
		// 创建一个Prod实例，封装表单数据
		Prod prod=new Prod();
		// 将表单中获取到的数据添加到prod中
		prod.setName(paramMap.get("name"));
		prod.setPrice(Double.parseDouble(paramMap.get("price")));
		prod.setCname(paramMap.get("cname"));
		prod.setPnum(Integer.parseInt(paramMap.get("pnum")));
		prod.setImgurl(paramMap.get("imgurl"));
		prod.setDescription(paramMap.get("description"));
	
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag=service.addProd(prod);
		if(flag){
			resp.getWriter().write("<h1>商品添加成功</h1>");
			resp.setHeader("refresh", "2;url="+req.getContextPath()+"/backend/_right.jsp");
		}else{
			resp.getWriter().write("<h1>商品添加失败</h1>");
			resp.setHeader("refresh", "2;url="+req.getContextPath()+"/backend/manageAddProd.jsp");
		}
		
		// 4. 根据执行的结果转发对应的视图
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
