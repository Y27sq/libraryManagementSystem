public interface Catalogue
{
    public int bookId[]={0,1, 2, 3, 4, 5, 6, 7, 8, 9}; 
    public String bookTitle[]={"Beauty and the Beast", "Snow White", "Cinderella", "Harry Potter", "Ikigai","Beauty and the Beast", "Snow White", "Cinderella", "Harry Potter", "Ikigai" }; 
    public String isbn[]={"1903a", "4523b", "4564c", "5657d", "5743e","1903a", "4523b", "4564c", "5657d", "5743e"}; 
    public String bookAuthor[]={"Gabrielle-Suzanne Barbot de Villeneuve","Jacob Grimm","Brothers Grimm","JK Rowling", "Hector Garcia","Gabrielle-Suzanne Barbot de Villeneuve","Jacob Grimm","Brothers Grimm","JK Rowling", "Hector Garcia" }; 
    public String bookAvai[]={"Available", "Available", "Unavailable", "Available", "Unavailable", "Available", "Available", "Unavailable", "Available", "Unavailable"}; 
    
    public void searchById(); 
    public void addBooks(); 
    public void archiveBook(); 
}
