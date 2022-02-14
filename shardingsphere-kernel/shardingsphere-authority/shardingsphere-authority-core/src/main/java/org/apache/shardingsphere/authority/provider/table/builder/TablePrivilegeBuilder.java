package org.apache.shardingsphere.authority.provider.table.builder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.authority.builder.PrivilegeBuilder;
import org.apache.shardingsphere.authority.model.PrivilegeType;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.table.model.privilege.TablePrivilegesPermittedShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.table.model.subject.TableAccessSubject;
import org.apache.shardingsphere.infra.metadata.user.Grantee;
import org.apache.shardingsphere.infra.metadata.user.ShardingSphereUser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wutao
 * @date: 2021/12/30 17:29
 * @description:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TablePrivilegeBuilder extends PrivilegeBuilder {

    /**
     * Build privileges.
     *
     * @param users
     * @param permitted
     * @return
     */
    public static Map<Grantee, ShardingSpherePrivileges> build(final Collection<ShardingSphereUser> users,
                                                               final Collection<Map<String, Object>> permitted) {

        return buildPrivileges(users, permitted);
    }

    private static Map<Grantee, ShardingSpherePrivileges> buildPrivileges(final Collection<ShardingSphereUser> users,
                                                                          Collection<Map<String, Object>> permitted) {
        Map<Grantee, ShardingSpherePrivileges> result = new HashMap<>(users.size(), 1);
        permitted.stream().forEach(map -> {
            Grantee grantee = getGrantee(map);
            String schema = (String) map.get("schema");
            String table = (String) map.get("table");
            ArrayList<String> privileges = (ArrayList<String>) map.get("privileges");
            TableAccessSubject tableAccessSubject = new TableAccessSubject(schema, table);
            Collection<PrivilegeType> privilegeTypes =
                    privileges.stream().map(each -> getPrivilegeType(each)).collect(Collectors.toSet());
            ShardingSpherePrivileges shardingSpherePrivileges =
                    Optional.ofNullable(result.get(grantee)).orElse(new TablePrivilegesPermittedShardingSpherePrivileges());
            shardingSpherePrivileges.setPrivileges(tableAccessSubject, privilegeTypes);
            result.put(grantee, shardingSpherePrivileges);
        });
        return result;
    }


}
