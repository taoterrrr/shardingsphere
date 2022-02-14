/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.authority.provider.schema.builder;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.authority.builder.PrivilegeBuilder;
import org.apache.shardingsphere.authority.model.PrivilegeType;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.schema.model.privilege.SchemaPrivilegesPermittedShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.schema.model.subject.SchemaAccessSubject;
import org.apache.shardingsphere.infra.metadata.user.Grantee;
import org.apache.shardingsphere.infra.metadata.user.ShardingSphereUser;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SchemaPrivilegeBuilder extends PrivilegeBuilder {

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
            ArrayList<String> privileges = (ArrayList<String>) map.get("privileges");
            SchemaAccessSubject schemaAccessSubject = new SchemaAccessSubject(schema);
            Collection<PrivilegeType> privilegeTypes =
                    privileges.stream().map(each -> getPrivilegeType(each)).collect(Collectors.toSet());
            ShardingSpherePrivileges shardingSpherePrivileges =
                    Optional.ofNullable(result.get(grantee)).orElse(new SchemaPrivilegesPermittedShardingSpherePrivileges());
            shardingSpherePrivileges.setPrivileges(schemaAccessSubject, privilegeTypes);
            result.put(grantee, shardingSpherePrivileges);
        });
        return result;
    }



    

}
