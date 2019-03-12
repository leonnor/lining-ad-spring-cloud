package com.lining.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * className WebConfiguration
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/12 20:16
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        //将Java对象转换为json对象,所有引用了ad-common的对象都会被转换器转换为json对象
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
