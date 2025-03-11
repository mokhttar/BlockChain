import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.spec.*;

public class Associates {

    // information about the associates
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String userName;
    private String address;

    public Associates(String userName, String address) {
        this.publicKey = genKeyPairs().getPublic();
        this.privateKey = genKeyPairs().getPrivate();
        this.userName = userName;
        this.address = address;
    }

    // method to generate keys

    public KeyPair genKeyPairs() {
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

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
