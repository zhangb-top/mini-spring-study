package org.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream is = resource.getInputStream();
            try {
                // 解析xml配置文件，加载bean
                doLoadBeanDefinitions(is);
            } finally {
                is.close();
            }
        } catch (IOException ex) {
            throw new BeansException("IOException parsing XML document from " + resource, ex);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    public void doLoadBeanDefinitions(InputStream is) {
        Document document = XmlUtil.readXML(is);
        Element element = document.getDocumentElement();
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                if (BEAN_ELEMENT.equals(childNodes.item(i).getNodeName())) {
                    // 解析bean标签
                    Element bean = (Element) childNodes.item(i);

                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);

                    // 获取bean的class对象
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new BeansException("Cannot find class [" + className + "]");
                    }

                    // 获取beanName，id>name>类名首字母小写
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    if (StrUtil.isEmpty(beanName))
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());

                    // 定义beanDefinition
                    BeanDefinition beanDefinition = new BeanDefinition(clazz);

                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        // 解析property标签
                        if (bean.getChildNodes().item(j) instanceof Element) {
                            if (PROPERTY_ELEMENT.equals(bean.getChildNodes().item(j).getNodeName())) {
                                Element property = (Element) bean.getChildNodes().item(j);
                                String propertyName = property.getAttribute(NAME_ATTRIBUTE);
                                String propertyValue = property.getAttribute(VALUE_ATTRIBUTE);
                                String propertyRef = property.getAttribute(REF_ATTRIBUTE);

                                // 属性name为空
                                if (StrUtil.isEmpty(propertyName))
                                    throw new BeansException("The name attribute cannot be null or " +
                                            "empty");

                                Object value = propertyValue;
                                // ref不为空
                                if (StrUtil.isNotEmpty(propertyRef)) {
                                    value = new BeanReference(propertyRef);
                                }

                                // 为bean添加属性
                                PropertyValue pv = new PropertyValue(propertyName, value);
                                beanDefinition.getPropertyValues().addPropertyValue(pv);
                            }
                        }
                    }
                    if (getRegistry().containsBeanDefinition(beanName))
                        // bean名称重复
                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");

                    // 注册BeanDefinition
                    getRegistry().registryBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }
}
