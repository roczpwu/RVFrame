package org.roc.wim.blog.article;

import com.opensymphony.xwork2.ActionContext;
import com.roc.core.base.BaseAction;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * User: rocwu
 * Date: 2016/10/25
 * Time: 13:21
 * Desc: 博客图片上传Action类
 */
public class ImgUploadAction extends BaseAction {

    private String err = "";
    private String msg;              //返回信息
    private File fileData;           //上传文件
    private String fileDataFileName; //文件名

    public String upload() throws Exception {
        //获取response、request对象
        ActionContext ac = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);
        HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);

        response.setContentType("text/html;charset=gbk");

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String saveRealFilePath = ServletActionContext.getServletContext().getRealPath("/upload");
        File fileDir = new File(saveRealFilePath);
        if (!fileDir.exists()) { //如果不存在 则创建
            fileDir.mkdirs();
        }
        File savefile;
        fileDataFileName = System.currentTimeMillis() + "-" + fileDataFileName;
        savefile = new File(saveRealFilePath + "/" + fileDataFileName);
        try {
            FileUtils.copyFile(fileData, savefile);
        } catch (IOException e) {
            err = "错误"+e.getMessage();
            e.printStackTrace();
        }

        msg = "{\"success\":\"" + true + "\",\"file_path\":\"/displayArticleImg.action?fileDataFileName="+fileDataFileName+"\"}";
        out.print(msg); //返回msg信息
        out.close();
        return null;
    }

    public String display() throws Exception {
        HttpServletResponse response = null;
        ServletOutputStream out = null;
        response = ServletActionContext.getResponse();
        response.setContentType("multipart/form-data");
        out = response.getOutputStream();
        String saveRealFilePath = ServletActionContext.getServletContext().getRealPath("/upload");
        File file = new File(saveRealFilePath + "/" + fileDataFileName);
        FileInputStream is = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int length;
        do {
            length = is.read(bytes);
            if (length > 0) out.write(bytes, 0, length);
        } while (length > 0);
        out.flush();
        out.close();
        return null;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public File getFileData() {
        return fileData;
    }

    public void setFileData(File fileData) {
        this.fileData = fileData;
    }

    public String getFileDataFileName() {
        return fileDataFileName;
    }

    public void setFileDataFileName(String fileDataFileName) {
        this.fileDataFileName = fileDataFileName;
    }
}
