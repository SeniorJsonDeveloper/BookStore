package dn.bookstore.repository;

import dn.bookstore.entity.SmsCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsCodeRepository extends JpaRepository<SmsCodeEntity, String> {

    SmsCodeEntity findSmsCodeEntitiesByCode(String code);
}