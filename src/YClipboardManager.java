import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class YClipboardManager
{
  private Clipboard cb = null;
  
  public YClipboardManager()
  {
	   cb = Toolkit.getDefaultToolkit().getSystemClipboard();
  }
  
  public void SetText(String txt)
  {
	  StringSelection ss = new StringSelection(txt);
	  cb.setContents(ss, ss);
  }
  
  public String GetText() 
  {
	  Transferable t = cb.getContents(this);

	  // Construimos el DataFlavor correspondiente al String java
	  DataFlavor dataFlavorStringJava;
	try {

	  ////////////////////////////////////	
		dataFlavorStringJava = new DataFlavor("application/x-java-serialized-object; class=java.lang.String");
	

	  // Y si el dato se puede conseguir como String java, lo sacamos por pantalla
	  if (t.isDataFlavorSupported(dataFlavorStringJava)) {
	     String texto = (String) t.getTransferData(dataFlavorStringJava);
	     return texto;
	  }
	  ////////////////////////////////////
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  }
  
  
}
