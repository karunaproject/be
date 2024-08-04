package karuna.karuna_backend.user.domain

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery

import java.util.function.Function

class MockUserRepository implements UserRepository {

    Map<Long, User> database = [:]

    @Override
    Optional<User> findByUsername(String username) {
        database.values().stream()
            .filter {it.username == username }
            .findFirst()
    }

    @Override
    void flush() {

    }

    @Override
    void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    void deleteAllInBatch() {

    }

    @Override
    User getOne(Long aLong) {
        return null
    }

    @Override
    User getById(Long aLong) {
        return null
    }

    @Override
    User getReferenceById(Long aLong) {
        return null
    }

    @Override
    <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null
    }

    @Override
    <S extends User> List<S> findAll(Example<S> example) {
        return null
    }

    @Override
    <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends User> S saveAndFlush(S entity) {
        return null
    }

    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends User> S save(S entity) {
        Long id = database.size() + 1
        entity.setId(id)
        database.put(id, entity)
        entity
    }

    @Override
    Optional<User> findById(Long aLong) {
        database.values().stream()
                .filter {it.id == aLong }
                .findFirst()
    }

    @Override
    boolean existsById(Long aLong) {
        return false
    }

    @Override
    List<User> findAll() {
        return null
    }

    @Override
    List<User> findAllById(Iterable<Long> longs) {
        return null
    }

    @Override
    long count() {
        return 0
    }

    @Override
    void deleteById(Long aLong) {

    }

    @Override
    void delete(User entity) {

    }

    @Override
    void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    void deleteAll() {
        database = [:]
    }

    @Override
    List<User> findAll(Sort sort) {
        return null
    }

    @Override
    Page<User> findAll(Pageable pageable) {
        return null
    }

    @Override
    <S extends User> Optional<S> findOne(Example<S> example) {
        return null
    }

    @Override
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null
    }

    @Override
    <S extends User> long count(Example<S> example) {
        return 0
    }

    @Override
    <S extends User> boolean exists(Example<S> example) {
        return false
    }

    @Override
    <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null
    }
}
