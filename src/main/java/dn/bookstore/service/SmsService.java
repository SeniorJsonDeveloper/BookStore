package dn.bookstore.service;

import dn.bookstore.entity.BookEntity;
import dn.bookstore.entity.SmsCodeEntity;

public interface SmsService {

    String sendSms(String phone);

    void saveNewCode(SmsCodeEntity smsCodeEntity);

    Boolean verifyCode(String code);
}
