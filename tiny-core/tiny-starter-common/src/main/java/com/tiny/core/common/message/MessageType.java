package com.tiny.core.common.message;

/**
 * @author Amin
 */

public enum MessageType {
    /**系统*/
    SYSTEM("100xx"),
    /**服务*/
    API("101xx"),
    /**服务*/
    SERVICE("102xx"),
    /**授权*/
    SECURITY("103xx");

    private final String codedDescription;

    MessageType(String codedDescription) {
        this.codedDescription = codedDescription;
    }

    public String getCodedDescription() {
        return codedDescription;
    }
}
