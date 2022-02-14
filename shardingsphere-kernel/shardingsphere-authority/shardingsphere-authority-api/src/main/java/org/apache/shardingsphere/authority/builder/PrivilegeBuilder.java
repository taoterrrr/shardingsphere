package org.apache.shardingsphere.authority.builder;

import org.apache.shardingsphere.authority.model.AccessSubject;
import org.apache.shardingsphere.authority.model.PrivilegeType;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.infra.metadata.user.Grantee;
import org.apache.shardingsphere.infra.metadata.user.ShardingSphereUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: wutao
 * @date: 2022/1/6 11:22
 * @description:
 */
public abstract class PrivilegeBuilder {

    public static PrivilegeType getPrivilegeType(final String privilege) {
        switch (privilege) {
            case "Select":
                return PrivilegeType.SELECT;
            case "Insert":
                return PrivilegeType.INSERT;
            case "Update":
                return PrivilegeType.UPDATE;
            case "Delete":
                return PrivilegeType.DELETE;
            case "Create":
                return PrivilegeType.CREATE;
            case "Alter":
                return PrivilegeType.ALTER;
            case "Drop":
                return PrivilegeType.DROP;
            case "Grant":
                return PrivilegeType.GRANT;
            case "Index":
                return PrivilegeType.INDEX;
            case "References":
                return PrivilegeType.REFERENCES;
            case "Create View":
                return PrivilegeType.CREATE_VIEW;
            case "Show view":
                return PrivilegeType.SHOW_VIEW;
            case "Trigger":
                return PrivilegeType.TRIGGER;
            default:
                throw new UnsupportedOperationException(privilege);
        }
    }

    public static Grantee getGrantee(Map<String, Object> map) {
        String granteeStr = (String) map.get("grantee");
        if (Objects.isNull(granteeStr)) {
            throw new UnsupportedOperationException("there is no grantee");
        }
        String username = granteeStr.substring(0, granteeStr.indexOf("@"));
        String hostname = granteeStr.substring(granteeStr.indexOf("@") + 1);
        Grantee grantee = new Grantee(username, hostname);
        return grantee;
    }

}
