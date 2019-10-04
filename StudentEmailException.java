/*
      Author: Moose OMalley
         WEB: Moose OMalley's Software Valley:
              http://moosesoftware.netau.net
      GitHub: All source code for the code developed in my videos is here:
              https://github.com/MooseValley
     YouTube: Mike's Java Software Development Tutorial Videos:
              http://tinyurl.com/MikesJavaVideos
 Source File: StudentEmailException.java
 Description: Part of the "Java - LinkedLists - Students GUI - Add, Delete, Display"
              video tutorials.  The latest source code.

See my YouTube Channel:
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