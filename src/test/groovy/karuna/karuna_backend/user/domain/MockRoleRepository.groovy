package karuna.karuna_backend.user.domain

import karuna.karuna_backend.Constants
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery

import java.util.function.Function

class MockRoleRepository implements RoleRepository, Constants {

    @Override
    Optional<Role> findByName(String name) {
        Optional.of(new Role(ROLE_USER))
    }

    @Override
    void flush() {

    }

    @Override
    void deleteAllInBatch(Iterable<Role> entities) {

    }

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    void deleteAllInBatch() {

    }

    @Override
    Role getOne(Long aLong) {
        return null
    }

    @Override
    Role getById(Long aLong) {
        return null
    }

    @Override
    Role getReferenceById(Long aLong) {
        return null
    }

    @Override
    <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
        return null
    }

    @Override
    <S extends Role> List<S> findAll(Example<S> example) {
        return null
    }

    @Override
    <S extends Role> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends Role> S saveAndFlush(S entity) {
        return null
    }

    @Override
    <S extends Role> List<S> saveAll(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends Role> S save(S entity) {
        return null
    }

    @Override
    Optional<Role> findById(Long aLong) {
        return null
    }

    @Override
    boolean existsById(Long aLong) {
        return false
    }

    @Override
    List<Role> findAll() {
        return null
    }

    @Override
    List<Role> findAllById(Iterable<Long> longs) {
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
    void delete(Role entity) {

    }

    @Override
    void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    void deleteAll(Iterable<? extends Role> entities) {

    }

    @Override
    void deleteAll() {

    }

    @Override
    List<Role> findAll(Sort sort) {
        return null
    }

    @Override
    Page<Role> findAll(Pageable pageable) {
        return null
    }

    @Override
    <S extends Role> Optional<S> findOne(Example<S> example) {
        return null
    }

    @Override
    <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null
    }

    @Override
    <S extends Role> long count(Example<S> example) {
        return 0
    }

    @Override
    <S extends Role> boolean exists(Example<S> example) {
        return false
    }

    @Override
    <S extends Role, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null
    }
}
