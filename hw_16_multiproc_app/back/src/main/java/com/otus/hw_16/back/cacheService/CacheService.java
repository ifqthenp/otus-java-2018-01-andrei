package com.otus.hw_16.back.cacheService;

import com.otus.hw_16.back.entities.UserDataSet;

import javax.cache.Cache;
import javax.cache.CacheManager;

public interface CacheService {

    String USER_DATASET_CACHE_NAME = "userDataSetCache";

    CacheManager getCacheManager();

    Cache<Long, UserDataSet> getUserDataSetCache();

}
