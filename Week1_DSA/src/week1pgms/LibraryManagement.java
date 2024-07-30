package week1pgms;

import java.util.*;
class Book{
    int bookId;
    String title;
    String author;
    public Book(int bookId,String title,String author){
        this.bookId=bookId;
        this.title=title;
        this.author=author;
    }
    public String getTitle()
    {
        return title;
    }
}
class LibraryManagement
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the no of books");
        int n=sc.nextInt();
        Book[] books =new Book[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter the details of Book"+(i+1));
            System.out.print("Enter the BookID: ");
            int id=sc.nextInt();
            System.out.print("Enter the Title of the book: ");
            String p=sc.next();
            System.out.print("Enter the Author name of the book: ");
            String c=sc.next();
            sc.nextLine();
            books[i]=new Book(id, p, c);
        }
        System.out.print("Enter the Book Title to search: ");
        String title=sc.next();
        Book[] sortedBooks=books.clone();
        Arrays.sort(sortedBooks,Comparator.comparing(Book::getTitle));
        System.out.println(linearSearch(books,title));
        System.out.println(binarySearch(sortedBooks, title, 0, n-1));
        sc.close();
    }
    public static String linearSearch(Book[] books,String title)
    {
        for(Book book:books)
            if(book.title.equals(title))
                return "Book found using linear search\nBook ID: "+book.bookId+"\nBook Title: "+book.title+"\nBook Author: "+book.author;
        return "Book not found using linear search";
    }
    public static String binarySearch(Book[] sortedBooks,String title,int low,int high)
    {
        if(low<=high)
        {
            int mid=(low+high)/2;
            int comparison = sortedBooks[mid].title.compareTo(title);
            if(comparison==0)
            return "Book found using binary search\nBook ID: "+sortedBooks[mid].bookId+"\nBook Title: "+sortedBooks[mid].title+"\nBook Author: "+sortedBooks[mid].author;
            else if(comparison < 0)
                return binarySearch(sortedBooks, title, mid+1, high);
            else
                return binarySearch(sortedBooks, title, low, mid-1);
        }
        return "Book not found using binary search";
    }
}