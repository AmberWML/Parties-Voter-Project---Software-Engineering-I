// AMber wong
// wang8039
// lab12
//key is rank, value is object

class PriorityQueue<Base> 
{ 
  private class Node 
  { 
    private Base object; 
    private int  rank; 
    private Node left; 
    private Node right; 
 
    private Node(Base object, int rank) 
    { 
      this.object = object; 
      this.rank = rank; 
      left = null; 
      right = null; 
    } 
  } 
 
  private Node root;  //  Root node of the BST. 
 
  public PriorityQueue(){
    root = new Node(null,20);

  }
  public Base dequeue(){
    if(isEmpty()){
      throw new IllegalStateException(" Empty");
    }
    
    Node point = root;
    Node highest = root.left;
      
      while(highest.left != null)
      {
        point  = highest;
        highest = highest.left;
      }
      
      point.left = highest.right;
      
      return highest.object;
  }


  
  public void enqueue(Base object, int rank){

      if(rank < 0)
        {
          throw new IllegalArgumentException("EMPTY");
        }
        else
        {
          Node subtree = root;
          while(true)
          {
            if(rank < subtree.rank)
            {
              if(subtree.left == null)
              {
                subtree.left = new Node(object, rank);
                break;
              }
              else
              {
                subtree = subtree.left;
              }
            }
            else if(rank > subtree.rank)
            {
              if(subtree.right == null)
              {
                subtree.right = new Node(object, rank);
                break;
              }
              else
              {
                subtree = subtree.right;
              }
            }
            else{
              subtree.object = object;
            }
          }
        }
  }
  public boolean isEmpty(){
    return  root.left == null && root.right == null;
    // return root==null;  //also works
  }
}


//  SNOBBERY. How the aristocracy behaves in a queue. 20 points.

class Snobbery
{

//  MAIN. Queue them up.

  public static void main(String[] args)
  {
    PriorityQueue<String> queue = new PriorityQueue<String>();

    System.out.println(queue.isEmpty());  //  true        2 points

    try
    {
      System.out.println(queue.dequeue());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("Blimey!");      //  Blimey!     2 points
    }

    queue.enqueue("Lancelot",  5);
    queue.enqueue("Fawlty",    7);
    queue.enqueue("Elizabeth", 0);
    queue.enqueue("Charles",   1);
    queue.enqueue("Turing",    7);

    try
    {
      queue.enqueue("Zeus", -100);
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No gods!");     //  No gods!    2 points
    }

    System.out.println(queue.isEmpty());  //  false       2 points

    System.out.println(queue.dequeue());  //  Elizabeth   2 points
    System.out.println(queue.dequeue());  //  Charles     2 points
    System.out.println(queue.dequeue());  //  Lancelot    2 points
    System.out.println(queue.dequeue());  //  Turing      2 points
    System.out.println(queue.dequeue());  //  Fawlty      2 points

//  It's OK if Fawlty comes out before Turing, but both must come out last.

    System.out.println(queue.isEmpty());  //  true        2 points.
  }

}
