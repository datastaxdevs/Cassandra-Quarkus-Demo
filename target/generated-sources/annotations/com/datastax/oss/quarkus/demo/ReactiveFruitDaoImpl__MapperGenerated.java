package com.datastax.oss.quarkus.demo;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatementBuilder;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.reflect.GenericType;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.entity.saving.NullSavingStrategy;
import com.datastax.oss.driver.api.mapper.result.MapperResultProducer;
import com.datastax.oss.driver.internal.core.util.concurrent.BlockingOperation;
import com.datastax.oss.driver.internal.core.util.concurrent.CompletableFutures;
import com.datastax.oss.driver.internal.mapper.DaoBase;
import com.datastax.oss.driver.internal.querybuilder.update.DefaultUpdate;
import com.datastax.oss.driver.shaded.guava.common.base.Throwables;
import com.datastax.oss.quarkus.runtime.api.reactive.mapper.MutinyMappedReactiveResultSet;
import io.smallrye.mutiny.Uni;
import java.lang.Boolean;
import java.lang.Exception;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.lang.Void;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generated by the DataStax driver mapper, do not edit directly.
 */
@SuppressWarnings("all")
public class ReactiveFruitDaoImpl__MapperGenerated extends DaoBase implements ReactiveFruitDao {
  private static final GenericType<Uni<Void>> GENERIC_TYPE = new GenericType<Uni<Void>>(){};

  private static final GenericType<MutinyMappedReactiveResultSet<Fruit>> GENERIC_TYPE1 = new GenericType<MutinyMappedReactiveResultSet<Fruit>>(){};

  private static final Logger LOG = LoggerFactory.getLogger(ReactiveFruitDaoImpl__MapperGenerated.class);

  private final FruitHelper__MapperGenerated fruitHelper;

  private final PreparedStatement updateStatement;

  private final PreparedStatement findAllStatement;

  private ReactiveFruitDaoImpl__MapperGenerated(MapperContext context,
      FruitHelper__MapperGenerated fruitHelper, PreparedStatement updateStatement,
      PreparedStatement findAllStatement) {
    super(context);
    this.fruitHelper = fruitHelper;
    this.updateStatement = updateStatement;
    this.findAllStatement = findAllStatement;
  }

  @Override
  public Uni<Void> update(Fruit fruit) {
    MapperResultProducer producer = context.getResultProducer(GENERIC_TYPE);
    try {
      BoundStatementBuilder boundStatementBuilder = updateStatement.boundStatementBuilder();
      fruitHelper.set(fruit, boundStatementBuilder, NullSavingStrategy.DO_NOT_SET);

      BoundStatement boundStatement = boundStatementBuilder.build();
      @SuppressWarnings("unchecked") Uni<Void> result =
          (Uni<Void>) producer.execute(boundStatement, context, fruitHelper);
      return result;
    } catch (Exception e) {
      try {
        @SuppressWarnings("unchecked") Uni<Void> result =
            (Uni<Void>) producer.wrapError(e);
        return result;
      } catch (Exception e2) {
        Throwables.throwIfUnchecked(e2);
        throw new RuntimeException(e2);
      }
    }
  }

  @Override
  public MutinyMappedReactiveResultSet<Fruit> findAll() {
    MapperResultProducer producer = context.getResultProducer(GENERIC_TYPE1);
    try {
      BoundStatementBuilder boundStatementBuilder = findAllStatement.boundStatementBuilder();

      BoundStatement boundStatement = boundStatementBuilder.build();
      @SuppressWarnings("unchecked") MutinyMappedReactiveResultSet<Fruit> result =
          (MutinyMappedReactiveResultSet<Fruit>) producer.execute(boundStatement, context, fruitHelper);
      return result;
    } catch (Exception e) {
      try {
        @SuppressWarnings("unchecked") MutinyMappedReactiveResultSet<Fruit> result =
            (MutinyMappedReactiveResultSet<Fruit>) producer.wrapError(e);
        return result;
      } catch (Exception e2) {
        Throwables.throwIfUnchecked(e2);
        throw new RuntimeException(e2);
      }
    }
  }

  public static CompletableFuture<ReactiveFruitDao> initAsync(MapperContext context) {
    LOG.debug("[{}] Initializing new instance for keyspace = {} and table = {}",
        context.getSession().getName(),
        context.getKeyspaceId(),
        context.getTableId());
    throwIfProtocolVersionV3(context);
    try {
      // Initialize all entity helpers
      FruitHelper__MapperGenerated fruitHelper = new FruitHelper__MapperGenerated(context);
      if ((Boolean)context.getCustomState().get("datastax.mapper.schemaValidationEnabled")) {
        fruitHelper.validateEntityFields();
      }
      List<CompletionStage<PreparedStatement>> prepareStages = new ArrayList<>();
      // Prepare the statement for `update(com.datastax.oss.quarkus.demo.Fruit)`:
      SimpleStatement updateStatement_simple = SimpleStatement.newInstance(((DefaultUpdate)fruitHelper.updateByPrimaryKey()).asCql());
      LOG.debug("[{}] Preparing query `{}` for method update(com.datastax.oss.quarkus.demo.Fruit)",
          context.getSession().getName(),
          updateStatement_simple.getQuery());
      CompletionStage<PreparedStatement> updateStatement = prepare(updateStatement_simple, context);
      prepareStages.add(updateStatement);
      // Prepare the statement for `findAll()`:
      SimpleStatement findAllStatement_simple = fruitHelper.selectByPrimaryKeyParts(0).build();
      LOG.debug("[{}] Preparing query `{}` for method findAll()",
          context.getSession().getName(),
          findAllStatement_simple.getQuery());
      CompletionStage<PreparedStatement> findAllStatement = prepare(findAllStatement_simple, context);
      prepareStages.add(findAllStatement);
      // Initialize all method invokers
      // Build the DAO when all statements are prepared
      return CompletableFutures.allSuccessful(prepareStages)
          .thenApply(v -> (ReactiveFruitDao) new ReactiveFruitDaoImpl__MapperGenerated(context,
              fruitHelper,
              CompletableFutures.getCompleted(updateStatement),
              CompletableFutures.getCompleted(findAllStatement)))
          .toCompletableFuture();
    } catch (Throwable t) {
      return CompletableFutures.failedFuture(t);
    }
  }

  public static ReactiveFruitDao init(MapperContext context) {
    BlockingOperation.checkNotDriverThread();
    return CompletableFutures.getUninterruptibly(initAsync(context));
  }
}
