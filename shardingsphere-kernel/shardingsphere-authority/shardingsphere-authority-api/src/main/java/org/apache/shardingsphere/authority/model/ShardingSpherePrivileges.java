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

package org.apache.shardingsphere.authority.model;

import java.util.Collection;

/**
 * ShardingSphere Privileges.
 */
public interface ShardingSpherePrivileges {
    
    /**
     * Set super privilege.
     */
    void setSuperPrivilege();

    /**
     *  Set privileges
     *
     * @param accessSubject
     * @param privileges
     */
    void setPrivileges(AccessSubject accessSubject, Collection<PrivilegeType> privileges);

    /**
     * Has privileges.
     *
     * @param schema
     * @return
     */
    boolean hasPrivileges(String schema);

    /**
     * Has privileges.
     *
     * @param schema
     * @param table
     * @param privileges
     * @return
     */
    boolean hasPrivileges(String schema, String table, Collection<PrivilegeType> privileges);
}
