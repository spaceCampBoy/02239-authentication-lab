package com.labwork;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

public class AccessPolicyEnforcer implements IAccessPolicyEnforcer {

    @Override
    public String user_access(String username, String permission) throws IOException, GeneralSecurityException {
        //Reading Json File
        //Putting users into username and operations into permission

        String userdata = new String(Files.readAllBytes(Paths.get("./src/main/java/com/labwork/acl-policy.json")));
        JSONArray jsonArray = new JSONArray(userdata);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String str = jsonArray.get(i).toString();
            JSONObject object1 = new JSONObject(str);
            username = object1.getString("user");
            permission = (String) object.get("operations");
            System.out.println("user: " + username);
            System.out.println("operations: " + permission);


        }


        return null;
    }
}