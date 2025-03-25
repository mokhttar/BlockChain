import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

class Transactions {

    private Integer amount;
    private String reciverAddress;
    private PublicKey publicKey;

    // reciver,Sender
    private Users reciver = new Users("address of the reciver");
    private Users sender = new Users("address of the sender");

    // contrcutor,add the signature using the senders Private Key
    public Transactions(int amount) {
        this.amount = amount;
        this.reciverAddress = reciver.getAddress();
        this.publicKey = sender.getPublicKey();
    }

    // method to apply hash of one peace of data
    private String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Sign Transaction
    public String SignTransaction(PrivateKey privateKey, Transactions transaction) {
        String hashedAddress = applySHA256(reciverAddress);
        String hashedAmount = applySHA256(amount.toString());
        String finalresult = "";
        try {
            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initSign(privateKey);
            byte[] strByte = (hashedAddress + hashedAmount).getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            finalresult = realSig.toString();
        } catch (Exception e) {
            System.out.println("Error in signing the transaction");
        }
        return finalresult;
    }

}