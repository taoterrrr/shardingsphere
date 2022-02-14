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

package org.apache.shardingsphere.authority.yaml.swapper;

import org.apache.shardingsphere.authority.config.AuthorityProviderAlgorithmConfiguration;
import org.apache.shardingsphere.authority.config.AuthorityRuleConfiguration;
import org.apache.shardingsphere.authority.constant.AuthorityOrder;
import org.apache.shardingsphere.authority.yaml.config.YamlAuthorityProviderAlgorithmConfiguration;
import org.apache.shardingsphere.authority.yaml.config.YamlAuthorityRuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.infra.metadata.user.ShardingSphereUser;
import org.apache.shardingsphere.infra.metadata.user.yaml.config.YamlUsersConfigurationConverter;
import org.apache.shardingsphere.infra.yaml.config.swapper.YamlRuleConfigurationSwapper;
import org.apache.shardingsphere.infra.yaml.config.swapper.algorithm.ShardingSphereAlgorithmConfigurationYamlSwapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Authority rule configuration YAML swapper.
 */
public final class AuthorityRuleConfigurationYamlSwapper implements YamlRuleConfigurationSwapper<YamlAuthorityRuleConfiguration, AuthorityRuleConfiguration> {

    @Override
    public YamlAuthorityRuleConfiguration swapToYamlConfiguration(final AuthorityRuleConfiguration data) {
        YamlAuthorityRuleConfiguration result = new YamlAuthorityRuleConfiguration();
        data.getProviders().forEach(provider -> {
            result.getProviders().add(new YamlAuthorityProviderAlgorithmConfiguration(provider.getType(),
                    provider.getPermitted(), provider.getProps()));
        });
        result.setUsers(YamlUsersConfigurationConverter.convertYamlUserConfigurations(data.getUsers()));
        return result;
    }
    
    @Override
    public AuthorityRuleConfiguration swapToObject(final YamlAuthorityRuleConfiguration yamlConfig) {
        Collection<ShardingSphereUser> users = YamlUsersConfigurationConverter.convertShardingSphereUser(yamlConfig.getUsers());
        Collection<AuthorityProviderAlgorithmConfiguration> providers = new ArrayList<>();
        yamlConfig.getProviders().forEach(provider -> {
            providers.add(new AuthorityProviderAlgorithmConfiguration(provider.getType(), provider.getPermitted(),
                    provider.getProps()));
        });
        return new AuthorityRuleConfiguration(users, providers);
    }
    
    @Override
    public Class<AuthorityRuleConfiguration> getTypeClass() {
        return AuthorityRuleConfiguration.class;
    }
    
    @Override
    public String getRuleTagName() {
        return "AUTHORITY";
    }
    
    @Override
    public int getOrder() {
        return AuthorityOrder.ORDER;
    }
}
