package com.gc.system.exceptions;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 作者  zhuangwentao
 * 日期  2016-04-20.
 */
public class BussinessException extends RuntimeException {
    private static final long serialVersionUID = -3950157462518464843L;

    private String code;
    private String messageKey;
    public BussinessException(String code, String message)
    {
        super(message);
        this.code = code;
    }

    public BussinessException(String code, String messageKey, String message)
    {
        super(message);
        this.code = code;
        this.messageKey = messageKey;
    }

    public BussinessException(String message)
    {
        super(message);
    }

    public BussinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BussinessException(Throwable cause)
    {
        super(cause);
    }

    public String getMessage()
    {
        return super.getMessage();
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessageKey()
    {
        return messageKey;
    }

    public void setMessageKey(String messageKey)
    {
        this.messageKey = messageKey;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
