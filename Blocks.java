import java.security.MessageDigest;
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

    public Miner miner = new Miner();

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
        Block newBlock = new Block(incrementIndex(blockChain), data, getLastHash(blockChain));
        boolean Mined = miner.mineBlock(newBlock);
        if (Mined)
            blockChain.add(newBlock);

        // blockChain.add(new Block(incrementIndex(blockChain), data, getLastHash(blockChain)));
    }

    public ArrayList<String> getTransactions(ArrayList<Block> blockChain) {
        ArrayList<String> transactions = new ArrayList<String>();
        for (Block block : blockChain) {
            transactions.add(block.getData());
        }
        return transactions;
    }

    // in normal block chain we use the all th e trasaction in my case i have only
    // one trasnaction and that it my data
    public String MerkleRoot(List<String> transactions) {
        if (blockChain.isEmpty())
            return "";
        List<String> hashes = new ArrayList<>(transactions);
        while (hashes.size() > 1) {
            List<String> newHashes = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i += 2) {
                String current = hashes.get(i);
                String next = "";
                // to check if it is odd or even
                if (i + 1 < hashes.size()) {
                    next = hashes.get(i + 1);
                } else {
                    next = current;
                }
                newHashes.add(applySHA256(current + next));
            }
            hashes = newHashes;
        }
        return hashes.get(0);
    }

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

}
