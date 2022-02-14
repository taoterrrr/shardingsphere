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

package org.apache.shardingsphere.authority.provider.natived.model.privilege;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.shardingsphere.authority.model.PrivilegeType;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.authority.provider.natived.model.privilege.admin.AdministrativePrivileges;
import org.apache.shardingsphere.authority.provider.natived.model.privilege.database.DatabasePrivileges;
import org.apache.shardingsphere.authority.model.AccessSubject;
import org.apache.shardingsphere.authority.provider.schema.model.subject.SchemaAccessSubject;
import org.apache.shardingsphere.authority.provider.table.model.subject.TableAccessSubject;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Native Privileges.
 */
@Getter
@EqualsAndHashCode
public final class NativePrivileges implements ShardingSpherePrivileges {
    
    private final AdministrativePrivileges administrativePrivileges = new AdministrativePrivileges();
    
    private final DatabasePrivileges databasePrivileges = new DatabasePrivileges();
    
    @Override
    public void setSuperPrivilege() {
        administrativePrivileges.getPrivileges().add(PrivilegeType.SUPER);
    }

    /**
     * Set privileges
     *
     * @param accessSubject
     * @param privileges
     */
    @Override
    public void setPrivileges(AccessSubject accessSubject, Collection<PrivilegeType> privileges) {

    }

    /**
     * Has privileges.
     *
     * @param schema
     * @return
     */
    @Override
    public boolean hasPrivileges(String schema) {
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
        return false;
    }

//    @Override
//    public boolean hasPrivileges(AccessSubject accessSubject) {
//        return false;
//    }
//
//    @Override
//    public boolean hasPrivileges(final AccessSubject accessSubject, final Collection<PrivilegeType> privileges) {
//        if (accessSubject instanceof SchemaAccessSubject) {
//            return hasPrivileges(((SchemaAccessSubject) accessSubject).getSchema(), filterPrivileges(privileges));
//        }
//        if (accessSubject instanceof TableAccessSubject) {
//            return hasPrivileges(((TableAccessSubject) accessSubject).getSchema(), ((TableAccessSubject) accessSubject).getTable(), filterPrivileges(privileges));
//        }
//        throw new UnsupportedOperationException(accessSubject.getClass().getCanonicalName());
//    }
//
//    private boolean hasPrivileges(final String schema, final Collection<PrivilegeType> privileges) {
//        return administrativePrivileges.hasPrivileges(privileges) || databasePrivileges.hasPrivileges(schema, privileges);
//    }
//
//    private boolean hasPrivileges(final String schema, final String table, final Collection<PrivilegeType> privileges) {
//        return administrativePrivileges.hasPrivileges(privileges) || databasePrivileges.hasPrivileges(schema, table, privileges);
//    }
//
//    private Collection<PrivilegeType> filterPrivileges(final Collection<PrivilegeType> privileges) {
//        return privileges.stream()
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//    }
}
