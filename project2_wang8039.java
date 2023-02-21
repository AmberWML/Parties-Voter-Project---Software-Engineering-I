//Amber Wong
//wang8039
//5444323
//Project2

//  SORT. Sort a linear singly-linked list of INTs.

class Sort
{ 

//  NODE. A node in a linear singly linked list of INTs.

  private static class Node
  {
    private int  number;  //  The INT in the node, duh.
    private Node next;    //  The NODE that follows this one, or NULL.

//  Constructor. Initialize a new NODE with NUMBER and NEXT.

    private Node(int number, Node next)
    {
      this.number = number;
      this.next = next;
    }
  }

//  MAKE NODES. Return a list of NODEs that contains INTs from NUMBERS in order
//  of their appearance.

  private static Node makeNodes(int ... numbers)
  {
    if (numbers.length > 0)
    {
      Node first = new Node(numbers[0], null);
      Node last  = first;
      for (int index = 1; index < numbers.length; index += 1)
      {
        last.next = new Node(numbers[index], null);
        last = last.next;
      }
      return first;
    }
    else
    {
      return null;
    }
  }

//  WRITE NODES. Write the INTs from a list of NODEs in paired square brackets,
//  separated by commas, with a newline at the end.

  private static void writeNodes(Node nodes)
  {
    System.out.print('[');
    if (nodes != null)
    {
      System.out.print(nodes.number);
      nodes = nodes.next;
      while (nodes != null)
      {
        System.out.print(", ");
        System.out.print(nodes.number);
        nodes = nodes.next;
      
    }
  }
    System.out.println(']');
}

//  SORT NODES. Sort UNSORTED, a list of NODEs, into nondecreasing order of its
//  NUMBER slots, without making new NODEs.

  private static Node sortNodes(Node unsorted)
  {
    
    if((unsorted == null) || (unsorted.next == null)){
      return unsorted;
      }
    else{
      Node result;
      Node left = null; // Node left = new Node();
      Node right =null;
      int i =0;

      while(unsorted != null){
          if(i % 2 ==0){
            Node temp= unsorted.next;
            unsorted.next = left;
            left = unsorted;
            unsorted = temp;
            

        }
          else{
            Node temp= unsorted.next;
            unsorted.next = right;
            right = unsorted;
            unsorted = temp;
            
        }
        
          i++;
        
      }
    

    left = sortNodes(left);
    right = sortNodes(right);

    Node sorted =null;
      if (left.number < right.number){
              Node temp = left.next;
              left.next = null;
              result = left;
              sorted = left;
              left = temp;
              
            }
            else{
              Node temp = right.next;
              right.next = null;
              result = right;
              sorted = right;
              right = temp;
            }


    while(left != null && right != null){
      if (left.number < right.number){
        Node temp = left.next;
        left.next = null;
        sorted.next = left;
        sorted = sorted.next;
        left = temp;
        
      }
      else{
        Node temp = right.next;
        right.next = null;
        sorted.next = right;
        sorted = sorted.next;
        right = temp;
      }

    }
    if(left ==null){
      sorted.next = right;
    }
    else{
      sorted.next = left;
    }
    return result;
}

}









//  MAIN. Run some examples. The comments show what must be printed.

  public static void main(String [] args)
  {
    writeNodes(sortNodes(makeNodes()));      //  []
    writeNodes(sortNodes(makeNodes(1)));     //  [1]
    writeNodes(sortNodes(makeNodes(1, 2)));  //  [1, 2]
    writeNodes(sortNodes(makeNodes(2, 1)));  //  [1, 2]

    writeNodes(sortNodes(makeNodes(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    //  [1, 2, 3, 4, 5, 6, 7, 8, 9]

    writeNodes(sortNodes(makeNodes(9, 8, 7, 6, 5, 4, 3, 2, 1)));
    //  [1, 2, 3, 4, 5, 6, 7, 8, 9]

    writeNodes(sortNodes(makeNodes(3, 1, 4, 5, 9, 2, 6, 8, 7)));

     // [1, 2, 3, 4, 5, 6, 7, 8, 9]

    // writeNodes(sortNodes(makeNodes(5, 8, 4, 9, 1, 2, 3, 7, 6)));

  }
}
