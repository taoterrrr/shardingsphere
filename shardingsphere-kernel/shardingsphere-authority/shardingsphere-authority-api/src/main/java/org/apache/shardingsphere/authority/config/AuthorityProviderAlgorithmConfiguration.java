package org.apache.shardingsphere.authority.config;

import lombok.Getter;
import org.apache.shardingsphere.infra.config.TypedSPIConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * @author: wutao
 * @date: 2021/12/24 15:16
 * @description:
 */
@Getter
public class AuthorityProviderAlgorithmConfiguration extends TypedSPIConfiguration {

    private final Collection<Map<String, Object>> permitted;

    public AuthorityProviderAlgorithmConfiguration(String type, Collection<Map<String, Object>> permitted,
                                                   Properties props) {
        super(type, props);
        this.permitted = permitted;
    }
}
