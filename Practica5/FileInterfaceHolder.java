
/**
* FileInterfaceHolder.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from FileInterface.idl
* jueves 20 de mayo de 2021 07:43:29 PM GMT
*/

public final class FileInterfaceHolder implements org.omg.CORBA.portable.Streamable
{
  public FileInterface value = null;

  public FileInterfaceHolder ()
  {
  }

  public FileInterfaceHolder (FileInterface initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = FileInterfaceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    FileInterfaceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return FileInterfaceHelper.type ();
  }

}
