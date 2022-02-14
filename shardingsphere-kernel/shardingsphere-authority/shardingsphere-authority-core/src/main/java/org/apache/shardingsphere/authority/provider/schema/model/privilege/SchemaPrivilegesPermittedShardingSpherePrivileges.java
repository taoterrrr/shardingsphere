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

package org.apache.shardingsphere.authority.provider.schema.model.privilege;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.authority.model.AccessSubject;
import org.apache.shardingsphere.authority.model.PrivilegeType;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.schema.model.subject.SchemaAccessSubject;

import lombok.AllArgsConstructor;


public class SchemaPrivilegesPermittedShardingSpherePrivileges implements ShardingSpherePrivileges {

    private final Map<SchemaAccessSubject, Collection<PrivilegeType>> privileges = new ConcurrentHashMap<>();

    @Override
    public void setSuperPrivilege() {

    }

    @Override
    public void setPrivileges(AccessSubject accessSubject, Collection<PrivilegeType> privileges) {
        if (accessSubject instanceof SchemaAccessSubject) {
            this.privileges.put((SchemaAccessSubject) accessSubject, privileges);
        }
    }

    /**
     * Has privileges.
     *
     * @param schema
     * @return
     */
    @Override
    public boolean hasPrivileges(String schema) {
        Collection<PrivilegeType> privilegeTypes = this.privileges.get(new SchemaAccessSubject(schema));
        if (Objects.nonNull(privilegeTypes) && !privilegeTypes.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Has privileges.
     *
     * @param schema
     * @param table
     * @param privileges
     * @return
     */
    @Override
    public boolean hasPrivileges(String schema, String table, Collection<PrivilegeType> privileges) {
        Collection<PrivilegeType> privilegeTypes = this.privileges.get(new SchemaAccessSubject(schema));
        if (Objects.nonNull(privilegeTypes) && !privilegeTypes.isEmpty()) {
            return privilegeTypes.containsAll(privileges);
        }
        return false;
    }
}
