package com.otus.hw_13.cacheService;

import com.otus.hw_13.entities.dataset.UserDataSet;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;

public class CacheServiceImpl implements CacheService
{
    private final static String XML_CONFIG = "/ehcache.xml";
    private final static String USER_DATASET_CACHE_NAME = "userDataSetCache";
    private final CachingProvider provider;
    private final CacheManager manager;

    public CacheServiceImpl()
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
        return this.manager.getCache(USER_DATASET_CACHE_NAME, Long.class, UserDataSet.class);
    }
}
