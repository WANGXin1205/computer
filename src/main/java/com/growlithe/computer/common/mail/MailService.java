package com.growlithe.computer.common.mail;

import com.growlithe.computer.common.CandyResult;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/7/29 23:27
 * @Description
 */
public interface MailService {
    /**
     * 发送普通文本
     *
     * @param toEmail
     * @param subject
     * @param content
     * @return
     */
    public CandyResult sendSimpleMail(String toEmail, String subject, String content);

    /**
     * 发送普通文本
     *
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param content
     * @return
     */
    public CandyResult sendSimpleMail(String fromEmail, String toEmail, String subject, String content);

    /**
     * 发送普通文本
     *
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param content
     */
    public CandyResult sendSimpleMail(String fromEmail, String[] toEmails, String subject, String content);

    /**
     * 发送普通文本
     *
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param content
     * @return
     */
    public CandyResult sendSimpleMail(String fromEmail, List<String> toEmails, String subject, String content);


//    /**
//     * 发送 html 代码的邮件
//     *
//     * @param to
//     * @param subject
//     * @param content
//     */
//    public void sendHtmlMail(String to, String subject, String content);
//
//    /**
//     * 发送附件的邮件
//     *
//     * @param to
//     * @param subject
//     * @param content
//     * @param filePath
//     */
//    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
//
//    /**
//     * 发送内嵌的文件
//     *
//     * @param to
//     * @param subject
//     * @param content
//     * @param rscPath
//     * @param rscId
//     */
//    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
