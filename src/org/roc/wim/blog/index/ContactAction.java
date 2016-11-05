package org.roc.wim.blog.index;

import com.roc.components.mail.MailConfig;
import com.roc.components.mail.MailSenderInfo;
import com.roc.components.mail.SimpleMailSender;
import com.roc.core.BLMessage;
import com.roc.core.base.BaseAction;

/**
 * User: rocwu
 * Date: 2016/11/05
 * Time: 20:08
 * Desc: 联系我们Action类
 */
public class ContactAction extends BaseAction {

    private String name;
    private String email;
    private String title;
    private String content;
    private BLMessage blMessage;

    // 联系我们页
    public String contactPage() {
        return SUCCESS;
    }

    // 提交联系我们内容
    public String submitContact() {
        blMessage = new BLMessage();
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost(MailConfig.SERVER_HOST);
        mailInfo.setMailServerPort(MailConfig.SERVER_PORT);
        mailInfo.setValidate(true);
        mailInfo.setUserName(MailConfig.USER_NAME);
        mailInfo.setPassword(MailConfig.PASSWORD);
        mailInfo.setFromAddress(MailConfig.FROM_ADDR);
        mailInfo.setToAddress(MailConfig.TO_ADDR);
        mailInfo.setSubject(this.title);
        this.content = "【from】" + this.name + "\n" +
                "【email】" + this.email + "\n" + this.content;
        mailInfo.setContent(this.content);
        blMessage.setSuccess(SimpleMailSender.sendTextMail(mailInfo));
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BLMessage getBlMessage() {
        return blMessage;
    }

    public void setBlMessage(BLMessage blMessage) {
        this.blMessage = blMessage;
    }
}
