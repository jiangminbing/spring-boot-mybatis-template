/****************************************************************
 *
 *    Copyright (c) 2001-2020 深圳南方电子口岸有限公司
 *    http://www.szeport.com.cn/
 *
 *    Package:     com.szeport.sys.admin.config.datasource
 *
 *    Filename:    DataSourceConfig.java
 *
 *    Description: DataSource数据源配置类
 *
 *    @author:     duanhp
 *
 *    @version:    1.0.0
 *
 *    Create at:   2020年4月1日 下午4:30:00
 *
 *    Revision:
 *
 *    2020年4月1日 下午4:30:00
 *        - first revision
 *
 *****************************************************************/
package com.szeport.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description DataSource数据源配置类
 * @author duanhp
 * @Date 2020年4月1日 下午4:30:00
 * @version 1.0.0
 */
@Configuration
public class DataSourceConfig {

	/**
	 * @Description Click数据源
	 * @return
	 */
	@Bean(name = "dataSourceClick")
	@ConfigurationProperties("spring.datasource.druid.click")
	public DataSource dataSourceClick() {
		return DruidDataSourceBuilder.create().build();
	}
}
