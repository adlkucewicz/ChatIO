package io.jabber.server.data.dao.common;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.jabber.server.data.exception.DeletionException;
import io.jabber.server.data.exception.InsertionException;
import io.jabber.server.data.exception.ReadException;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityDaoTest {

  private final EntityManager entityManager = mock(EntityManager.class);
  private final EntityDao<TestEntity> dao = new EntityDao<>(TestEntity.class);

  @BeforeEach
  public void configureEntityManager() {
    dao.setEntityManager(entityManager);
  }

  @Test
  public void constructor_EntityTypeIsNull_ExceptionThrown() {
    assertThrows(
        NullPointerException.class,
        () -> new EntityDao<>(null)
    );
  }

  @Test
  public void constructor_EntityTypePresent_TypeSet() {
    Class<TestEntity> entityType = TestEntity.class;
    assertEquals(entityType, new EntityDao<>(entityType).getType());
  }

  @Test
  public void findById_EntityPresent_EntityReturned() {
    long id = 123L;
    TestEntity entity = mock(TestEntity.class);
    when(entityManager.find(TestEntity.class, id)).thenReturn(entity);
    Optional<TestEntity> result = dao.findById(id);
    assertTrue(result.isPresent());
    assertEquals(entity, result.get());
  }

  @Test
  public void findById_EntityNotPresent_EntityNotReturned() {
    assertTrue(dao.findById(123L).isEmpty());
  }

  @Test
  public void findById_ExceptionThrownFromManager_ReadExceptionThrown() {
    when(entityManager.find(eq(TestEntity.class), anyLong())).thenThrow(new RuntimeException());
    assertThrows(
        ReadException.class,
        () -> dao.findById(123L)
    );
  }

  @Test
  public void save_CompletesSuccessfully_NoExceptionsThrown() {
    TestEntity entity = mock(TestEntity.class);
    dao.save(entity);
    verify(entityManager).persist(entity);
  }

  @Test
  public void save_ExceptionThrownFromManager_InsertionExceptionThrown() {
    doThrow(new RuntimeException()).when(entityManager).persist(any(Object.class));
    assertThrows(
        InsertionException.class,
        () -> dao.save(mock(TestEntity.class))
    );
  }

  @Test
  public void delete_CompletesSuccessfully_NoExceptionsThrown() {
    TestEntity entity = mock(TestEntity.class);
    dao.delete(entity);
    verify(entityManager).remove(entity);
  }

  @Test
  public void delete_ExceptionThrownFromManager_DeletionExceptionThrown() {
    doThrow(new RuntimeException()).when(entityManager).remove(any(Object.class));
    assertThrows(
        DeletionException.class,
        () -> dao.delete(mock(TestEntity.class))
    );
  }

  private static class TestEntity {}

}