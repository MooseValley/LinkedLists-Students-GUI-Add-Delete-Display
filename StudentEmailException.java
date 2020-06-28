/*
      Author: Moose OMalley
 Source File: StudentEmailException.java
 Description: Part of the "Java - LinkedLists - Students GUI - Add, Delete, Display"
              video tutorials.  The latest source code.


The Students GUI Linked List tutorial series:
* Java - LinkedLists - Students GUI - Add, Delete, Display - Part 01
https://youtu.be/0KdG9DZgi6I
* Java - LinkedLists - Students GUI - Add, Delete, Display - Part 02 - Edit
https://youtu.be/LXMg1E6gJJ0
* Java - LinkedLists - Students GUI - Add, Delete, Display - Part 03 - NetBeans
https://youtu.be/xrNSa0nXDLE

Java Source code is in my GitHub:
* https://github.com/MooseValley/LinkedLists-Students-GUI-Add-Delete-Display

See my other Java videos:
* Mike's Java Software Development Tutorial Videos:
http://tinyurl.com/MikesJavaVideos

*/
// IllegalArgumentException UN-CHECKED
// Exception                CHECKED
public class StudentEmailException extends Exception
{

   public StudentEmailException ()
   {
      super ();
   }

   public StudentEmailException (String message)
   {
      super (message);
   }

   public StudentEmailException (Throwable cause)
   {
      super (cause);
   }

   public StudentEmailException (String message, Throwable cause)
   {
      super (message, cause);
   }
}