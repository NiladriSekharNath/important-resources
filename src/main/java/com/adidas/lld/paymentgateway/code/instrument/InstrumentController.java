package com.adidas.lld.paymentgateway.code.instrument;

import java.util.List;

public class InstrumentController {
  InstrumentFactory instrumentFactory ;

  public InstrumentController(){
    this.instrumentFactory = new InstrumentFactory();
  }

  public InstrumentDTO addInstrument(InstrumentDTO instrument){
    InstrumentService instrumentController = instrumentFactory.getInstrumentService(instrument.getInstrumentType());
    return instrumentController.addInstrument(instrument);
  }

  public InstrumentDTO getInstrumentById(String userId, String instrumentId){
    List<InstrumentDTO> instrumentDTOList = getAllInstruments(userId);
    for(InstrumentDTO instrumentDTO : instrumentDTOList){
      if(instrumentDTO.getInstrumentId().equals(instrumentId)){
        return instrumentDTO;
      }
    }
    return null;
  }

  public List<InstrumentDTO> getAllInstruments(String userId){
    InstrumentService bankInstrumentController = instrumentFactory.getInstrumentService(InstrumentType.BANK);

    InstrumentService cardInstrumentController = instrumentFactory.getInstrumentService(InstrumentType.CARD);

    List<InstrumentDTO> instrumentDTOList = bankInstrumentController.getInstrumentByUserId(userId);
    instrumentDTOList.addAll(cardInstrumentController.getInstrumentByUserId(userId));

    return instrumentDTOList;
  }
}
