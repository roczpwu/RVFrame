package org.roc.wim.blog.person;

import com.roc.core.BLMessage;
import com.roc.core.Utils.ImageCutterUtil;
import com.roc.core.base.BaseAction;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * User: rocwu
 * Date: 2016/11/07
 * Time: 02:27
 * Desc: 个人资料头像上传Action类
 */
public class UploadPhotoAction extends BaseAction {

    private int x;
    private int y;
    private int w;
    private int h;
    private File fileData;
    private String fileName;
    private BLMessage blMessage;

    public String upload() {
        blMessage = new BLMessage();
        String saveRealFilePath = ServletActionContext.getServletContext().getRealPath("/upload/photo");
        File fileDir = new File(saveRealFilePath);
        if (!fileDir.exists()) { //如果不存在 则创建
            fileDir.mkdirs();
        }
        File savefile;
        fileName = System.currentTimeMillis() + "-" + fileData.getName();
        savefile = new File(saveRealFilePath + "/" + fileName);
        try {
            BufferedImage image = ImageIO.read(fileData);
            double destWidth = image.getWidth();
            double destHeight = image.getHeight();
            if (destWidth < w || destHeight < h)
                throw new RuntimeException("源图大小小于截取图片大小!");

            ImageCutterUtil.cutImage(fileData, savefile, x, y, w, h);
            String formatName = ImageCutterUtil.getImageFormatName(fileData);
            String preName = savefile.getName().substring(0, savefile.getName().lastIndexOf("."));

            blMessage.setSuccess(true);
            blMessage.setData("/displayPhoto.action?fileName="+preName+"."+formatName);
        } catch (Exception e) {
            blMessage.setSuccess(false);
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String display() throws Exception {
        HttpServletResponse response = null;
        ServletOutputStream out = null;
        response = ServletActionContext.getResponse();
        response.setContentType("multipart/form-data");
        out = response.getOutputStream();
        String saveRealFilePath = ServletActionContext.getServletContext().getRealPath("/upload/photo");
        File file = new File(saveRealFilePath + "/" + fileName);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public File getFileData() {
        return fileData;
    }

    public void setFileData(File fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BLMessage getBlMessage() {
        return blMessage;
    }

    public void setBlMessage(BLMessage blMessage) {
        this.blMessage = blMessage;
    }
}
