package dn.bookstore.service.impl;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import dn.bookstore.entity.BookEntity;
import dn.bookstore.entity.SmsCodeEntity;
import dn.bookstore.repository.SmsCodeRepository;
import dn.bookstore.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsCodeRepository smsCodeRepository;

    @Value("${integration.twilio.phone}")
    private String TWILIO_PHONE;

    @Value("${integration.twilio.sid}")
    private String TWILIO_SID;

    @Value("${integration.twilio.token}")
    private String TWILIO_TOKEN;

    private String generateCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 6){
            sb.append(random.nextInt(10));
        }
        sb.insert(3,"");
        return sb.toString();
    }



    @Override
    public String sendSms(String phone) {
        Twilio.init(TWILIO_SID,TWILIO_TOKEN);
        String formattedContact = phone.replaceAll("[( )-] ", "");
        String generatedCode = generateCode();
        Message.creator(
                new PhoneNumber(formattedContact),
                new PhoneNumber(TWILIO_PHONE),
                "Ваш секретный код: "+generatedCode
        ).create();
        return generatedCode;
    }

    @Override
    public void saveNewCode(SmsCodeEntity smsCodeEntity) {
        if (smsCodeRepository.findSmsCodeEntitiesByCode(smsCodeEntity.getCode()) == null) {
            smsCodeRepository.save(smsCodeEntity);
        }

    }

    @Override
    public Boolean verifyCode(String code) {
        SmsCodeEntity smsCodeEntity = smsCodeRepository.findSmsCodeEntitiesByCode(code);
        return smsCodeEntity != null&&smsCodeEntity.isExpired();
    }
}
