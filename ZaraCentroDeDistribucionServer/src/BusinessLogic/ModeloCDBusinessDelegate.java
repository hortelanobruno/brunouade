package BusinessLogic;

import javax.ejb.Remote;

@Remote
public interface ModeloCDBusinessDelegate
{
   public String getDescripcion(int codigo);
   public String getStock(int codigo);
}
