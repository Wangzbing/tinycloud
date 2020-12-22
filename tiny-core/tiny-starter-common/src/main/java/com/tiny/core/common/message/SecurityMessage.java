package com.tiny.core.common.message;


import com.tiny.core.common.util.StringUtils;

/**
 * @author Amin
 */
public class SecurityMessage extends ApplicationMessage {

    public static final int PASSWORD_ERROR_CODE = 10301;
    public static final String PASSWORD_ERROR_10301_MESSAGE = " Password error ";

    public static final int ORIGINAL_PASSWORD_ERROR_CODE = 10302;
    public static final String ORIGINAL_PASSWORD_ERROR_10302_MESSAGE = " Original password error ";

    public static final int ILLEGAL_PASSWORD_LENGTH_CODE = 10303;
    public static final String ILLEGAL_PASSWORD_LENGTH_10303_MESSAGE = " Illegal password length";

    public static final int INCORRECT_USERNAME_OR_PASSWORD_CODE = 10304;
    public static final String INCORRECT_USERNAME_OR_PASSWORD_10304_MESSAGE = " Incorrect username or password ";

    public static final int ILLEGAL_PASSWORD_RULES_CODE = 10305;
    public static final String ILLEGAL_PASSWORD_RULES_10305_MESSAGE = " Illegal password rules";

    public static final int ILLEGAL_SIGNATURE_CODE = 10306;
    public static final String ILLEGAL_SIGNATURE_10306_MESSAGE = " Illegal signature ";


    public static final int ACCOUNT_ERROR  = 10307;
    public static final int ACCOUNT_KICKED_OUT  = 10308;

    public static final String ACCOUNT_10307_10308_MESSAGE = " Account error ";


    public static SecurityMessage accountError(String message) {
        return new SecurityMessage(ACCOUNT_ERROR, StringUtils.isEmpty(message) ? ACCOUNT_10307_10308_MESSAGE : message , MessageType.SECURITY);
    }

    public static SecurityMessage accountKickedOut (String message) {
        return new SecurityMessage(ACCOUNT_KICKED_OUT, StringUtils.isEmpty(message) ? ACCOUNT_10307_10308_MESSAGE : message , MessageType.SECURITY);
    }


    public static SecurityMessage passwordError(String message) {
        return new SecurityMessage(PASSWORD_ERROR_CODE, StringUtils.isEmpty(message) ? PASSWORD_ERROR_10301_MESSAGE : message , MessageType.SECURITY);
    }

    public static SecurityMessage originalPasswordError(String message) {
        return new SecurityMessage(ORIGINAL_PASSWORD_ERROR_CODE, StringUtils.isEmpty(message) ? ORIGINAL_PASSWORD_ERROR_10302_MESSAGE : message , MessageType.SECURITY);
    }

    public static SecurityMessage illegalPasswordLength(String message) {
        return new SecurityMessage(ILLEGAL_PASSWORD_LENGTH_CODE, StringUtils.isEmpty(message) ? ILLEGAL_PASSWORD_LENGTH_10303_MESSAGE : message , MessageType.SECURITY);
    }

    public static SecurityMessage incorrectUsernameOrPassword (String message) {
        return new SecurityMessage(INCORRECT_USERNAME_OR_PASSWORD_CODE, StringUtils.isEmpty(message) ? INCORRECT_USERNAME_OR_PASSWORD_10304_MESSAGE : message , MessageType.SECURITY);
    }

    public static SecurityMessage illegalPasswordRules(String message) {
        return new SecurityMessage(ILLEGAL_PASSWORD_RULES_CODE, StringUtils.isEmpty(message) ? ILLEGAL_PASSWORD_RULES_10305_MESSAGE : message , MessageType.SECURITY);
    }


    public static SecurityMessage illegalSignature(String message) {
        return new SecurityMessage(ILLEGAL_SIGNATURE_CODE, StringUtils.isEmpty(message) ? ILLEGAL_SIGNATURE_10306_MESSAGE : message , MessageType.SECURITY);
    }

    private SecurityMessage(int code, String message, MessageType type) {
        super(code, message, type);
    }
}
