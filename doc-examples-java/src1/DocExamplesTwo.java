import java.io.*;


public class DocExamples
{

/*===========================
FORWARD_NULL
===========================*/

  public static Object callA() {
    // This causes a FORWARD_NULL defect report
    return testA(null);
  }
  
  public static Object callB() {
    // No defect report 
    return testA(new Object());
  }
    
  public static String testA(Object o) {
    return o.toString();
  }


/*===========================
GUARDED_BY_VIOLATION
===========================*/

   int count;
    Object lock;

    public void increment() {
        synchronized(lock) { //  lock_event
            count++;         //  guarded_access event
        }
    }
   
   public void times_two() {
        synchronized(lock) { //  lock_event
            count *= 2;      //  guarded_access event
        }
    }

    public void square() {
        synchronized(lock) { //  lock_event
            count *= count;  //  guarded_access event
        }
    }

    public void decrement() {
        count--;             //  Defect: unguarded_access
    }


/*===========================
NULL_RETURN
===========================*/
    public static Object returnC() {
        return null;
    }

    public static Object returnD() {
        return new Object();
    }

    public static void testC() {
        // This demonstrates a very straightforward null-return bug
        returnC().toString();
    }

    public static void testD() {
        returnD().toString();
    }

	public static void testE() {
        returnD().toString();
    }

	public static void testF() {
        // This demonstrates a very straightforward null-return bug
        returnC().toString();
    }
	
	public static void testG() {
        
        returnC().toString();
    }

/*===========================
RESOURCE_LEAK
===========================*/

    public void ResourceLeak()
    {
	try
	{
		FileInputStream fis = new FileInputStream("foo");  
		BufferedInputStream bis = new BufferedInputStream(fis);
		
	}
	catch(Exception e)
	{
		// ignore exception
	}
    }
	
	public void ResourceLeak1()
    {
	try
	{
		FileInputStream fis = new FileInputStream("bar");  
		BufferedInputStream bis = new BufferedInputStream(fis);
		
	}
	catch(Exception e)
	{
		// ignore exception
	}
    }
	
	public void ResourceLeak2()
    {
	try
	{
		FileInputStream fis = new FileInputStream("bar");  
		BufferedInputStream bis = new BufferedInputStream(fis);
		
	}
	catch(Exception e)
	{
		// ignore exception
	}
    }
	
	
/*===========================
REVERSE_INULL
===========================*/

   public static String ReverseINull(Object o) {
	// Dereference o, making the later check a bug
	System.out.println(o.toString());

	if( o == null ) {
		System.out.println("It's null");
	}
	
	return "done";
   }


/*===========================
USE_AFTER_FREE
===========================*/

    public void UseAfterFree()
    {
	try
	{

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		String line1 = br.readLine();
		br.close();
		System.out.println(br.readLine()); // defect
		System.out.println(br.readLine()); // defect
	}
	catch(Exception e)
	{
		// ignore exception
	}
    }






}

