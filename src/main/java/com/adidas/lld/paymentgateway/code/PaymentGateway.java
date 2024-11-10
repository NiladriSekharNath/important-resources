package com.adidas.lld.paymentgateway.code;

import com.adidas.lld.paymentgateway.code.instrument.InstrumentController;
import com.adidas.lld.paymentgateway.code.instrument.InstrumentDTO;
import com.adidas.lld.paymentgateway.code.instrument.InstrumentType;
import com.adidas.lld.paymentgateway.code.payment.Transaction;
import com.adidas.lld.paymentgateway.code.payment.TransactionController;
import com.adidas.lld.paymentgateway.code.payment.TransactionDTO;
import com.adidas.lld.paymentgateway.code.user.UserController;
import com.adidas.lld.paymentgateway.code.user.UserDTO;

import java.util.List;

public class PaymentGateway {

  public static void main(String[] args) {

    InstrumentController instrumentController = new InstrumentController();
    UserController userController = new UserController();
    TransactionController transactionController = new TransactionController();

    //1. add User1

    UserDTO user1 = new UserDTO();
    user1.setUserName("Sj");
    user1.setMail("sj@conceptandcoding.com");
    UserDTO user1Details = userController.addUser(user1);

    //2. add User2

    UserDTO user2 = new UserDTO();
    user2.setUserName("Pj");
    user2.setMail("Pj@conceptandcoding.com");
    UserDTO user2Details = userController.addUser(user2);

    // add bank to User1
    InstrumentDTO bankInstrumentDTO = new InstrumentDTO();
    bankInstrumentDTO.setBankAccountNumber("2343423");
    bankInstrumentDTO.setInstrumentType(InstrumentType.BANK);
    bankInstrumentDTO.setUserId(user1Details.getUserId());
    bankInstrumentDTO.setIfsc("ER3223E");
    InstrumentDTO user1BankInstrument = instrumentController.addInstrument(bankInstrumentDTO);

    System.out.println("Bank Instrument created for User1: " + user1BankInstrument.getInstrumentId());

    // add Card to User2
    InstrumentDTO cardInstrumentDTO = new InstrumentDTO();
    cardInstrumentDTO.setCardNumber("1234353535");
    cardInstrumentDTO.setInstrumentType(InstrumentType.CARD);
    cardInstrumentDTO.setCvv("187");
    cardInstrumentDTO.setUserId(user2.getUserId());
    InstrumentDTO user2CardInstrument = instrumentController.addInstrument(cardInstrumentDTO);
    System.out.println("Card Instrument created for User2: " + user2CardInstrument.getInstrumentId());

    // make payment

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAmount(10);
    transactionDTO.setSenderUserId(user1Details.getUserId());
    transactionDTO.setReceiverUserId(user2Details.getUserId());
    transactionDTO.setDebitInstrumentId(user1BankInstrument.getInstrumentId());
    transactionDTO.setCreditInstrumentId(user2CardInstrument.getInstrumentId());

    transactionController.makePayment(transactionDTO);

    // get All instruments of User1

    List<InstrumentDTO> user1Instruments = instrumentController.getAllInstruments(user1Details.getUserId());

    for (InstrumentDTO instrumentDTO : user1Instruments) {
      System.out.println("User1 InstrumentId: " + instrumentDTO.getInstrumentId() + ": UserId: " + instrumentDTO.getUserId() +
          ": InstrumentType: " + instrumentDTO.getInstrumentType().name());

    }

    // get All instruments of User2
    List<InstrumentDTO> user2Instruments = instrumentController.getAllInstruments(user2Details.getUserId());

    for (InstrumentDTO instrumentDTO : user2Instruments) {
      System.out.println("User2 InstrumentId: " + instrumentDTO.getInstrumentId() + ": UserId: " + instrumentDTO.getUserId() +
          ": InstrumentType: " + instrumentDTO.getInstrumentType().name());

    }

    List<Transaction> user1TransactionList = transactionController.getTransactionHistory(user1Details.getUserId());

    /**
     * and printing the user1TransactionList;
     */
  }
}
