package com.growlithe.computer.common.mail;

import com.growlithe.computer.common.CandyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/7/29 23:29
 * @Description
 */
@Service(value = "mailService")
public class MailServiceImpl implements MailService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 发送文本邮件
     *
     * @param toEmail
     * @param subject
     * @param content
     */
    @Override
    public CandyResult sendSimpleMail(String toEmail, String subject, String content) {
        return this.sendSimpleMail(fromEmail, toEmail, subject, content);
    }

    /**
     * 发送普通文本
     *
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param content
     */
    @Override
    public CandyResult sendSimpleMail(String fromEmail, String toEmail, String subject, String content) {
        String[] toEmails = new String[]{toEmail};
        return this.sendSimpleMail(fromEmail, toEmails, subject, content);
    }

    /**
     * 发送普通文本
     *
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param content
     */
    @Override
    public CandyResult sendSimpleMail(String fromEmail, String[] toEmails, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmails);
        message.setSubject(subject);
        message.setText(content);
        return this.send(message);
    }

    /**
     * 发送普通文本
     *
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param content
     * @return
     */
    @Override
    public CandyResult sendSimpleMail(String fromEmail, List<String> toEmails, String subject, String content) {
        Integer arraySize = toEmails.size();
        String[] arrayOfToEmails = toEmails.toArray(new String[arraySize]);
        return this.sendSimpleMail(fromEmail, arrayOfToEmails, subject, content);
    }

    /**
     * 发送邮件
     *
     * @param message
     */
    private CandyResult send(SimpleMailMessage message) {
        CandyResult candyResult = new CandyResult();

        try {
            javaMailSender.send(message);
            LOGGER.info("简单邮件已经发送");
        } catch (Exception e) {
            LOGGER.error("发送简单邮件时发生异常", e);
            candyResult.setMessage("发送简单邮件时发生异常:[ " + e.getMessage() + " ]");
            return candyResult;
        }

        candyResult.setSuccess(true);
        return candyResult;
    }

//    /**
//     * 发送html邮件
//     *
//     * @param to
//     * @param subject
//     * @param content
//     */
//    @Override
//    public void sendHtmlMail(String to, String subject, String content) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        try {
//            //true表示需要创建一个multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//
//            javaMailSender.send(message);
//            LOGGER.info("html邮件发送成功");
//        } catch (MessagingException e) {
//            LOGGER.error("发送html邮件时发生异常！", e);
//        }
//    }
//
//
//    /**
//     * 发送带附件的邮件
//     *
//     * @param to
//     * @param subject
//     * @param content
//     * @param filePath
//     */
//    @Override
//    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//
//            FileSystemResource file = new FileSystemResource(new File(filePath));
//            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
//            helper.addAttachment(fileName, file);
//            javaMailSender.send(message);
//            LOGGER.info("带附件的邮件已经发送。");
//        } catch (MessagingException e) {
//            LOGGER.error("发送带附件的邮件时发生异常！", e);
//        }
//    }
//
//
//    /**
//     * 发送正文中有静态资源（图片）的邮件
//     *
//     * @param to
//     * @param subject
//     * @param content
//     * @param rscPath
//     * @param rscId
//     */
//    @Override
//    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//            System.out.println("content=" + content);
//            System.out.println("rscId=" + rscId);
//            System.out.println("rscPath=" + rscPath);
//            FileSystemResource res = new FileSystemResource(new File(rscPath));
//            helper.addInline(rscId, res);
//            javaMailSender.send(message);
//            LOGGER.info("嵌入静态资源的邮件已经发送。");
//        } catch (MessagingException e) {
//            LOGGER.error("发送嵌入静态资源的邮件时发生异常！", e);
//        }
//    }

}
