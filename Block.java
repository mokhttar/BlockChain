import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// import java.util.Date;

import java.time.LocalTime;

public class Block {

    private int index; // we use this to keep track of the numbers of the blocks in the block chain
    private LocalTime timestamp; // to store the date or something
    private String data; // data of the blockChain like transactions
    private String previousHash; // to keep track of the prev blocks
    private String currentHash; // this is the hash of the current block we get it add the prev data and hash
                                // then
    
    
    private int nonce;


    // Blocks blockChain = new Blocks();

    public Block(int index, String data, String previousHash) {
        this.index = index;
    
        this.timestamp = LocalTime.now();
        this.data = data;
        this.previousHash = previousHash;
        this.currentHash = calculateHash();
        this.nonce = 0;
    }

    public String calculateHash() {
        // String text = String
        //         .valueOf(getIndex() + getPreviousHash() + String.valueOf(getTimestamp()) + String.valueOf(getData())+String.valueOf(getNonce()));



        StringBuilder text = new StringBuilder();
        text.append(getIndex())
            .append(getPreviousHash())
            .append(getTimestamp().toString()) 
            .append(getData())
            .append(getNonce());



        MessageDigest digest = null;
        try {

            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // final byte bytes[] = digest.digest(text.getBytes());

        final byte bytes[] = digest.digest(text.toString().getBytes());
        final StringBuilder hexString = new StringBuilder();
        for (final byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public String toString() {
        return "Block #" + index + " [" +
                "\n\tTimestamp: " + timestamp +
                "\n\tData: " + data +
                "\n\tPrevious Hash: " + previousHash +
                "\n\tCurrent Hash: " + currentHash +
                "\n]";
    }

    // GET/SET
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public int getNonce() {
        return this.nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

}
