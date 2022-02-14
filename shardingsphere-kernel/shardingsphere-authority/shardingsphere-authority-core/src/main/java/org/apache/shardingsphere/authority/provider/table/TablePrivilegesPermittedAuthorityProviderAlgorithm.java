package org.apache.shardingsphere.authority.provider.table;

import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.schema.builder.SchemaPrivilegeBuilder;
import org.apache.shardingsphere.authority.provider.table.builder.TablePrivilegeBuilder;
import org.apache.shardingsphere.authority.spi.AuthorityProvideAlgorithm;
import org.apache.shardingsphere.infra.metadata.ShardingSphereMetaData;
import org.apache.shardingsphere.infra.metadata.user.Grantee;
import org.apache.shardingsphere.infra.metadata.user.ShardingSphereUser;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TABLE privileges permitted authority provider algorithm.
 */
public class TablePrivilegesPermittedAuthorityProviderAlgorithm implements AuthorityProvideAlgorithm {

    private final Map<Grantee, ShardingSpherePrivileges> userPrivilegeMap = new ConcurrentHashMap<>();

    @Override
    public void init(final Collection<Map<String, Object>> permitted, final Map<String,
                     ShardingSphereMetaData> mataDataMap, final Collection<ShardingSphereUser> users) {
        this.userPrivilegeMap.putAll(TablePrivilegeBuilder.build(users, permitted));
    }

    @Override
    public void refresh(Map<String, ShardingSphereMetaData> mataDataMap, Collection<ShardingSphereUser> users) {

    }

    @Override
    public Optional<ShardingSpherePrivileges> findPrivileges(Grantee grantee) {
        return userPrivilegeMap.keySet().stream().filter(each -> each.equals(grantee)).findFirst().map(userPrivilegeMap::get);
    }

    @Override
    public String getType() {
        return "TABLE_PRIVILEGES_PERMITTED";
    }

}
