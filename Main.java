
class Main {
    public static void main(String[] args) {

        Blocks blockChain = new Blocks();
        blockChain.addBlocks("block number one");
        blockChain.addBlocks("block number two");

        blockChain.displayBlockchain();
    }
}