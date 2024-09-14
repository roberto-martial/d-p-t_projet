using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.contract
{
    public interface IObserver
    {
        public void UpdateHasMoved(int pastX, int pastY, int newX, int newY);
        public void UpdateMaterialChange(String name);
        public void UpdateFreeEmployee(String name);
        public void UpdateBusyEmployee(String Name);
        public void UpdateEventLog(String eventName);
    
    }
    
}
