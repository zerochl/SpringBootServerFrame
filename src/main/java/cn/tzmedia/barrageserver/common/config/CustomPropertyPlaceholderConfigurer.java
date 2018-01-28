package cn.tzmedia.barrageserver.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by zero大神 on 2017/11/30.
 */
@Component
public class CustomPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {
    private Properties props;

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        super.processProperties(beanFactory, props);
        this.props = props;
    }

    public Object getProperty(String key) {
        return props.get(key);
    }
}
