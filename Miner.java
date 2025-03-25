public class Miner {

    private int difficulty;

    public Miner() {
        this.difficulty = 6;
    }   

    public boolean GoldenHash(Block block) {
        String leadingZeros = new String(new char[difficulty]).replace('\0', '0');
        return block.calculateHash().substring(0, difficulty).equals(leadingZeros);
    }

    //method to update the nonce value until finding the golden hash 
    public boolean mineBlock(Block block) {
         boolean minedBlock = false;
        while (!GoldenHash(block)) {
            block.setNonce(block.getNonce() + 1);
            block.setCurrentHash(block.calculateHash());
        }
        minedBlock = true;
        System.out.println("Block Mined!!! : " + block.getCurrentHash());
        return minedBlock;
     }
}
