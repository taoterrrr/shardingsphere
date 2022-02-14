package org.apache.shardingsphere.authority.provider.table.model.privilege;

import org.apache.shardingsphere.authority.model.AccessSubject;
import org.apache.shardingsphere.authority.model.PrivilegeType;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.schema.model.subject.SchemaAccessSubject;
import org.apache.shardingsphere.authority.provider.table.model.subject.TableAccessSubject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: wutao
 * @date: 2021/12/20 17:25
 * @description:
 */
public class TablePrivilegesPermittedShardingSpherePrivileges implements ShardingSpherePrivileges {

    private final Map<TableAccessSubject, Collection<PrivilegeType>> privileges = new ConcurrentHashMap<>();

    @Override
    public void setSuperPrivilege() {

    }

    @Override
    public void setPrivileges(AccessSubject accessSubject, Collection<PrivilegeType> privileges) {
        if (accessSubject instanceof TableAccessSubject) {
            this.privileges.put((TableAccessSubject) accessSubject, privileges);
        }
    }

    @Override
    public boolean hasPrivileges(String schema) {
        List<TableAccessSubject> accessSubjects =
                this.privileges.keySet().stream().filter(each -> each.getSchema().equalsIgnoreCase(schema)).collect(Collectors.toList());
        if (!accessSubjects.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrivileges(String schema, String table, Collection<PrivilegeType> privileges) {
        Collection<PrivilegeType> privilegeTypes = this.privileges.get(new TableAccessSubject(schema, table));
        if (Objects.nonNull(privilegeTypes) && !privilegeTypes.isEmpty()) {
            return privilegeTypes.containsAll(privileges);
        }
        return false;
    }
}
