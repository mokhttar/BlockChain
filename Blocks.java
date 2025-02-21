import java.util.ArrayList;
import java.util.List;

//**Data of the block is :

// private int index; // we use this to keep track of the numbers of the blocks in the block chain
// private long timestamp; // to store the date or somthing
// private String data; // data of the blockChain like transactions
// private String previousHash; // to keep track of the prev blocks
// private String currentHash;

// 

public class Blocks {

    private List<Block> blockChain = new ArrayList<>();

    // constructor to add the Genesis Block
    public Blocks() {
        // add the Genesis Block
        blockChain.add(new Block(0, "Genesis Block", "0"));
    }

    // fucntion to display
    public void displayBlockchain() {
        for (Block block : blockChain) {
            System.out.println(block);
        }
    }

    // retrive last block from the block chain
    public Block getLastBlock(List<Block> blockChain) {
        Block lastBlock = blockChain.get(blockChain.size() - 1);
        return lastBlock;
    }

    // this method is to retrive the last block then get the index increment it
    public int incrementIndex(List<Block> blockChain) {
        Block lastBlock = getLastBlock(blockChain);
        int lastIndex = lastBlock.getIndex();
        lastIndex++;
        return lastIndex;
    }

    // method to get the prevHash

    public String getLastHash(List<Block> blockChain) {
        Block lastBlock = getLastBlock(blockChain);
        String currentHas = lastBlock.getCurrentHash();
        return currentHas;
    }

    // method to add blocks into the blockChain
    public void addBlocks(String data) {
        blockChain.add(new Block(incrementIndex(blockChain), data, getLastHash(blockChain)));
    }

}
