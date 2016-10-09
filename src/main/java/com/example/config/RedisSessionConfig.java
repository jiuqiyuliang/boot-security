package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/** 
 * @author  lilufeng 
 * @date 创建时间：2016年5月9日 下午3:46:20 
 */

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

}
