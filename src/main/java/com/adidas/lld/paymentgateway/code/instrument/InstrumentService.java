package com.adidas.lld.paymentgateway.code.instrument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class InstrumentService {

  static Map<String, List<Instrument>> userVsInstruments = new HashMap<>();

  public abstract InstrumentDTO addInstrument(InstrumentDTO instrumentDTO);

  public abstract List<InstrumentDTO> getInstrumentByUserId(String userId);

}
