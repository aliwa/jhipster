package com.aliwa.generator.repository;

import com.aliwa.generator.domain.Userfalse;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the {@link Userfalse} entity.
 */
@Repository
public interface UserRepository extends MongoRepository<Userfalse, String> {
    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";
    Optional<Userfalse> findOneByActivationKey(String activationKey);
    List<Userfalse> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);
    Optional<Userfalse> findOneByResetKey(String resetKey);

    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<Userfalse> findOneByEmailIgnoreCase(String email);

    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<Userfalse> findOneByLogin(String login);

    Page<Userfalse> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);
}
