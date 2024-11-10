package com.adidas.lld.paymentgateway.code.instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardService extends InstrumentService {
  @Override
  public InstrumentDTO addInstrument(InstrumentDTO instrumentDTO) {
    CardInstrument cardInstrument = new CardInstrument();

    cardInstrument.setInstrumentId(UUID.randomUUID().toString());
    cardInstrument.setCardNumber(instrumentDTO.getCardNumber());
    cardInstrument.setCvvNumber(instrumentDTO.getCvv());
    cardInstrument.setInstrumentType(InstrumentType.CARD);
    cardInstrument.userId = instrumentDTO.getUserId();
    List<Instrument> userInstrumentList = userVsInstruments.get(cardInstrument.userId);
    if (userInstrumentList == null) {
      userInstrumentList = new ArrayList<>();
      userVsInstruments.put(cardInstrument.userId, userInstrumentList);

    }
    userInstrumentList.add(cardInstrument);
    return mapBankInstrumentToInstrumentDTO(cardInstrument);
  }


  @Override
  public List<InstrumentDTO> getInstrumentByUserId(String userId) {
    List<Instrument> userInstruments = userVsInstruments.get(userId);
    List<InstrumentDTO> userInstrumentFetched = new ArrayList<>();
    for (Instrument instrument : userInstruments) {
      if (instrument.getInstrumentType() == InstrumentType.CARD) {
        userInstrumentFetched.add(mapBankInstrumentToInstrumentDTO((CardInstrument) instrument));
      }
    }

    return userInstrumentFetched;
  }

  private InstrumentDTO mapBankInstrumentToInstrumentDTO(CardInstrument cardInstrument) {
    InstrumentDTO instrumentDTO = new InstrumentDTO();

    instrumentDTO.setInstrumentType(cardInstrument.instrumentType);
    instrumentDTO.setInstrumentId(cardInstrument.getUserId());
    instrumentDTO.setCardNumber(cardInstrument.getCardNumber());
    instrumentDTO.setCvv(cardInstrument.getCvvNumber());
    instrumentDTO.setUserId(cardInstrument.userId);

    return instrumentDTO;
  }
}
