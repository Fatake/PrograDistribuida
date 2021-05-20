
/**
* _FileInterfaceImplBase.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from FileInterface.idl
* jueves 20 de mayo de 2021 07:43:25 PM GMT
*/

public abstract class _FileInterfaceImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements FileInterface, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _FileInterfaceImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("downloadFile", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get (method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // FileInterface/downloadFile
       {
         String fileName = in.read_string ();
         byte __result[] = null;
         __result = this.downloadFile (fileName);
         out = rh.createReply();
         FileInterfacePackage.DataHelper.write (out, __result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:FileInterface:1.0"};

  public String[] _ids ()
  {
    return __ids;
  }


} // class _FileInterfaceImplBase
