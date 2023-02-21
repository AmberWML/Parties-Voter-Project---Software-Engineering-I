//Amber Wong
//1913
//Project3



import java.io.FileReader;   //  Read Unicode chars from a file.
import java.io.IOException;  //  In case there's IO trouble.

//  WORDS. Iterator. Read words, represented as STRINGs, from a text file. Each
//  word is the longest possible contiguous series of alphabetic ASCII CHARs.

class Words
{
  private int           ch;      //  Last CHAR from READER, as an INT.
  private FileReader    reader;  //  Read CHARs from here.
  private StringBuilder word;    //  Last word read from READER.

//  Constructor. Initialize an instance of WORDS, so it reads words from a file
//  whose pathname is PATH. Throw an exception if we can't open PATH.

  public Words(String path)
  {
    try
    {
      reader = new FileReader(path);
      ch = reader.read();
    }
    catch (IOException ignore)
    {
      throw new IllegalArgumentException("Cannot open '" + path + "'.");
    }
  }

//  HAS NEXT. Try to read a WORD from READER, converting it to lower case as we
//  go. Test if we were successful.

  public boolean hasNext()
  {
    word = new StringBuilder();
    while (ch > 0 && ! isAlphabetic((char) ch))
    {
      read();
    }
    while (ch > 0 && isAlphabetic((char) ch))
    {
      word.append(toLower((char) ch));
      read();
    }
    return word.length() > 0;
  }

//  IS ALPHABETIC. Test if CH is an ASCII letter.

  private boolean isAlphabetic(char ch)
  {
    return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z';
  }

//  NEXT. If HAS NEXT is true, then return a WORD read from READER as a STRING.
//  Otherwise, return an undefined STRING.

  public String next()
  {
    return word.toString();
  }

//  READ. Read the next CHAR from READER. Set CH to the CHAR, represented as an
//  INT. If there are no more CHARs to be read from READER, then set CH to -1.

  private void read()
  {
    try
    {
      ch = reader.read();
    }
    catch (IOException ignore)
    {
      ch = -1;
    }
  }

//  TO LOWER. Return the lower case ASCII letter which corresponds to the ASCII
//  letter CH.

  private char toLower(char ch)
  {
    if ('a' <= ch && ch <= 'z')
    {
      return ch;
    }
    else
    {
      return (char) (ch - 'A' + 'a');
    }
  }




//  MAIN. For testing. Open a text file whose pathname is the 0th argument from
//  the command line. Read words from the file, and print them one per line.

  public static void main(String [] args)
  {
    Words words = new Words(args[0]);
    while (words.hasNext())
    {
      System.out.println("'" + words.next() + "'");
    }
  }
}

class AnagramTree{
  private class TreeNode{
    private byte[] summary;
    private WordNode words;
    private TreeNode left;
    private TreeNode right;
    private TreeNode(byte[] summary,WordNode words){
      this.summary = summary; // = new byte[26];
      this.words =words;
      this.left = null;
      this.right =null;
    }


  }

  private class WordNode{
   
    private String word;
    private WordNode next;
    private WordNode( String word, WordNode next){
      
      this.word = word;
      this.next = next;
    }

  }

  private TreeNode head;

  public AnagramTree(){
    head = new TreeNode( stringToSummary(""), new WordNode("" , null));
    

  }

  public void add(String word){
    // head = insertrec(head,word);
    byte[] b =stringToSummary(word);
    TreeNode temp = head;
    while(true){
      int comp = compareSummaries(b,temp.summary);
      if(comp==0){
        WordNode w = temp.words;
        while(w != null)
          {
            
            if(w.word.equals(word))
            {
              return;
            }

            w = w.next;
          }

      temp.words = new WordNode(word,temp.words);
     
     
      
    }
    else if(comp<0){
        
        if(temp.left == null){
          TreeNode n = new TreeNode(b,new WordNode(word,null));
          temp.left =n;
          return;
        }
        else{
          temp=temp.left;
        } 
        // head.left = insertrec(head.left , word);
        // head.summary = head.left.summary;
    }
      // else if(comp=0){
      //   head.words += word;
      // }
    else{
        if(temp.right == null){

        TreeNode n = new TreeNode(b,new WordNode(word,null));
        temp.right =n;
        return;
        }

        else{
          temp=temp.right;
        }
      }

    }
  }





  // public TreeNode insertrec(TreeNode head, String word){
  //   byte[] b =stringToSummary(word);
    
  //   return head;
  // }


  public void anagrams(){
    inorder(head.right);


  }

  private void inorder(TreeNode subtree){
    // what us the type?
    if(subtree!=null){
      inorder(subtree.left);
      if(subtree.words != null && subtree.words.next != null){
        WordNode temp = subtree.words;
        while(temp != null){
        System.out.print(temp.word);
        System.out.print(" ");
        temp = temp.next;
      }
      System.out.println();
    }
      inorder(subtree.right);
    }
     
  }
  

  private int compareSummaries(byte[] left, byte[] right){
    int diff = 0;
    for(int i=0; i<26;i++){
      if(left[i]!= right[i]){
        diff = left[i] - right[i];
        return diff;
      } 
    }
    return 0;  

  }
  //word.charAt(some index)

  private byte[] stringToSummary(String word){

    byte[] summary = new byte[26];
    int k = word.length();
    // char[] c = new char[k];
    for(int i=0; i< k;i++)
    {
    int index = word.charAt(i) - 'a';
    summary[index] +=1;
  }
  return summary;
}

}

class Anagrammer{
  public static void main(String[] args){
    Words book = new Words(args[0]);
    AnagramTree tree = new AnagramTree();
    while(book.hasNext()){
       tree.add(book.next());
    }
    tree.anagrams();

  }
}
