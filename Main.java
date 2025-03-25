import java.util.ArrayList;

class Main {
    public static void main(String[] args) {

        Blocks blockChain = new Blocks();
        ArrayList<String> transactions = new ArrayList<String>();
        
        blockChain.MerkleRoot(transactions);
        blockChain.addBlocks("block number one");
        blockChain.addBlocks("block number two");
        blockChain.displayBlockchain();
    }
}