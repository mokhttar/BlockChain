import java.security.*;

// creating an instance of this class will generate  publicKey,privateKey,and ad address to the constructore

class Users {

    private KeyPair keyPair = GenKeys();

    private PublicKey publicKey;
    private PrivateKey PrivateKey;
    private String address;

    public Users(String adress) {
        this.publicKey = keyPair.getPublic();
        this.PrivateKey = keyPair.getPrivate();
        this.address = adress;
    }

    public KeyPair GenKeys() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            System.out.println("Error in generating the key pairs");
        }
        return keyPair;
    }

    public String getAddress() {
        return this.address;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public PrivateKey getPrivateKey() {
        return this.PrivateKey;
    }
}
