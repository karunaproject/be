package karuna.karuna_backend.receiver.domain

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery

import java.util.function.Function
import java.util.stream.Collectors

class MockReceiverRepository implements ReceiverRepository{

    private Map<Long, Receiver> database = [:]

    @Override
    Receiver findByEmailIgnoreCase(String email) {
        database.values().stream()
            .filter { it.email.equalsIgnoreCase(email)}
            .findFirst()
            .orElseThrow { new RuntimeException("Can not found entity!") }
    }

    @Override
    void deleteByEmailIgnoreCase(String email) {
        Optional<Receiver> receiverOptional = database.values().stream()
            .filter { it.email.equalsIgnoreCase(email) }
            .findFirst()
        if (receiverOptional.isPresent()) {
            database.remove(receiverOptional.get())
        }
    }

    @Override
    void flush() {

    }

    @Override
    void deleteAllInBatch(Iterable<Receiver> entities) {

    }

    @Override
    void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    void deleteAllInBatch() {

    }

    @Override
    Receiver getOne(Integer integer) {
        return null
    }

    @Override
    Receiver getById(Integer integer) {
        return null
    }

    @Override
    Receiver getReferenceById(Integer integer) {
        return null
    }

    @Override
    <S extends Receiver> List<S> findAll(Example<S> example, Sort sort) {
        return null
    }

    @Override
    <S extends Receiver> List<S> findAll(Example<S> example) {
        return null
    }

    @Override
    <S extends Receiver> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends Receiver> S saveAndFlush(S entity) {
        return null
    }

    @Override
    <S extends Receiver> List<S> saveAll(Iterable<S> entities) {
        return null
    }

    @Override
    <S extends Receiver> S save(S entity) {
        Integer id = database.size() + 1
        entity.setId(id)
        database.put(id, entity)
        entity
    }

    @Override
    Optional<Receiver> findById(Integer integer) {
        return null
    }

    @Override
    boolean existsById(Integer integer) {
        return false
    }

    @Override
    List<Receiver> findAll() {
        return null
    }

    @Override
    List<Receiver> findAllById(Iterable<Integer> integers) {
        return null
    }

    @Override
    long count() {
        return 0
    }

    @Override
    void deleteById(Integer integer) {

    }

    @Override
    void delete(Receiver entity) {

    }

    @Override
    void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    void deleteAll(Iterable<? extends Receiver> entities) {

    }

    @Override
    void deleteAll() {
        database = [:]
    }

    @Override
    List<Receiver> findAll(Sort sort) {
        database.values().stream().collect(Collectors.toList())
    }

    @Override
    Page<Receiver> findAll(Pageable pageable) {
        return null
    }

    @Override
    <S extends Receiver> Optional<S> findOne(Example<S> example) {
        return null
    }

    @Override
    <S extends Receiver> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null
    }

    @Override
    <S extends Receiver> long count(Example<S> example) {
        return 0
    }

    @Override
    <S extends Receiver> boolean exists(Example<S> example) {
        return false
    }

    @Override
    <S extends Receiver, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null
    }
}
