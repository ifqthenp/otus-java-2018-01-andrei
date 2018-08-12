package com.otus.hw_15.cacheService;

import com.otus.hw_15.entities.dataset.UserDataSet;

import javax.cache.Cache;
import javax.cache.CacheManager;

public interface CacheService
{
    CacheManager getCacheManager();

    Cache<Long, UserDataSet> getUserDataSetCache();
}
