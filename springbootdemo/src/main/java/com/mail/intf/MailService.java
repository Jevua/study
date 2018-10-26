package com.mail.intf;

import org.springframework.stereotype.Component;

/**
 * @author jevua
 */
@Component
public interface MailService {

    /**
     * 简单邮件发送
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content);


    /**
     * 富文本发送
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String to, String subject, String content);


    /**
     * 带附件邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachMentsMail(String to, String subject, String content, String filePath);


    /**
     * 静态资源发送
     * @param to
     * @param subject
     * @param content
     * @param resPath
     * @param resId
     */
    void sendInLineResourceMail(String to, String subject, String content, String resPath, String resId);
}
