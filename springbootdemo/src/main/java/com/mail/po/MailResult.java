package com.mail.po;

public class MailResult {
    private String retCode;
    private String retMsg;

    public MailResult() {
        this.retCode = "00";
        this.retMsg = "发送成功";
    }
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
