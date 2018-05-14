package com.otus.hw_12.util.ehcache;

import com.otus.hw_12.entities.dataset.UserDataSet;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;

public class EhCacheUtil
{
    private final static String XML_CONFIG = "/ehcache.xml";
    private final CachingProvider provider;
    private final CacheManager manager;

    public EhCacheUtil()
    {
        provider = Caching.getCachingProvider();
        this.manager = managerInit();
    }

    private CacheManager managerInit()
    {
        try {
            return provider.getCacheManager(
                    getClass().getResource(XML_CONFIG).toURI(),
                    getClass().getClassLoader());
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CacheManager getCacheManager()
    {
        return this.manager;
    }

    public Cache<Long, UserDataSet> getUserDataSetCache()
    {
        return this.manager.getCache("userDataSetCache", Long.class, UserDataSet.class);
    }
}
