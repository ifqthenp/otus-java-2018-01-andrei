package com.otus.hw_16.back.util;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.*;

public final class MBeansUtil {

    private MBeansUtil() {}

    public static Map<String, Object> getEhCacheStats(final String cacheName) {
        final Map<String, Object> result = new LinkedHashMap<>();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            final String mBeanObjectName = getCacheMBeanObjectName(mbs, cacheName);
            final ObjectName objectName = new ObjectName(Objects.requireNonNull(mBeanObjectName));
            if (mbs.isRegistered(objectName)) {
                MBeanAttributeInfo[] cacheInfo = mbs.getMBeanInfo(objectName).getAttributes();
                for (final MBeanAttributeInfo mBeanAttributeInfo : cacheInfo) {
                    final String mBeanAttributeDescription = mBeanAttributeInfo.getDescription();
                    result.put(mBeanAttributeDescription, mbs.getAttribute(objectName, mBeanAttributeDescription));
                }
            }
        } catch (ReflectionException | InstanceNotFoundException |
                IntrospectionException | AttributeNotFoundException |
                MBeanException | MalformedObjectNameException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getCacheMBeanObjectName(final MBeanServer mbs, final String cacheName) {
        Set<ObjectName> objectNameSet = null;

        try {
            objectNameSet = mbs.queryNames(new ObjectName("javax.cache:type=CacheStatistics,*,Cache=" + cacheName), null);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        Iterator<ObjectName> it = Objects.requireNonNull(objectNameSet).iterator();
        String mBeanObjectName = null;
        final String cacheNameNeeded = "Cache=" + cacheName;
        while (it.hasNext()) {
            String objectNameFromSet = it.next().toString();
            if (objectNameFromSet.endsWith(cacheNameNeeded)) {
                mBeanObjectName = objectNameFromSet;
            }
        }
        return mBeanObjectName;
    }

}
