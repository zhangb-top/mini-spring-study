package org.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * bean的属性集合
 */
public class PropertyValues {
    List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加bean的属性
     *
     * @param pv bean的属性
     */
    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    /**
     * 获取bean的属性集合
     *
     * @return propertyValueList的array
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 获取根据属性名称单个属性
     *
     * @param name 属性名称
     * @return 单个属性
     */
    public PropertyValue getPropertyValue(String name) {
        for (PropertyValue currentPropertyValue : propertyValueList) {
            if (currentPropertyValue.getName().equals(name)) return currentPropertyValue;
        }
        return null;
    }
}
