package javablockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    private String hash; // Calculated based on the below data.
    private String previousHash; // Indispensable to build a chain.
    private String data; // The data for the block to hold.
    private long timestamp; // Of the creation of the block.
    private int nonce; // An arbitrary number used in cryptography.

    public Block(String data, String previousHash, long timestamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.hash = calculateBlockHash();
    }

    /**
     * Calculating the hash of the new block.
     */
    private String calculateBlockHash() {
        // First to concatenate different parts of the block to generate the hash from.
        String dataToHash = previousHash
                + timestamp
                + nonce
                + data;
        // MessageDigest is used to get the hash function of "SHA-256".
        MessageDigest digest;
        byte[] bytes = null;
        // Then to generate the hash value out of the input data as a byte array.
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // To transform the byte array into a hex String. A hash is typically represented as a 32-digit hex number.
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
}
