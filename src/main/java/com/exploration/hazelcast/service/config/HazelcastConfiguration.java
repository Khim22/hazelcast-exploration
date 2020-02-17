package com.exploration.hazelcast.service.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {
    @Bean
    public Config hazelCastConfig(){
        Config config = new Config()
                .setInstanceName("employee-hazelcast")
                .setProperty("hazelcast.rest.enabled", "true")
                .addMapConfig(
                        new MapConfig()
                                .setName("configuration")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(-1));

        config.getNetworkConfig().setPort(5970).setPortAutoIncrement(true); //autoincr=true so that new instance will use new port
        config.getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true)
                .setMulticastPort(5980).setMulticastTimeToLive(30).setMulticastTimeoutSeconds(5);

        // don't wait for a number of member nodes to be up before starting this member
        config.getProperties().setProperty("hazelcast.initial.min.cluster.size", "0");



        return config;
    }


}
