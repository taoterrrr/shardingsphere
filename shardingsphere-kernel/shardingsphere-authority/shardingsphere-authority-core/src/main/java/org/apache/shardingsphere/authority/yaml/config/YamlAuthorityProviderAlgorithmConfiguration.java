package org.apache.shardingsphere.authority.yaml.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.yaml.config.pojo.YamlConfiguration;
import org.apache.shardingsphere.infra.yaml.config.pojo.YamlRuleConfiguration;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * @author: wutao
 * @date: 2021/12/24 13:54
 * @description:
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class YamlAuthorityProviderAlgorithmConfiguration implements YamlConfiguration {

    private String type;

    private Collection<Map<String, Object>> permitted;

    private Properties props = new Properties();

}
