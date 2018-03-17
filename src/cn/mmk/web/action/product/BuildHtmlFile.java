package cn.mmk.web.action.product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import cn.mmk.bean.product.ProductInfo;

public class BuildHtmlFile {
	
	public static void createProductHtml(ProductInfo product, File saveDir){
		try {
			if(!saveDir.exists()) saveDir.mkdirs();
			VelocityContext context = new VelocityContext();
			context.put("product", product);
			Template template = Velocity.getTemplate("product/productview.html");
			FileOutputStream outStream = new FileOutputStream(new File(saveDir, product.getId()+".shtml"));
			OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
