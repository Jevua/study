package com.mail.service;

import java.io.File;
import java.nio.file.FileSystems;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.mail.intf.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 简单的实现，优化点颇多
 */
@Component
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        //抄送
//        message.copyTo(copyTo);
        try {
            javaMailSender.send(message);
            logger.debug("邮件发送成功！");
        }
        catch (Exception e) {
            logger.error("邮件发送失败：" + e.getMessage(), e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            logger.debug("html邮件发送成功");
            //抄送
//            messageHelper.addCc(cc);
        }
        catch (MessagingException e) {
            logger.error("html邮件发送失败：" + e.getMessage(), e);
        }

    }

    @Override
    public void sendAttachMentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            //添加附件
            messageHelper.addAttachment(fileName, file);
            javaMailSender.send(message);
            logger.debug("附件邮件发送成功");
        }
        catch (MessagingException e) {
            logger.error("附件邮件发送失败：" + e.getMessage(), e);
        }
    }

    @Override
    public void sendInLineResourceMail(String to, String subject, String content, String resPath, String resId) {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(resPath));
            //添加静态资源
            messageHelper.addInline(resId, res);
            javaMailSender.send(message);
            logger.debug("静态资源邮件发送成功");
        }
        catch (MessagingException e) {
            logger.error("静态资源邮件发送失败：" + e.getMessage(), e);
        }
    }
}
