
import java.security.*;

import javax.xml.crypto.dsig.XMLSignature.SignatureValue;

class Transaction {

    PublicKey senderPublicKey;
    String address;
    Integer amount;
    String signature;

    Associates SENDER = new Associates("senderUserName", "testAddress");

    public Transaction(PublicKey senderPublicKey, String address, Integer amount) {
        this.senderPublicKey = senderPublicKey;
        this.address = address;
        this.amount = amount;
        this.signature = signTransaction(SENDER.getPrivateKey());
    }

    // this code is not mine im not pro at java
    public String signTransaction(PrivateKey privateKey) {
        String finalresult="";
        try {
            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initSign(privateKey);
            byte[] strByte = (senderPublicKey.toString() + address + amount.toString()).getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            finalresult = realSig.toString();
            return finalresult;
        } catch (Exception e) {
            System.out.println("Error in signing the transaction");
        }
        return finalresult;
    }

}