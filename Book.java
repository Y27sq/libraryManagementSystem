import java.io.*; 
import java.util.*; 

public class Book implements Catalogue
{
    private String bookAvailability; 
    private int id; 
    private String title, isbnBook, author; 

    private static ArrayList<Integer>ids= new ArrayList<Integer>();  
    private static ArrayList<String>titles= new ArrayList<String>();
    private static ArrayList<String>isbnBooks= new ArrayList<String>();
    private static ArrayList<String>authors= new ArrayList<String>();
    private static ArrayList<String>avai= new ArrayList<String>();
    
    static Scanner sc ; 
    
    //default constructor
    public Book()
    {
    	id=0;
    	title = null;
    	isbnBook =null;
    	author = null;
    	bookAvailability=null; 
    }
    
    //adding the books from the catalogue to the ArrayList
    public static void addBook(){
        for (int a:bookId){
            ids.add(a); 
        }
        for(String b:bookTitle){
            titles.add(b); 
        }
        for(String c:bookAuthor){
            authors.add(c); 
        }
        for(String d:isbn){
            isbnBooks.add(d); 
        }
        for(String e:bookAvai){
            avai.add(e); 
        }
    }
    
    // to search for books with book id 
    public void searchById(){
       int index= ids.indexOf(id); 
        if(index!=-1){
            System.out.println("=======Book details======="); 
            System.out.println("Book ID:"+ ids.get(index)); 
            System.out.println("Book Title:"+titles.get(index)); 
            System.out.println("Book isbn:"+isbnBooks.get(index)); 
            System.out.println("Book Author:"+authors.get(index)); 
            System.out.println("Book Availability:"+avai.get(index)); 
        }else{
            System.out.println("The book cannot be found."); 
        }
    }
    
     public void setId(int bId){
        id= bId; 
    }
    
    public int getID(){
    	return id;
    }
    
    public int getLength(){
        return ids.size(); 
    }
    
     public String getTitle()
    {
    	return title;
    }
    
    
    
    public Book(int id)
    {
    	setId(id);
    	title = titles.get(id);
    	isbnBook = isbnBooks.get(id);
    	author = authors.get(id);
    	bookAvailability = avai.get(id); //added 21/11
    }
    
    public void setAvailability(String av)
    {
    	bookAvailability = av;
    }
    
     public String getAvai(){
        return bookAvailability; 
    }
    
    //to search book for title
    public void searchByTitle(String title) {
    boolean bookFound = false;
    for (int i = 0; i < titles.size(); i++) {
        if (titles.get(i).contains(title)) {
            System.out.println("=======Book details======="); 
            System.out.println("Book ID:" + ids.get(i));
            System.out.println("Book Title:" + titles.get(i));
            System.out.println("Book isbn:" + isbnBooks.get(i));
            System.out.println("Book Author:" + authors.get(i));
            System.out.println("Book Availability:" + avai.get(i));
            bookFound = true;
        }
    }

    if (!bookFound) {
        System.out.println("The book cannot be found");
    }
}


     
     
      //to search for books with author
    public void searchByAuthor(String authorName) {
    boolean authorFound = false;
    for (int j = 0; j < authors.size(); j++) {
        if (authors.get(j).contains(authorName)) {
            System.out.println("=======Book details=======");
            System.out.println("Book ID:" + ids.get(j));
            System.out.println("Book Title:" + titles.get(j));
            System.out.println("Book isbn:" + isbnBooks.get(j));
            System.out.println("Book Author:" + authors.get(j));
            System.out.println("Book Availability:" + avai.get(j));
            authorFound = true;
        }
    }

    if (!authorFound) {
        System.out.println("The book cannot be found");
    }
}

     
     public void addBooks() {
        int x = 0;

        do {
            System.out.println("Please enter the amount of books to add (max 3):");
            sc = new Scanner(System.in);

            try {
                if (sc.hasNextInt()) {
                    x = sc.nextInt();
                    if (x > 0 && x < 3) {
                        break;
                    } else {
                        System.out.println("Please enter a valid amount");
                    }
                } else {
                    System.out.println("Please enter a valid number");
                    sc.next(); 
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                sc.next();
            }
        } while (true);

        System.out.println("You entered: " + x);
        
        
        for(int i=0; i<x; i++){
          System.out.println("Please key in the following information."); 
          
           ids.add(getLength()+i);
           
            System.out.println("Book Title:"); 
             sc.nextLine();
             title= sc.nextLine();
             titles.add(title); 

             System.out.println("Book Author:"); 
            author= sc.nextLine(); 
           authors.add(author); 
           
            System.out.println("Book isbn:"); 
            isbnBook= sc.nextLine(); 
            isbnBooks.add(isbnBook);   
            
            avai.add("Available"); 
        }
    }
     
     public void displayAll(){
         
         System.out.println("List of Books"); 
         for(int k=0; k<ids.size(); k++){
             
                 System.out.println("Book ID:"+ ids.get(k)); 
                 System.out.println("Book Title:"+titles.get(k)); 
                 System.out.println("Book isbn:"+isbnBooks.get(k)); 
                 System.out.println("Book Author:"+authors.get(k)); 
                 System.out.println("Book Availability:"+avai.get(k)); 
         }
     }
     
     //to archive books 
     public void archiveBook(){
        int index= ids.indexOf(id); 
        if(index!=-1){
            ids.remove(index); 
            titles.remove(index); 
            authors.remove(index); 
            isbnBooks.remove(index); 
            System.out.println("Archiving is successful!"); 
        }else{
            System.out.println("Archiving is not successful!"); 
        }
    }
    
    //to edit books
    //edit title
    public void editBookTitle(String title){
        
        int index= ids.indexOf(id); 
        titles.set(index, title); 
     }
     
    //edit availability 
     public void editBookAvai(String a){
         int index= ids.indexOf(id); 
         avai.set(index, a); 
     }
}