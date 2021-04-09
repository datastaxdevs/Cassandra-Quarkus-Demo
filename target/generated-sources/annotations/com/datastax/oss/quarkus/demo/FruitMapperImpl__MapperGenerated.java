package com.datastax.oss.quarkus.demo;

import com.datastax.oss.driver.internal.core.util.concurrent.LazyReference;
import com.datastax.oss.driver.internal.mapper.DefaultMapperContext;
import java.lang.Override;
import java.lang.SuppressWarnings;

/**
 * Do not instantiate this class directly, use {@link FruitMapperBuilder} instead.
 *
 * <p>Generated by the DataStax driver mapper, do not edit directly.
 */
@SuppressWarnings("all")
public class FruitMapperImpl__MapperGenerated implements FruitMapper {
  private final DefaultMapperContext context;

  private final LazyReference<FruitDao> fruitDaoCache;

  private final LazyReference<ReactiveFruitDao> reactiveFruitDaoCache;

  public FruitMapperImpl__MapperGenerated(DefaultMapperContext context) {
    this.context = context;
    this.fruitDaoCache = new LazyReference<>(() -> FruitDaoImpl__MapperGenerated.init(context));
    this.reactiveFruitDaoCache = new LazyReference<>(() -> ReactiveFruitDaoImpl__MapperGenerated.init(context));
  }

  @Override
  public FruitDao fruitDao() {
    return fruitDaoCache.get();
  }

  @Override
  public ReactiveFruitDao reactiveFruitDao() {
    return reactiveFruitDaoCache.get();
  }
}