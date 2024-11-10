package com.adidas.lld.paymentgateway.code.payment;

import com.adidas.lld.paymentgateway.code.instrument.InstrumentController;
import com.adidas.lld.paymentgateway.code.instrument.InstrumentDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TransactionService {

  public static Map<String, List<Transaction>> userVsTransactionsList = new HashMap<>();

  InstrumentController instrumentController;

  Processor processor;

  public TransactionService(){
    instrumentController = new InstrumentController();
    processor = new Processor();
  }


  public List<Transaction> getTransactionHistory(String userId) {
    return userVsTransactionsList.get(userId);
  }

  public TransactionDTO makePayment(TransactionDTO transactionDTO) {

    InstrumentDTO senderInstrumentDTO = instrumentController.getInstrumentById(transactionDTO.getSenderUserId(), transactionDTO.getDebitInstrumentId());
    InstrumentDTO receiverInstrumentDTO = instrumentController.getInstrumentById(transactionDTO.getReceiverUserId(), transactionDTO.getCreditInstrumentId());
    /**
     * we could validate sender and receiver devices here and also validate payments
     */
    processor.processPayment(transactionDTO);

    Transaction transaction = new Transaction();
    transaction.setAmount(transactionDTO.getAmount());
    transaction.setTransactionId(UUID.randomUUID().toString());
    transaction.setSenderId(transaction.getSenderId());
    transaction.setReceiverId(transaction.getReceiverId());
    transaction.setTransactionStatus(TransactionStatus.SUCCESS);

    List<Transaction> senderTransactionList = userVsTransactionsList.get(transaction.getSenderId());

    if (senderTransactionList == null) {
      senderTransactionList = new ArrayList<>();
      userVsTransactionsList.put(transaction.getSenderId(), senderTransactionList);

    }
    senderTransactionList.add(transaction);

    List<Transaction> receiverTransactionLists = userVsTransactionsList.get(transaction.getReceiverId());

    if (receiverTransactionLists == null) {
      receiverTransactionLists = new ArrayList<>();
      userVsTransactionsList.put(transaction.getReceiverId(), receiverTransactionLists);

    }

    receiverTransactionLists.add(transaction);
    transactionDTO.setTransactionId(transaction.getTransactionId());
    transactionDTO.setStatus(transaction.getTransactionStatus());

    return transactionDTO;
  }

}
