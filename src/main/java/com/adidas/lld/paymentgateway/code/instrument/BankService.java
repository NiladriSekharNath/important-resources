package com.adidas.lld.paymentgateway.code.instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankService extends InstrumentService{

  @Override
  public InstrumentDTO addInstrument(InstrumentDTO instrumentDTO) {
    BankInstrument bankInstrument = new BankInstrument();

    bankInstrument.instrumentId = UUID.randomUUID().toString();
    bankInstrument.setBankAccountNumber(instrumentDTO.getBankAccountNumber());
    bankInstrument.setIfscCode(instrumentDTO.getIfsc());
    bankInstrument.instrumentType = InstrumentType.BANK;
    bankInstrument.userId = instrumentDTO.getUserId();
    List<Instrument> userInstrumentList = userVsInstruments.get(bankInstrument.userId);

    if(userInstrumentList == null){
      userInstrumentList = new ArrayList<>();
      userVsInstruments.put(bankInstrument.userId, userInstrumentList);
    }

    userInstrumentList.add(bankInstrument);

    return mapBankInstrumentToInstrumentDTO(bankInstrument);

  }

  @Override
  public List<InstrumentDTO> getInstrumentByUserId(String userId) {
    List<Instrument> userInstruments = userVsInstruments.get(userId);
    List<InstrumentDTO> userInstrumentFetched = new ArrayList<>();
    for(Instrument instrument : userInstruments){
      if(instrument.getInstrumentType() == InstrumentType.BANK)
        userInstrumentFetched.add(mapBankInstrumentToInstrumentDTO((BankInstrument) instrument));
    }
    return userInstrumentFetched;
  }


  private InstrumentDTO mapBankInstrumentToInstrumentDTO(BankInstrument bankInstrument) {
    InstrumentDTO instrumentDTO = new InstrumentDTO();
    instrumentDTO.setInstrumentType(bankInstrument.instrumentType);
    instrumentDTO.setInstrumentId(bankInstrument.instrumentId);
    instrumentDTO.setBankAccountNumber(bankInstrument.getBankAccountNumber());
    instrumentDTO.setIfsc(bankInstrument.getIfscCode());
    instrumentDTO.setUserId(bankInstrument.userId);
    return instrumentDTO;
  }

}
