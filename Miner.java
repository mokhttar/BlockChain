public class Miner {

    private int difficulty;

    public Miner() {
        this.difficulty = 1;
    }   

    public boolean GoldenHash(Block block) {
        String leadingZeros = new String(new char[difficulty]).replace('\0', '0');
        return block.calculateHash().substring(0, difficulty).equals(leadingZeros);
    }

    //method to update the nonce value until finding the golden hash 
    // public boolean mineBlock(Block block) {
    //      boolean minedBlock = false;
    //     while (!GoldenHash(block)) {
    //         block.setNonce(block.getNonce() + 1);
    //         block.setCurrentHash(block.calculateHash());
    //     }
    //     minedBlock = true;
    //     System.out.println("Block Mined!!! : " + block.getCurrentHash());
    //     return minedBlock;
    // }





    public boolean mineBlock(Block block) {
        String leadingZeros = "0".repeat(difficulty);
        int maxAttempts = Integer.MAX_VALUE;
    
        for (int i = 0; i < maxAttempts; i++) {
            block.setNonce(block.getNonce() + 1);
            String newHash = block.calculateHash();
    
            if (newHash.startsWith(leadingZeros)) {
                block.setCurrentHash(newHash);
                System.out.println("✅ Block Mined: " + newHash);
                return true;
            }
    
            if (block.getNonce() % 1_000_000 == 0) {
                System.out.println("⏳ Trying nonce: " + block.getNonce());
            }
        }
    
        return false;
    }
    





}
