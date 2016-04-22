public class CipherCube {
    private String plaintext;
    private String ciphertext;
    
    /**
     * @param text maybe be plaintext or ciphertext
     * @param spec specifies plaintext (0) or ciphertext(1), 
     */
    public CipherCube(String text, Boolean spec) {
        if(text.length() != 150) 
            text = this.pad(text);
        
        if(spec) {            
            this.ciphertext = text;
            this.decipher();
        }
        else {
            this.plaintext = text;
            this.cipher();
        }
    }
    
    /** 
     * Adjust input text length to 250 characters
     */
    private String pad(String text) {
        StringBuilder strBldr = new StringBuilder(text);
        if(text.length() < 150)           
            while(strBldr.length() <= 150) 
                strBldr.append("X");            
        
        else strBldr.replace(150, text.length()-1, "");
            
        text = new String(strBldr);
        return text;
    }
    
    private void cipher() {
        this.ciphertext = this.plaintext;
        System.out.println("feature not yet supported");
    }
    
    private void decipher() {
        this.plaintext = this.ciphertext;
        System.out.println("feature not et supported");
    }
    
    /**
     * Accessor and mutator methods
     * plaintext setter method may not be required
     */
    
    public String getPlaintext() {
        return this.plaintext;
    }
    
    public String getCiphertext() {
        return this.ciphertext;
    }
    
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
    
}
