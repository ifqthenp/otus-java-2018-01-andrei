package com.otus.hw_16.back.cacheService;

import com.otus.hw_16.back.entities.UserDataSet;

import javax.cache.Cache;
import javax.cache.CacheManager;

public interface CacheService {

    CacheManager getCacheManager();

    Cache<Long, UserDataSet> getUserDataSetCache();

}
