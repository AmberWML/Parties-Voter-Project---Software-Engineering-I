class Test 
{ 
  private static final String [] reserved = 
   { "and", 
     "begin", 
     "define", 
     "do", 
     "else", 
     "end", 
     "if", 
     "not", 
     "or", 
     "return", 
     "then", 
     "while" }; 
 
  private static int hash(String name) 
  { 

   
      return ((13*name.charAt(0)+19*name.charAt(1))- 11) % 2000;
    
    
  } 
 
  public static void main(String [] args) 
  { 
    for (int index = 0; index < reserved.length ; index += 1) 
    { 
      System.out.print("h(\"" + reserved[index] + "\") = "); 
      System.out.print(hash(reserved[index])); 
      System.out.println(); 
    } 
  } 
}