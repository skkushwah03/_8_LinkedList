package _8_LinkedList;

public class L2 {
    // Make Node static so it can be used with static head and tail
    static class Node {
        int data; 
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public static void addfirst(int data) {
        Node newnode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newnode;
            return;
        }
        newnode.next = head;
        head = newnode;
    }
    public static void Lastadd(int data) {
        Node newnode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newnode;
            return;
        }
        tail.next = newnode; // directly attach after tail
        tail = newnode;      // move tail to new node
    }
    public static void middle(int idx,int data){
        if(idx==0){
            addfirst(data);
            return;
        }
         Node newnode = new Node(data);
         size++;
         Node temp=head;
         int i=0; 
         while (i<idx-1) {
          temp=temp.next;
          i++;  
         }
         newnode.next=temp.next;
         temp.next=newnode;

    }
    public static void calsize(){
         Node temp = head;
         int siz=0;
        while (temp != null) {
            siz++;
            temp = temp.next;
        }
        System.out.println("Total size="+siz);
    }
    public static int removefirst(){
        if(head==null){
            System.out.println("Node Empty");
            return Integer.MIN_VALUE;
        }
        else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }
    public static int removelast(){
        if(head==null){
            System.out.println("Node Empty");
            return Integer.MIN_VALUE;
        }
        else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        Node prev=head;
        for(int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int val=prev.next.data;
        prev.next=null;
        tail=prev;
        size--;
        return val;
    }
    public static void removepos(int pos){
        if(head==null){
            return;
        }
        if(pos==0){
            head=head.next;
            return;
        }
      Node temp=head;
      for(int i=0;i<pos-1 && temp!=null;i++){
         temp=temp.next;
      }
      temp.next=temp.next.next;
    }
    public static int  search(int key){
          Node temp = head;
          int i=0;
        while (temp != null) {
            if(temp.data==key){
                // System.out.println(" found at index"+key);
                return i;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }
    public static int helper(Node head,int key){
        if(head==null){
            return -1;
        }
        if(head.data==key){
            return 0;
        }
        int idx=helper(head.next, key);
        if(idx==-1){
            return -1;
        }
        return idx+1;
    }
    public static int recSearch(int key){
        return helper(head,key);
    }
    public static void reverse(){
        Node prev=null;
        Node curr=tail=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;
    }
    public static void deleteNthNode(int n){
        // size-n+1
        int sizz=0;
        Node temp=head;
        while(temp!=null){
            temp=temp.next;
            sizz++;
        }
        if(n==sizz){
            head=head.next;
            return; 
        }
        int i=1;
        int ito_find=sizz-n;
        Node prev=head;
        while(i<ito_find){
            prev=prev.next;
            i++;
        }
        prev.next=prev.next.next;
    }
    public static Node findMid(Node head){
        Node slow=head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;

    }
    public static boolean checkpalindrome(){
        if(head==null||head.next!=null){
            return true;
        }
        //find mid
        Node midNode=findMid(head);
        //reverse second half
        Node prev=null;
        Node curr=midNode;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node right=prev;
        Node left=head;
        //check left half and right half
        while(right!=null){
            if(left.data!=right.data){
                return false;
            }
            left=left.next;
            right=right.next;
        }
        return true;
    }
    public  void slowfaster() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow by 1
            fast = fast.next.next; // Move fast by 2
        }

        System.out.println("Middle element is: " + slow.data); // Slow will be at the middle
    }
    public  void detectcycle(){
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow by 1
            fast = fast.next.next; // Move fast by 2

            if (slow == fast) {
                System.out.println("Cycle detected");
                return; // Cycle detected
            }
        }
        System.out.println("No cycle detected"); // No cycle found
    }
    public  void removeCycle() {
    
        Node slow = head;
        Node fast = head;

        // Detect cycle using Floyd's Tortoise and Hare algorithm
        boolean cycleDetected = false;
        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow by 1
            fast = fast.next.next; // Move fast by 2

            if (slow == fast) {
                cycleDetected = true;
                break; // Cycle detected
            }
        }

        if (!cycleDetected) {
            System.out.println("No cycle detected");
            return; // No cycle found
        }

        // Find the start of the cycle
        slow = head;
        Node prev = null; // To keep track of the node before the meeting point
        while (slow != fast) {
            prev=fast;
            slow = slow.next; // Move slow by 1
            fast = fast.next; // Move fast by 1
        }
       prev.next=null;
       
        }
    public Node cyclebegin() {
    Node slow = head;
    Node fast = head;

    // Step 1: Detect if cycle exists
    while (fast != null && fast.next != null) {
        slow = slow.next;         // move 1 step
        fast = fast.next.next;    // move 2 steps

        if (slow == fast) {
            System.out.println("Cycle detected");
            break;  // exit loop if cycle found
        }
    }

    // Step 2: Find the cycle starting point
    Node temp = head;
    while (temp != slow) {
        temp = temp.next;
        slow = slow.next;
    }
    return slow;  // cycle start node
} 
   
    public  Node getMid(Node head){
        Node slow=head;
        Node fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;

    }
    private Node merge(Node head1,Node head2){
        Node mergedLL=new Node(-1);
         Node temp=mergedLL;
         while (head1 !=null && head2 !=null) {
            if(head1.data<=head2.data){
                temp.next=head;
                head1=head1.next;
                temp=temp.next; 
            }
            else{
                temp.next=head2;
                head2=head2.next;
                temp=temp.next;
            }
         }
         while (head1!=null) {
            temp.next=head1;    
            head1=head1.next;
            temp=temp.next;         
         }
         while(head2 !=null){
            temp.next=head2;
            head2=head2.next;
            temp=temp.next;
         }
         return mergedLL.next;
    }
    public Node Mergesort(Node head){
        if(head==null || head.next==null){
            return head;
        }
        Node mid=getMid(head);
        Node righthead=mid.next;
        mid.next=null;
        Node newleft=Mergesort(head);
        Node newright=Mergesort(righthead);
        return merge(newleft,newright);  
    }
    
    public  void zigzag(){
        //find mid
         Node slow=head;
        Node fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        Node mid=slow;

        //reverse 2nd half
        Node curr=mid.next;
        mid.next=null;
        Node prev=null;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node left=head;
        Node right=prev;
        Node nextL,nextR;

        //alt merge -zig/zag merge
        while(curr !=null && right!=null){
            nextL=left.next;
            left.next=right;
            nextR=right.next;
            right.next=nextL;

            left=nextL;
            right=nextR;
        }
    }
    
    public static void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        L2firstadd lst = new L2firstadd();
        lst.addfirst(1);
        lst.addfirst(2);
        lst.addfirst(3);
        lst.printList();  // Output: 3 -> 2 -> 1 -> null
        lst.calsize();
        lst.removefirst();
        lst.removelast();
        // lst.removemiddle();

    }
}
