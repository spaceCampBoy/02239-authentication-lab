package com.labwork;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IAccessPolicyEnforcer {
    String user_access(String username, String permission) throws IOException, GeneralSecurityException ;
}
