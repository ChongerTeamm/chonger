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
		// ��ȡServletContext����
		ServletContext sc=this.getServletContext();
		// ��ȡ��Ŀ���õ��ַ�������
		String encode=sc.getInitParameter("encode");
		// 1. ��ȡ�������
		String uploadPath="/WEB-INF/upload";
		String tempPath="/WEB-INF/temp";
		// ������ʱ�����������������map����
		// key-����������  value-������ֵ
		Map<String, String> paramMap=new HashMap<String, String>();

		// ��ȡFileItem�Ĺ���
		DiskFileItemFactory factory=new DiskFileItemFactory(1024*1024,new File(sc.getRealPath(tempPath)));
		// ��ȡServletFileUpload����
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		
		if(!fileUpload.isMultipartContent(req)){
			throw new RuntimeException("��ʹ����ȷ���ļ��ϴ���");
		}
		
		// ���õ����ļ��Ĵ�С����
		fileUpload.setFileSizeMax(1024*1024);//1mb
		// ���õ����ϴ��������ļ����ܴ�С����
		fileUpload.setSizeMax(1024*1024*5);//5mb
		
		// ����ļ�������������
		fileUpload.setHeaderEncoding(encode);
		
		// ʵ�ʽ�������ʵ�����ݣ���ÿ�������װ��һ��FileItem
		try {
			List<FileItem> list=fileUpload.parseRequest(req);
			if(list!=null){
				for(FileItem fileItem:list){
					if(fileItem.isFormField()){
						//��ͨ����
						String name=fileItem.getFieldName();
						String value=fileItem.getString(encode);
						//����������map����
						paramMap.put(name, value);
						//System.out.println("name="+name+",value="+value);
					}else{
						//�ļ��ϴ���
						String fileName=fileItem.getName();
						// ���ie���ļ���bug
						if(fileName.contains("\\")){
							fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
						}
						// ����ļ����ظ�������
						fileName=UUID.randomUUID()+"_"+fileName;
						
						// ��������ļ����±�������ļ�������
						String hcStr=Integer.toHexString(fileName.hashCode());
						// ����8λ��0
						while(hcStr.length()<8){
							hcStr="0"+hcStr;
						}
						String midPath="/";
						for(int i=0;i<hcStr.length();i++){
							midPath+=hcStr.charAt(i)+"/";
						}
						// ��ȡͼƬ��url�����û����ڷ��ʸ�ͼƬʹ��
						// /WEB-INF/upload/a/b/c/d/e/1/2/3/UUID_name.jpg
						String imgurl=uploadPath+midPath+fileName;
						paramMap.put("imgurl", imgurl);
						// ��ȡ�ļ�������ļ��еľ���·��
						String savePath=sc.getRealPath(uploadPath+midPath);
						// �ڷ������ϴ�����Ӧ���ļ���
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
							//ɾ����������ʱ�ļ�
							fileItem.delete();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			throw new RuntimeException(e.getMessage());
		}

		// 2. ����֤(��)
		
		// 3. ����serviceִ���߼�
		// ����һ��Prodʵ������װ������
		Prod prod=new Prod();
		// �����л�ȡ����������ӵ�prod��
		prod.setName(paramMap.get("name"));
		prod.setPrice(Double.parseDouble(paramMap.get("price")));
		prod.setCname(paramMap.get("cname"));
		prod.setPnum(Integer.parseInt(paramMap.get("pnum")));
		prod.setImgurl(paramMap.get("imgurl"));
		prod.setDescription(paramMap.get("description"));
	
		ProdService service=BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag=service.addProd(prod);
		if(flag){
			resp.getWriter().write("<h1>��Ʒ��ӳɹ�</h1>");
			resp.setHeader("refresh", "2;url="+req.getContextPath()+"/backend/_right.jsp");
		}else{
			resp.getWriter().write("<h1>��Ʒ���ʧ��</h1>");
			resp.setHeader("refresh", "2;url="+req.getContextPath()+"/backend/manageAddProd.jsp");
		}
		
		// 4. ����ִ�еĽ��ת����Ӧ����ͼ
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
