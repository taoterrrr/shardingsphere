package org.apache.shardingsphere.authority.provider.simple.builder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.authority.builder.PrivilegeBuilder;
import org.apache.shardingsphere.authority.model.PrivilegeType;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.schema.SchemaPrivilegesPermittedAuthorityProviderAlgorithm;
import org.apache.shardingsphere.authority.provider.schema.model.privilege.SchemaPrivilegesPermittedShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.schema.model.subject.SchemaAccessSubject;
import org.apache.shardingsphere.infra.metadata.user.Grantee;
import org.apache.shardingsphere.infra.metadata.user.ShardingSphereUser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wutao
 * @date: 2021/12/21 14:09
 * @description:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AllPrivilegeBuilder extends PrivilegeBuilder {

    public static Collection<Grantee> build(final Collection<ShardingSphereUser> users,
                                                               final Collection<Map<String, Object>> permitted) {
        Collection<Grantee> grantees = new LinkedList<>();
        permitted.stream().forEach(map -> {
            grantees.add(getGrantee(map));
        });
        return grantees;
    }


}
